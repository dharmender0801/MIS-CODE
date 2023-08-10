package com.consolidate.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consolidate.Model.MisColumn;
import com.consolidate.service.consolidateService;

@RestController
public class Quiz2playMisController {

	@Autowired
	private consolidateService misService;
	
	
	@GetMapping("/quizmis")
	public String  CreateExcelForMis() throws IOException {
		
		return misService.Getquiz2playMis();
	}
	
	
}
