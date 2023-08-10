package com.consolidate.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consolidate.Model.Country;

public interface CountryRepos  extends JpaRepository<Country,Long>{

	List<Country> findByMisStatus(String string);

	List<Country> findByVendorWapStatus(String string);

}
