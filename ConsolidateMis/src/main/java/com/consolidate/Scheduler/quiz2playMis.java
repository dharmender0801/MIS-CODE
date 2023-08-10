package com.consolidate.Scheduler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.consolidate.Model.Country;
import com.consolidate.Model.Quiz2playMisModel;
import com.consolidate.Repository.CountryRepos;
import com.consolidate.Repository.q2pdailyMisRepos;
import com.consolidate.service.consolidateService;
import com.consolidate.service.impl.serviceImpl;

@Component
public class quiz2playMis {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CountryRepos countryRepos;

	@Autowired
	private q2pdailyMisRepos misRepos;

	@Autowired
	private consolidateService consolidateService;

//	@Scheduled(cron = "0 0 4 * * *")
//	@Scheduled(fixedDelay = 10000)
	public void quiz2plaMis() throws IOException {
		LocalDate currentDate = LocalDate.now();
//		for (int i = 4; i > 0; i--) {
		LocalDate previousDate = currentDate.minusDays(1);
		System.out.println(previousDate);
		int month = currentDate.getMonthValue();
		List<Country> list = countryRepos.findByMisStatus("1");
		for (Country col : list) {
			GetfromJDbc(col, previousDate, month);

//			}
		}

		System.out.println("Data Inserted Successfully");
		consolidateService.Getquiz2playMis();

	}

	private void GetfromJDbc(Country col, LocalDate previousDate, int month) {
		// TODO Auto-generated method stub
		String activebaseQ = "SELECT COUNT(1) FROM appstore_users_subscription WHERE active_status !=5 AND product_id IN ("
				+ col.getProductIds() + ") AND DATE(charge_date)>=DATE(SUBDATE(NOW(),30))";
		String newsubQ = "select count(1) from appstore_users_subscription where date(subscription_date)='"
				+ previousDate + "' and portal_id='" + col.getPortalId() + "'";

		String BilledActQ = "select count(1) from tbl_billing_success where date(process_datetime)='" + previousDate
				+ "' and  type_event='sub'  and product_id in (" + col.getProductIds()
				+ ") and date(subscription_date)!='" + previousDate + "'";
		String newBilledQ = "select count(1) from tbl_billing_success where date(process_datetime)='" + previousDate
				+ "' and  type_event='sub' and product_id in (" + col.getProductIds()
				+ ") and date(subscription_date)='" + previousDate + "'";
		String RenActQ = "select count(1) from tbl_billing_success where date(process_datetime)='" + previousDate
				+ "' and product_id in (" + col.getProductIds() + ") and type_event='ren'";
		String VolChurnQ = "select count(1) from appstore_users_subscription_history where product_id in ("
				+ col.getProductIds() + ") and date(cancel_date)='" + previousDate + "'";
		String newActrevQ = "select ifnull(round((" + col.getNet() + "*(SUM(deducted_amount)" + col.getConversionRate()
				+ "))),0)  " + "from tbl_billing_success where date(process_datetime)='" + previousDate
				+ "' and product_id in (" + col.getProductIds() + ") and type_event='sub'";
		String RenRevQ = "select ifnull(round((" + col.getNet() + "*(SUM(deducted_amount)" + col.getConversionRate()
				+ "))),0)  from tbl_billing_success where date(process_datetime)='" + previousDate
				+ "' and product_id in (" + col.getProductIds() + ") and type_event='ren'";
		String TotalRevQ = "select ifnull(round((" + col.getNet() + "*(SUM(deducted_amount)" + col.getConversionRate()
				+ "))),0)  from tbl_billing_success " + "where date(process_datetime)='" + previousDate
				+ "' and product_id in (" + col.getProductIds() + ")";
		String MtdRevQ = "select ifnull(round((" + col.getNet() + "*(SUM(deducted_amount)" + col.getConversionRate()
				+ "))),0)  from tbl_billing_success where month(date_time)='" + month + "' and product_id in ("
				+ col.getProductIds() + ");";
		String samDayunsubQ = "SELECT COUNT(1) FROM appstore_users_subscription_history WHERE  product_id IN ("
				+ col.getProductIds() + ") AND DATE(subscription_date) = '" + previousDate + "'";

		String activebase = jdbcTemplate.queryForObject(activebaseQ, String.class);
		int newSub = jdbcTemplate.queryForObject(newsubQ, int.class);
		String BilledAct = jdbcTemplate.queryForObject(BilledActQ, String.class);
		String newBilled = jdbcTemplate.queryForObject(newBilledQ, String.class);
		String RenAct = jdbcTemplate.queryForObject(RenActQ, String.class);
		String Volchurn = jdbcTemplate.queryForObject(VolChurnQ, String.class);
		String newActRev = jdbcTemplate.queryForObject(newActrevQ, String.class);
		String RenRev = jdbcTemplate.queryForObject(RenRevQ, String.class);
		String TotaRev = jdbcTemplate.queryForObject(TotalRevQ, String.class);
		String MtdRev = jdbcTemplate.queryForObject(MtdRevQ, String.class);
		int SameDayUnsub = jdbcTemplate.queryForObject(samDayunsubQ, int.class);
		String sub = String.valueOf(SameDayUnsub + newSub);
		saveTOMisTable(activebase, newActRev, sub, BilledAct, newBilled, RenAct, Volchurn, RenRev, TotaRev, MtdRev, col,
				previousDate);

	}

	private void saveTOMisTable(String activebase, String newActRev, String sub, String billedAct, String newBilled,
			String renAct, String volchurn, String renRev, String totaRev, String mtdRev, Country col,
			LocalDate previousDate) {
		// TODO Auto-generated method stub
		Quiz2playMisModel misModel = new Quiz2playMisModel();
		misModel.setActiveBase(activebase);
		misModel.setBilledActivation(billedAct);
		misModel.setChurnCount(volchurn);
		misModel.setDate(previousDate);
		misModel.setOperatorCode(col.getOperatorId());
		misModel.setOperatorName(col.getOperatorName());
		misModel.setMtd(mtdRev);
		misModel.setNewActivationRevenue(newActRev);
		misModel.setTotalRevenue(totaRev);
		misModel.setNewSubscription(sub);
		misModel.setPortalId(col.getPortalId());
		misModel.setInvolChurn("0");
		misModel.setRenewalRevenue(renRev);
		misModel.setProductId(col.getProductIds());
		misModel.setRenewalActivation(renAct);
		misModel.setVolChurn(volchurn);
		misModel.setNewBilledActivation(newBilled);
		misRepos.save(misModel);

	}

}
