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
@Table(name = "quiz2play_vendor_wap")

public class MisColumn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String OperatorName;
	@Column
	private String CountryName;
	@Column
	private String CpId;
	@Column
	private String VendorName;
	@Column
	private String TotalHits;
	@Column
	private String UniqueHits;
	@Column
	private String SameDayBilled;
	@Column
	private String PostbackSent;
	@Column
	private LocalDate processDate;
	@Column
	private String activation;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOperatorName() {
		return OperatorName;
	}
	public void setOperatorName(String operatorName) {
		OperatorName = operatorName;
	}
	public String getCountryName() {
		return CountryName;
	}
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
	public String getCpId() {
		return CpId;
	}
	public void setCpId(String cpId) {
		CpId = cpId;
	}
	public String getVendorName() {
		return VendorName;
	}
	public void setVendorName(String vendorName) {
		VendorName = vendorName;
	}
	public String getTotalHits() {
		return TotalHits;
	}
	public void setTotalHits(String totalHits) {
		TotalHits = totalHits;
	}
	public String getUniqueHits() {
		return UniqueHits;
	}
	public void setUniqueHits(String uniqueHits) {
		UniqueHits = uniqueHits;
	}
	public String getSameDayBilled() {
		return SameDayBilled;
	}
	public void setSameDayBilled(String sameDayBilled) {
		SameDayBilled = sameDayBilled;
	}
	public String getPostbackSent() {
		return PostbackSent;
	}
	public void setPostbackSent(String postbackSent) {
		PostbackSent = postbackSent;
	}
	public LocalDate getProcessDate() {
		return processDate;
	}
	public void setProcessDate(LocalDate processDate) {
		this.processDate = processDate;
	}
	public String getActivation() {
		return activation;
	}
	public void setActivation(String activation) {
		this.activation = activation;
	}
	public MisColumn(Long id, String operatorName, String countryName, String cpId, String vendorName, String totalHits,
			String uniqueHits, String sameDayBilled, String postbackSent, LocalDate processDate, String activation) {
		super();
		this.id = id;
		OperatorName = operatorName;
		CountryName = countryName;
		CpId = cpId;
		VendorName = vendorName;
		TotalHits = totalHits;
		UniqueHits = uniqueHits;
		SameDayBilled = sameDayBilled;
		PostbackSent = postbackSent;
		this.processDate = processDate;
		this.activation = activation;
	}
	public MisColumn() {
		super();
		// TODO Auto-generated constructor stub
	}

}
