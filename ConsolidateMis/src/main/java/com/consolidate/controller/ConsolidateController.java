package com.consolidate.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.consolidate.Model.Country;
import com.consolidate.Model.MisColumn;
import com.consolidate.service.consolidateService;

@RestController
public class ConsolidateController {

	@Autowired
	private consolidateService misService;

	@GetMapping("/getmis")
	public 	Map<String, List<MisColumn>>  CreateExcelForMis() throws IOException {
		
		return misService.getMis();
	}

	@GetMapping("/add")
	public String addCountry(@RequestBody Country country) {
		misService.addCountry(country);
		return "Success";
	}
}
