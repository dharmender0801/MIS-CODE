package com.consolidate.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.consolidate.Model.MisColumn;

public interface MisRepos extends JpaRepository<MisColumn, Long> {
	
	
 
	@Query("SELECT e FROM MisColumn e WHERE MONTH(e.processDate) = MONTH(:currentDate) AND e.OperatorName=:operatorName AND YEAR(e.processDate) = YEAR(:currentDate)")
	List<MisColumn> findByProcessDateMonthAndOperatorName(LocalDate currentDate, String operatorName);
	
}
