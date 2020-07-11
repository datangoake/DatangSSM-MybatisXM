package com.newer.mysm.data.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.newer.mysm.data.dao.IPlanDAO;
import com.newer.mysm.data.dao.Tallyselectdao;
import com.newer.mysm.data.entity.T_Employee;
import com.newer.mysm.data.entity.T_Plan;
import com.newer.mysm.data.entity.T_Role;
import com.newer.mysm.data.entity.T_Task;
import com.newer.mysm.data.util.gaojifenye;
@Repository("login")
public class LoginDAOImpl extends SqlSessionDaoSupport implements
		Tallyselectdao {
	@Resource(name = "sqlSessionFactory")
	SqlSessionFactory sqlSessionFactory;

	@PostConstruct
	private void initialize() {
		setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Override
	public T_Employee selectEmployees(String name, String pwd) {
		T_Employee emp = null;
		SqlSession session = super.getSqlSession();
		Tallyselectdao dao = session.getMapper(Tallyselectdao.class);
		return emp = dao.selectEmployees(name, pwd);
	}

	@Override
	public T_Role roleSelect(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T_Employee personSelect(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T_Task taskSelectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T_Plan planSelectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T_Task> taskSelect(int implementorId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T_Plan> planSelect(int taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int PlanInsert(T_Plan plan) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int PlanDelete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int planUpdate(T_Plan plan) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int jihuaCount(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int StatusUpdate(String statu, int renwuId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<gaojifenye> query(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int SelectZongShu(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
