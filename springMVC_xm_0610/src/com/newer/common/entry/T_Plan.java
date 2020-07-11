package com.newer.common.entry;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class T_Plan {
	int plan_id;
	String plan_name;
	String status;
	String is_feedback;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date begin_date;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date end_date;
	T_Task task;
	String feedback_info;
	String plan_desc;
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIs_feedback() {
		return is_feedback;
	}
	public void setIs_feedback(String is_feedback) {
		this.is_feedback = is_feedback;
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
	public T_Task getTask() {
		return task;
	}
	public void setTask(T_Task task) {
		this.task = task;
	}
	public String getFeedback_info() {
		return feedback_info;
	}
	public void setFeedback_info(String feedback_info) {
		this.feedback_info = feedback_info;
	}
	public String getPlan_desc() {
		return plan_desc;
	}
	public void setPlan_desc(String plan_desc) {
		this.plan_desc = plan_desc;
	}
	@Override
	public String toString() {
		return "T_Plan [plan_id=" + plan_id + ", plan_name=" + plan_name
				+ ", status=" + status + ", is_feedback=" + is_feedback
				+ ", begin_date=" + begin_date + ", end_date=" + end_date
				+ ", task=" + task + ", feedback_info=" + feedback_info
				+ ", plan_desc=" + plan_desc + "]";
	}
	public T_Plan(int plan_id, String plan_name, String status,
			String is_feedback, Date begin_date, Date end_date, T_Task task,
			String feedback_info, String plan_desc) {
		super();
		this.plan_id = plan_id;
		this.plan_name = plan_name;
		this.status = status;
		this.is_feedback = is_feedback;
		this.begin_date = begin_date;
		this.end_date = end_date;
		this.task = task;
		this.feedback_info = feedback_info;
		this.plan_desc = plan_desc;
	}
	public T_Plan() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
