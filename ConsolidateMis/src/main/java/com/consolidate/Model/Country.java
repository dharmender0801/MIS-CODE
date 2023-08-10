package com.consolidate.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "quiz2play_consolidate_mis_operator")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String operatorName;
	@Column
	private String portalId;
	@Column
	private String operatorId;
	@Column
	private String countryName;
	@Column
	private String productIds;
	@Column
	private String vendorWapStatus;
	@Column
	private String misStatus;
	@Column
	private String conversionRate;
	@Column
	private String net;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getPortalId() {
		return portalId;
	}
	public void setPortalId(String portalId) {
		this.portalId = portalId;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getProductIds() {
		return productIds;
	}
	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}
	public String getVendorWapStatus() {
		return vendorWapStatus;
	}
	public void setVendorWapStatus(String vendorWapStatus) {
		this.vendorWapStatus = vendorWapStatus;
	}
	public String getMisStatus() {
		return misStatus;
	}
	public void setMisStatus(String misStatus) {
		this.misStatus = misStatus;
	}
	public String getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(String conversionRate) {
		this.conversionRate = conversionRate;
	}
	public String getNet() {
		return net;
	}
	public void setNet(String net) {
		this.net = net;
	}
	public Country(long id, String operatorName, String portalId, String operatorId, String countryName,
			String productIds, String vendorWapStatus, String misStatus, String conversionRate, String net) {
		super();
		this.id = id;
		this.operatorName = operatorName;
		this.portalId = portalId;
		this.operatorId = operatorId;
		this.countryName = countryName;
		this.productIds = productIds;
		this.vendorWapStatus = vendorWapStatus;
		this.misStatus = misStatus;
		this.conversionRate = conversionRate;
		this.net = net;
	}
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}

}
