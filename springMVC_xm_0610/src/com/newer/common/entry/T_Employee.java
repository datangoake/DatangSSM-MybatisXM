package com.newer.common.entry;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * create table T_EMPLOYEE
(
  EMPLOYEE_ID   NUMBER not null,		--Ա����ţ���������
  EMPLOYEE_NAME VARCHAR2(20) not null,	--�û����ƣ�
  PASSWORD      VARCHAR2(10) not null,		--���룬
  REAL_NAME     VARCHAR2(20) not null,		--��ʵ������
  SEX           VARCHAR2(4) not null,		--�Ա�
  BIRTHDAY      DATE,				--��������
  DUTY          VARCHAR2(30) not null,		--ְλ��Ϣ
  ENROLLDATE    DATE not null,			--��ְʱ��
  EDUCATION     VARCHAR2(30) not null,		--ѧ����Ϣ
  MAJOR         VARCHAR2(30) not null,		--רҵ��Ϣ
  EXPERIENCE    VARCHAR2(30) not null,		--��ҵ����
  ROLE_ID       NUMBER,			--�����������ɫ�����ý�ɫ���
  PARENT_ID     NUMBER			--��������ܣ�����Ա�����
)
 * @author Lg
 *
 */
public class T_Employee {
	int employee_ID;
	String employee_name;
	String password;
	String real_name;
	String sex;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date birthday;
	String duty;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date enrolldate;
	String education;
	String major;
	String experience;
	T_Role role;
	T_Employee emp;
	public int getEmployee_ID() {
		return employee_ID;
	}
	public void setEmployee_ID(int employee_ID) {
		this.employee_ID = employee_ID;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public Date getEnrolldate() {
		return enrolldate;
	}
	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public T_Role getRole() {
		return role;
	}
	public void setRole(T_Role role) {
		this.role = role;
	}
	public T_Employee getEmp() {
		return emp;
	}
	public void setEmp(T_Employee emp) {
		this.emp = emp;
	}
	@Override
	public String toString() {
		return "T_Employee [employee_ID=" + employee_ID + ", employee_name="
				+ employee_name + ", password=" + password + ", real_name="
				+ real_name + ", sex=" + sex + ", birthday=" + birthday
				+ ", duty=" + duty + ", enrolldate=" + enrolldate
				+ ", education=" + education + ", major=" + major
				+ ", experience=" + experience + ", role=" + role + ", emp="
				+ emp + "]";
	}
	public T_Employee(int employee_ID, String employee_name, String password,
			String real_name, String sex, Date birthday, String duty,
			Date enrolldate, String education, String major, String experience,
			T_Role role, T_Employee emp) {
		super();
		this.employee_ID = employee_ID;
		this.employee_name = employee_name;
		this.password = password;
		this.real_name = real_name;
		this.sex = sex;
		this.birthday = birthday;
		this.duty = duty;
		this.enrolldate = enrolldate;
		this.education = education;
		this.major = major;
		this.experience = experience;
		this.role = role;
		this.emp = emp;
	}
	public T_Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
