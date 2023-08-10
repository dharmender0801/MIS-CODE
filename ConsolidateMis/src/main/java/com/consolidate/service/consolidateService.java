package com.consolidate.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.consolidate.Model.Country;
import com.consolidate.Model.MisColumn;

public interface consolidateService {

	Map<String, List<MisColumn>> getMis() throws IOException;

	void addCountry(Country country);

	String Getquiz2playMis() throws IOException;

}
