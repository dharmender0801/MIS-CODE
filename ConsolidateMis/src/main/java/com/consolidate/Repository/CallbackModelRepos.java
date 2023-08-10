package com.consolidate.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.consolidate.Model.CallbackVendorModel;

public interface CallbackModelRepos extends JpaRepository<CallbackVendorModel, Long> {

	@Query(value ="SELECT * FROM quiz2play_callback_vendor WHERE cp_id = '39' AND DATE(date_time) = '2023-05-15' AND callback_status = '0' limit 50",nativeQuery = true)
	List<CallbackVendorModel> findByDateTimeAndPortalIdAndCallbackStatus();


}
