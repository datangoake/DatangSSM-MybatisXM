package com.newer.admin.web.util;
/*
 * 叶金豪2020-6-16
 * 
 */
import com.newer.common.entry.T_Employee;
import com.newer.common.entry.T_Role;

public class EmployeeForm {
	T_Employee emp;//表单员工
	T_Role role;//表单角色
	
	
	String confirmPassword;//第二次确认密码
		
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public T_Employee getEmp() {
		return emp;
	}
	public void setEmp(T_Employee emp) {
		this.emp = emp;
	}
	public T_Role getRole() {
		return role;
	}
	public void setRole(T_Role role) {
		this.role = role;
	}
	public EmployeeForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeForm(T_Employee emp, T_Role role, String confirmPassword) {
		super();
		this.emp = emp;
		this.role = role;
		this.confirmPassword = confirmPassword;
	}
	@Override
	public String toString() {
		return "EmployeeForm [emp=" + emp + ", role=" + role
				+ ", confirmPassword=" + confirmPassword + "]";
	}
	
}
