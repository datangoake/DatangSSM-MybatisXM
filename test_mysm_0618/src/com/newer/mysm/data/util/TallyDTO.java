package com.newer.mysm.data.util;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TallyDTO {
	private Integer search_tallyid;//编号
	private String search_type;//类型
	private Double search_price1;//最低价格
	private Double search_price2;//最高价格
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date search_payDate1;//开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date search_payDate2;//结束时间
	public TallyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TallyDTO(Integer search_tallyid, String search_type,
			Double search_price1, Double search_price2, Date search_payDate1,
			Date search_payDate2) {
		super();
		this.search_tallyid = search_tallyid;
		this.search_type = search_type;
		this.search_price1 = search_price1;
		this.search_price2 = search_price2;
		this.search_payDate1 = search_payDate1;
		this.search_payDate2 = search_payDate2;
	}
	public Integer getSearch_tallyid() {
		return search_tallyid;
	}
	public void setSearch_tallyid(Integer search_tallyid) {
		this.search_tallyid = search_tallyid;
	}
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	public Double getSearch_price1() {
		return search_price1;
	}
	public void setSearch_price1(Double search_price1) {
		this.search_price1 = search_price1;
	}
	public Double getSearch_price2() {
		return search_price2;
	}
	public void setSearch_price2(Double search_price2) {
		this.search_price2 = search_price2;
	}
	public Date getSearch_payDate1() {
		return search_payDate1;
	}
	public void setSearch_payDate1(Date search_payDate1) {
		this.search_payDate1 = search_payDate1;
	}
	public Date getSearch_payDate2() {
		return search_payDate2;
	}
	public void setSearch_payDate2(Date search_payDate2) {
		this.search_payDate2 = search_payDate2;
	}

	
}