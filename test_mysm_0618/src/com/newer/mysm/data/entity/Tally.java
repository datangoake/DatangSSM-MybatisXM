package com.newer.mysm.data.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Tally {
	private int tallyid;//编号
	private String type;//类型
	private Double price;//价格
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date payDate;//支付日期
	public int getTallyid() {
		return tallyid;
	}
	public void setTallyid(int tallyid) {
		this.tallyid = tallyid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Tally() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tally(int tallyid, String type, Double price, Date payDate) {
		super();
		this.tallyid = tallyid;
		this.type = type;
		this.price = price;
		this.payDate = payDate;
	}
	@Override
	public String toString() {
		return "Tally [tallyid=" + tallyid + ", type=" + type + ", price="
				+ price + ", payDate=" + payDate + "]";
	}

	
}
