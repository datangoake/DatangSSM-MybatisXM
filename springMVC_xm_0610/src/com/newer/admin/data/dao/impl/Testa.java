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


@RunWith(SpringJUnit4ClassRunner.class)//��ʾ�̳���SpringJUnit4ClassRunner��
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

	//��ѯ��������Ϊ����Ա��
//	System.out.println(dao.getTotalCounts2());
	//��ѯ��������ɫΪԱ����
//	System.out.println(dao.getTotalCounts3());
	
	//����Ա��
//			T_Employee emp=new T_Employee();
//
//			emp.setEmployee_name("qwer");
//			emp.setPassword("123456");
//			emp.setReal_name("zs");
//			emp.setSex("��");
//			emp.setBirthday(new Timestamp(new Date().getTime()));
//			emp.setDuty("�ؼ�����ʦ");
//			emp.setEnrolldate(new Timestamp(new Date().getTime()));
//			emp.setEducation("��ѧ");
//			emp.setMajor("�����");
//			emp.setExperience("5��");
//			T_Role r=new T_Role();
//			r.setRole_ID(4);
//			emp.setRole(r);
//			int i=dao.insertEmployee(emp);
//			System.out.println(i);
	
	//����Ų�ѯһ��Ա��
//	System.out.println(dao.findEmployeeById(8));
	
	//�����ɾ��
//	System.out.println(dao.deleteEmployee(182));
	
	//������޸�Ա��������Ա����
	//������޸�
//	T_Employee employee=new T_Employee();
//	employee.setEmployee_ID(8);
//	emp.setEmp(employee);
//	emp.setEmployee_ID(180);
//	System.out.println(emp.toString());
//	System.out.println(dao.modifyEmployee(emp));
			
//��ѯ��������
//		System.out.println(dao.queryAllManager());
	
	//�޸�Ա���ϼ�
//	System.out.println(dao.modifSuperior(emp.getEmployee_ID()));
	
}
