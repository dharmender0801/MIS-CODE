package com.consolidate.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.consolidate.Model.Quiz2playMisModel;

@Repository
public interface q2pdailyMisRepos extends JpaRepository<Quiz2playMisModel, Long> {

	
	@Query("SELECT e FROM Quiz2playMisModel e WHERE MONTH(e.date) = MONTH(:previousDate) AND e.operatorName = :operatorName AND YEAR(e.date) = YEAR(:previousDate)")
	List<Quiz2playMisModel> findByDateMonthAndOperatorName(@Param("previousDate") LocalDate previousDate, @Param("operatorName") String operatorName);

	

	List<Quiz2playMisModel> findByDateAndOperatorName(LocalDate previousDate, String string);


}
