package com.consolidate.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tbl_quiz2play_mis")
@AllArgsConstructor
@NoArgsConstructor
public class Quiz2playMisModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "date")
	private LocalDate date;
	@Column
	private String portalId;
	@Column
	private String productId;
	@Column
	private String operatorCode;
	@Column
	private String operatorName;
	@Column
	private String activeBase;
	@Column
	private String newSubscription;
	@Column
	private String billedActivation;
	@Column
	private String newBilledActivation;
	@Column
	private String renewalActivation;
	@Column
	private String churnCount;
	@Column
	private String involChurn;
	@Column
	private String volChurn;
	@Column
	private String newActivationRevenue;
	@Column
	private String renewalRevenue;
	@Column
	private String totalRevenue;
	@Column
	private String mtd;
	@Column
	private String totalRevenuelc;
	@Column
	private String grossMtd;

}
