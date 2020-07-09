package com.newer.mysm.data.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class T_Task {
	int task_id;
	String task_name;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date begin_date;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date end_date;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date real_begin_date;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date real_end_date;
	String status;
	T_Employee mp_emp;
	T_Employee as_emp;
	String task_desc;
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public Date getBegin_date() {
		return begin_date;
	}
	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Date getReal_begin_date() {
		return real_begin_date;
	}
	public void setReal_begin_date(Date real_begin_date) {
		this.real_begin_date = real_begin_date;
	}
	public Date getReal_end_date() {
		return real_end_date;
	}
	public void setReal_end_date(Date real_end_date) {
		this.real_end_date = real_end_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public T_Employee getMp_emp() {
		return mp_emp;
	}
	public void setMp_emp(T_Employee mp_emp) {
		this.mp_emp = mp_emp;
	}
	public T_Employee getAs_emp() {
		return as_emp;
	}
	public void setAs_emp(T_Employee as_emp) {
		this.as_emp = as_emp;
	}
	public String getTask_desc() {
		return task_desc;
	}
	public void setTask_desc(String task_desc) {
		this.task_desc = task_desc;
	}
	@Override
	public String toString() {
		return "T_Task [task_id=" + task_id + ", task_name=" + task_name
				+ ", begin_date=" + begin_date + ", end_date=" + end_date
				+ ", real_begin_date=" + real_begin_date + ", real_end_date="
				+ real_end_date + ", status=" + status + ", mp_emp=" + mp_emp
				+ ", as_emp=" + as_emp + ", task_desc=" + task_desc + "]";
	}
	public T_Task(int task_id, String task_name, Date begin_date,
			Date end_date, Date real_begin_date, Date real_end_date,
			String status, T_Employee mp_emp, T_Employee as_emp,
			String task_desc) {
		super();
		this.task_id = task_id;
		this.task_name = task_name;
		this.begin_date = begin_date;
		this.end_date = end_date;
		this.real_begin_date = real_begin_date;
		this.real_end_date = real_end_date;
		this.status = status;
		this.mp_emp = mp_emp;
		this.as_emp = as_emp;
		this.task_desc = task_desc;
	}
	public T_Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
