package com.consolidate.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class count {
	
	private String count;
	private String distinctcount;
	private String cpId;
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getDistinctcount() {
		return distinctcount;
	}
	public void setDistinctcount(String distinctcount) {
		this.distinctcount = distinctcount;
	}
	public String getCpId() {
		return cpId;
	}
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
	public count(String count, String distinctcount, String cpId) {
		super();
		this.count = count;
		this.distinctcount = distinctcount;
		this.cpId = cpId;
	}
	public count() {
		super();
		// TODO Auto-generated constructor stub
	}

}
