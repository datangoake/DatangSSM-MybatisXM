package com.newer.person.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newer.common.entry.*;
import com.newer.dto.PlanDTO;
import com.newer.person.data.dao.PersonDao;
import com.newer.person.service.PersonService;

@Service("PersonService")
public class PersonServiceImpl implements PersonService{

	public PersonServiceImpl() {
		System.out.println("PersonServiceÊµÀý»¯");
	}
	
	@Resource(name="PersonDao")
	PersonDao dao;
	
	@Override
	public T_Role roleSelect(int id) {
		// TODO Auto-generated method stub
		return dao.roleSelect(id);
	}

	@Override
	public T_Employee personSelect(int id) {
		// TODO Auto-generated method stub
		return dao.personSelect(id);
	}

	@Override
	public T_Task taskSelectById(int id) {
		// TODO Auto-generated method stub
		return dao.taskSelectById(id);
	}

	@Override
	public List<T_Task> taskSelect(int implementorId,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.taskSelect(implementorId,pageNo,pageSize);
	}

	@Override
	public List<T_Plan> planSelect(int taskId) {
		// TODO Auto-generated method stub
		return dao.planSelect(taskId);
	}

	@Override
	public int PlanInsert(T_Plan plan) {
		// TODO Auto-generated method stub
		return dao.PlanInsert(plan);
	}

	@Override
	public int PlanDelete(int id) {
		// TODO Auto-generated method stub
		return dao.PlanDelete(id);
	}

	@Override
	public int planUpdate(T_Plan plan) {
		// TODO Auto-generated method stub
		return dao.planUpdate(plan);
	}

	@Override
	public int jihuaCount(int rnwuId) {
		// TODO Auto-generated method stub
		return dao.jihuaCount(rnwuId);
	}

	@Override
	public int StatusUpdate(String statu, int renwuId) {
		// TODO Auto-generated method stub
		return dao.StatusUpdate(statu, renwuId);
	}

	@Override
	public T_Plan planSelectById(int id) {
		// TODO Auto-generated method stub
		return dao.planSelectById(id);
	}

	@Override
	public List<Object> query(PlanDTO dto, int pageNo, int pageSize,int id) {
		// TODO Auto-generated method stub
		return dao.query(dto, pageNo, pageSize,id);
	}

	@Override
	public int Count(PlanDTO dto,int id) {
		// TODO Auto-generated method stub
		return dao.Count(dto,id);
	}

	@Override
	public T_Employee checkLogin(String employeeName, String password) {
		T_Employee employee = null;

		List<T_Employee> employeeList = new ArrayList<T_Employee>();

		String whereSql= "select * from t_employee where employee_name=? and password=?";
		Object[] params = new Object[] { employeeName, password };

		employeeList = (List<T_Employee>) dao.selectEmployees(whereSql, params);
				

		if (employeeList != null && employeeList.size() > 0) {
			employee = employeeList.get(0);
		}
		return employee;
	}

	@Override
	public int SelectZongShu(int id) {
		String sql = "select count(*) from t_task where implementor_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		System.out.println("service:"+dao.SelectZongShu(sql, params));
		return dao.SelectZongShu(sql, params);
		
	}

	

}
