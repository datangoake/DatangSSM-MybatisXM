package com.newer.admin.data.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.newer.admin.data.dao.IEmployeeDAO;
import com.newer.admin.data.dao.ITaskDao;
import com.newer.common.entry.T_Employee;
import com.newer.common.entry.T_Role;


@RunWith(SpringJUnit4ClassRunner.class)//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Testa {
	@Resource(name="EmployeeDaoImpl")
	IEmployeeDAO dao;
	@Resource(name="TaskDaoImpl")
	ITaskDao tdao;
	//191
//	this.Service.updateAssignerId(ID);
//	this.Service.updateEmployeeParent(ID);
//	this.Service.deleteEmployeebyid(ID);
	@Test
	public void name() {
	
		System.out.println(dao.updateEmployeeParent(191));
		
	}

	//查询总数（不为管理员）
//	System.out.println(dao.getTotalCounts2());
	//查询总数（角色为员工）
//	System.out.println(dao.getTotalCounts3());
	
	//插入员工
//			T_Employee emp=new T_Employee();
//
//			emp.setEmployee_name("qwer");
//			emp.setPassword("123456");
//			emp.setReal_name("zs");
//			emp.setSex("男");
//			emp.setBirthday(new Timestamp(new Date().getTime()));
//			emp.setDuty("特级工程师");
//			emp.setEnrolldate(new Timestamp(new Date().getTime()));
//			emp.setEducation("大学");
//			emp.setMajor("计算机");
//			emp.setExperience("5年");
//			T_Role r=new T_Role();
//			r.setRole_ID(4);
//			emp.setRole(r);
//			int i=dao.insertEmployee(emp);
//			System.out.println(i);
	
	//按编号查询一个员工
//	System.out.println(dao.findEmployeeById(8));
	
	//按编号删除
//	System.out.println(dao.deleteEmployee(182));
	
	//按编号修改员工（分配员工）
	//按编号修改
//	T_Employee employee=new T_Employee();
//	employee.setEmployee_ID(8);
//	emp.setEmp(employee);
//	emp.setEmployee_ID(180);
//	System.out.println(emp.toString());
//	System.out.println(dao.modifyEmployee(emp));
			
//查询所有主管
//		System.out.println(dao.queryAllManager());
	
	//修改员工上级
//	System.out.println(dao.modifSuperior(emp.getEmployee_ID()));
	
}
