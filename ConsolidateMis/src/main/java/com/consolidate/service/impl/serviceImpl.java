package com.consolidate.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consolidate.Model.Country;
import com.consolidate.Model.MisColumn;
import com.consolidate.Model.Quiz2playMisModel;
import com.consolidate.Repository.CountryRepos;
import com.consolidate.Repository.MisRepos;
import com.consolidate.Repository.q2pdailyMisRepos;
import com.consolidate.excel.CreateExcel;
import com.consolidate.excel.MisExcel;
import com.consolidate.service.consolidateService;
import com.consolidate.util.sendEmail;

@Service
public class serviceImpl implements consolidateService {

	@Autowired
	private CountryRepos countryRepos;
	@Autowired
	private MisRepos misRepos;

	@Autowired
	private q2pdailyMisRepos misRepos2;

	@Override
	public Map<String, List<MisColumn>> getMis() throws IOException {
		// TODO Auto-generated method stub

		Map<String, List<MisColumn>> maplist = new LinkedHashMap<>();
		List<Country> list = countryRepos.findByVendorWapStatus("1");
		for (Country coun : list) {
			LocalDate currentDate = LocalDate.now();
			List<MisColumn> MisList = misRepos.findByProcessDateMonthAndOperatorName(currentDate,
					coun.getOperatorName());
			maplist.put(coun.getOperatorName(), MisList);
		}
		CreateExcel createExcel = new CreateExcel();
		createExcel.CreateExcelSheet(list, maplist);

		return maplist;

	}

	@Override
	public void addCountry(Country country) {
		// TODO Auto-generated method stub
		countryRepos.save(country);
	}

	@Override
	public String Getquiz2playMis() throws IOException {
		// TODO Auto-generated method stub
		Map<String, List<Quiz2playMisModel>> maplist = new LinkedHashMap<>();
		Map<String, List<Quiz2playMisModel>> OneDay = new LinkedHashMap<>();
		List<Country> list = countryRepos.findByMisStatus("1");
		for (Country coun : list) {
			LocalDate currentDate = LocalDate.now();
			LocalDate previousDate = currentDate.minusDays(1);
			List<Quiz2playMisModel> MisList = misRepos2.findByDateMonthAndOperatorName(previousDate,
					coun.getOperatorName());
			maplist.put(coun.getOperatorName(), MisList);
			List<Quiz2playMisModel> onedayList = misRepos2.findByDateAndOperatorName(previousDate,
					coun.getOperatorName());
			OneDay.put(coun.getOperatorName(), onedayList);
		}
		MisExcel.CreateExcelSheet(list, maplist);
		sendEmail.sendEmailer( OneDay);

		return "Success";
	}

}
