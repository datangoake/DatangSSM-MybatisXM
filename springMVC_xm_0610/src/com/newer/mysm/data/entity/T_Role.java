package com.newer.mysm.data.entity;
/**
 * create table T_ROLE
(
  ROLE_ID   NUMBER not null,		--角色编号，主键序列
  ROLE_NAME VARCHAR2(20) not null,	--角色名称
  ROLE_DESC VARCHAR2(30) not null	--角色描述
)
 * @author Lg
 *
 */
public class T_Role {
	private int role_ID;
	private String role_NAME;
	private String role_DESC;
	
	public int getRole_ID() {
		return role_ID;
	}
	public void setRole_ID(int role_ID) {
		this.role_ID = role_ID;
	}
	public String getRole_NAME() {
		return role_NAME;
	}
	public void setRole_NAME(String role_NAME) {
		this.role_NAME = role_NAME;
	}
	public String getRole_DESC() {
		return role_DESC;
	}
	public void setRole_DESC(String role_DESC) {
		this.role_DESC = role_DESC;
	}

	public String toString() {
		return "T_Role [role_ID=" + role_ID + ", role_NAME=" + role_NAME
				+ ", role_DESC=" + role_DESC + "]";
	}
	public T_Role(int role_ID, String role_NAME, String role_DESC) {
		super();
		this.role_ID = role_ID;
		this.role_NAME = role_NAME;
		this.role_DESC = role_DESC;
	}
	public T_Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
