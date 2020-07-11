package com.newer.mysm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newer.dto.PlanDTO;
import com.newer.mysm.data.dao.Tallyselectdao;
import com.newer.mysm.data.entity.T_Employee;
import com.newer.mysm.data.entity.T_Plan;
import com.newer.mysm.data.entity.T_Role;
import com.newer.mysm.data.entity.T_Task;
import com.newer.mysm.data.util.TallyDTO;
import com.newer.mysm.data.util.gaojifenye;
import com.newer.mysm.service.Itallyservice;
@Service("tallyservice")
public class tallyserviceimpl implements Itallyservice {

	@Resource(name="talldao")
	Tallyselectdao dao;

	@Override
	public T_Role roleSelect(int id) {
		
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
	public List<T_Task> taskSelect(int implementorId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.taskSelect(implementorId, pageNo, pageSize);
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
		
		return  dao.planSelectById(id);
	}

	@Override
	public List<gaojifenye> query(Map<String, Object>map) {
		// TODO Auto-generated method stub
		return dao.query(map);
	}

	@Override
	public int Count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.Count(map);
	}

	@Override
	public T_Employee checkLogin(String employeeName, String password) {
		// TODO Auto-generated method stub
		return  dao.selectEmployees(employeeName, password);
	}

	@Override
	public int SelectZongShu(int id) {
		// TODO Auto-generated method stub
		return dao.SelectZongShu(id);
	}
	
//	public List selectbypage(Map<String, Object> map) {
//		return dao.selectall(map);
//	}
//
//	@Override
//	public int getTatleCount(TallyDTO dto) {
//		return dao.getCount(dto);
//	}



}
