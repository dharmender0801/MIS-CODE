package com.consolidate.Model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "quiz2play_callback_vendor")
public class CallbackVendorModel {
	@Id
	private long id;
	@Column
	private String msisdn;
	@Column
	private String productId;
	@Column
	private String channel;
	@Column
	private String cpId;
	@Column
	private String kpId;
	@Column
	private LocalDate dateTime;
	@Column
	private String callbackStatus;
	@Column
	private String portalId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getCpId() {
		return cpId;
	}
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
	public String getKpId() {
		return kpId;
	}
	public void setKpId(String kpId) {
		this.kpId = kpId;
	}
	public LocalDate getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}
	public String getCallbackStatus() {
		return callbackStatus;
	}
	public void setCallbackStatus(String callbackStatus) {
		this.callbackStatus = callbackStatus;
	}
	public String getPortalId() {
		return portalId;
	}
	public void setPortalId(String portalId) {
		this.portalId = portalId;
	}
	public CallbackVendorModel(long id, String msisdn, String productId, String channel, String cpId, String kpId,
			LocalDate dateTime, String callbackStatus, String portalId) {
		super();
		this.id = id;
		this.msisdn = msisdn;
		this.productId = productId;
		this.channel = channel;
		this.cpId = cpId;
		this.kpId = kpId;
		this.dateTime = dateTime;
		this.callbackStatus = callbackStatus;
		this.portalId = portalId;
	}
	public CallbackVendorModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
