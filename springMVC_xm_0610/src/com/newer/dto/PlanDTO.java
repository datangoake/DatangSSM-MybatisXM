package com.newer.dto;

import java.util.Date;

public class PlanDTO {

	String plan_name;//计划名称
	String task_name;//所属任务
	String begin_date_befor;//开始时间1
	String begin_date_after;//开始时间2
	String end_date_befor;//结束时间1
	String end_date_after;//结束时间2
	String feedback_info;//是否反馈
	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getBegin_date_befor() {
		return begin_date_befor;
	}
	public void setBegin_date_befor(String begin_date_befor) {
		this.begin_date_befor = begin_date_befor;
	}
	public String getBegin_date_after() {
		return begin_date_after;
	}
	public void setBegin_date_after(String begin_date_after) {
		this.begin_date_after = begin_date_after;
	}
	public String getEnd_date_befor() {
		return end_date_befor;
	}
	public void setEnd_date_befor(String end_date_befor) {
		this.end_date_befor = end_date_befor;
	}
	public String getEnd_date_after() {
		return end_date_after;
	}
	public void setEnd_date_after(String end_date_after) {
		this.end_date_after = end_date_after;
	}
	public String getFeedback_info() {
		return feedback_info;
	}
	public void setFeedback_info(String feedback_info) {
		this.feedback_info = feedback_info;
	}
	@Override
	public String toString() {
		return "PlanDTO [plan_name=" + plan_name + ", task_name=" + task_name
				+ ", begin_date_befor=" + begin_date_befor
				+ ", begin_date_after=" + begin_date_after
				+ ", end_date_befor=" + end_date_befor + ", end_date_after="
				+ end_date_after + ", feedback_info=" + feedback_info + "]";
	}
	public PlanDTO(String plan_name, String task_name, String begin_date_befor,
			String begin_date_after, String end_date_befor,
			String end_date_after, String feedback_info) {
		super();
		this.plan_name = plan_name;
		this.task_name = task_name;
		this.begin_date_befor = begin_date_befor;
		this.begin_date_after = begin_date_after;
		this.end_date_befor = end_date_befor;
		this.end_date_after = end_date_after;
		this.feedback_info = feedback_info;
	}
	public PlanDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
