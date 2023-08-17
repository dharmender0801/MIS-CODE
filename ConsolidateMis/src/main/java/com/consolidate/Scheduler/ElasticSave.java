package com.consolidate.Scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.consolidate.Model.CallbackVendorModel;
import com.consolidate.Model.Country;
import com.consolidate.Model.MisColumn;
import com.consolidate.Model.count;
import com.consolidate.Repository.CallbackModelRepos;
import com.consolidate.Repository.CountryRepos;
import com.consolidate.Repository.MisRepos;
import com.consolidate.service.consolidateService;

@Component
public class ElasticSave {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CountryRepos countryRepos;
	
	@Autowired
	private consolidateService misService;

	@Autowired
	private MisRepos misRepos;

	@Value("${hit}")
	private String hitquery;

//	@Scheduled(fixedDelay = 10000)
	@Scheduled(cron = "0 0 3 * * *")
	public void shdeuling() throws IOException {
//		misRepos.deleteAll();
//		for (int i = 3; i > 0; i--) {

			List<Country> list = countryRepos.findAll();
			for (Country col : list) {
				String query = hitquery;
				LocalDate currentDate = LocalDate.now();
				LocalDate previousDate = currentDate.minusDays(1);
				System.out.println(previousDate);
				query = query.replace("2023-05-11", previousDate.toString());
				query = query.replace("1006,1007", col.getProductIds());
				List<count> countList = jdbcTemplate.query(query,
						(rs, rowNum) -> new count(rs.getString(1), rs.getString(2), rs.getString(3)));
				for (count li : countList) {

					try {

						String subquery = "SELECT COUNT(*) FROM `appstore_users_subscription`  WHERE DATE(subscription_date)='"
								+ previousDate + "' AND adv_id='" + li.getCpId() + "'" + "";

						String unsub = "SELECT COUNT(*) FROM `appstore_users_subscription_history`  WHERE DATE(subscription_date)='"
								+ previousDate + "' AND adv_id='" + li.getCpId() + "'" + "";

						String samedayBilledquery = "SELECT COUNT(*) FROM `appstore_users_subscription`  WHERE DATE(subscription_date)='"
								+ previousDate + "'" + " AND DATE(charge_date)='" + previousDate + "' AND adv_id='"
								+ li.getCpId() + "'";
						String pstbk = "SELECT COUNT(*) FROM `quiz2play_callback_vendor` " + " WHERE cp_id='"
								+ li.getCpId() + "' AND DATE(date_time)='" + previousDate
								+ "'  AND callback_status='1' ";
						String vend = "SELECT vendor_name FROM `quiz2play_vendor_detail` WHERE cp_id ='" + li.getCpId()
								+ "'";
						int unsubcount = jdbcTemplate.queryForObject(unsub, int.class);
						int subcount = jdbcTemplate.queryForObject(subquery, int.class);
						int samDaybilledCount = jdbcTemplate.queryForObject(samedayBilledquery, int.class);
						int postback = jdbcTemplate.queryForObject(pstbk, int.class);
						String vendName = jdbcTemplate.queryForObject(vend, String.class);
						MisColumn column = new MisColumn();
						column.setCountryName(col.getCountryName());
						column.setCpId(li.getCpId());
						column.setOperatorName(col.getOperatorName());
						column.setProcessDate(previousDate);
						column.setVendorName(vendName);
						column.setActivation(String.valueOf(subcount + unsubcount));
						column.setPostbackSent(String.valueOf(postback));
						column.setSameDayBilled(String.valueOf(samDaybilledCount));
						column.setTotalHits(li.getCount());
						column.setUniqueHits(li.getDistinctcount());
						misRepos.save(column);
					} catch (EmptyResultDataAccessException e) {
						// handle the exception gracefully, e.g. by logging the error or showing a
						// user-friendly message
						System.out.println("No result found for the given query.");
					}

				}

//			}
		}

		System.out.println("Done All is ");
		misService.getMis();

	}

//	@Autowired
//	private CallbackModelRepos callbackModelRepos;
//
//	@Scheduled(fixedDelay = 100000)
//	public void postback() {
//		LocalDate currentDate = LocalDate.now();
//		LocalDate previousDate = currentDate.minusDays(1);
//		List<CallbackVendorModel> model = callbackModelRepos.findByDateTimeAndPortalIdAndCallbackStatus();
//		System.out.println(model.size());
//		for (CallbackVendorModel col : model) {
////			try {
////				Thread.sleep(1000); // Sleep for 3 seconds (3000 milliseconds)
////			} catch (InterruptedException e) {
////				// Handle interrupted exception if necessary
////			}
////			String url = "http://tracking.y2nx.com/postback?cid=" + col.getKpId() + "";
////			String responsefromhitUrl = sendHttpRequest(url);
////			System.out.println(responsefromhitUrl);
//			col.setCallbackStatus("2");
//		}
//		callbackModelRepos.saveAll(model);
//
//	}

	public String sendHttpRequest(String url) {

		StringBuffer response = new StringBuffer();

		try {

			HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());

			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoInput(true);
			connection.setDoOutput(true);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.toString();
	}

}
