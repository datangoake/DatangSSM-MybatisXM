package com.newer.mysm.data.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jms.Session;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.newer.mysm.data.dao.IPlanDAO;
import com.newer.mysm.data.dao.IempDAO;
import com.newer.mysm.data.entity.T_Plan;

@Repository("PlanDAO")
public class PlanDAOImpl extends SqlSessionDaoSupport implements IPlanDAO {
	@Resource(name = "sqlSessionFactory")
	SqlSessionFactory sqlSessionFactory;

	@PostConstruct
	private void initialize() {
		setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public List<T_Plan> selectPlan(int taskId) {
		List<T_Plan> allplan = null;
		SqlSession session = super.getSqlSession();
		IPlanDAO dao = session.getMapper(IPlanDAO.class);
		return allplan = dao.selectPlan(taskId);
	}

	@Override
	public int selectPlanCount(int taskid) {
		int Count = 0;
		SqlSession session = super.getSqlSession();
		IPlanDAO dao = session.getMapper(IPlanDAO.class);
		return Count=dao.selectPlanCount(taskid);
	}

	@Override
	public T_Plan selectPlanById(int planId) {
		T_Plan planuser=null;
		SqlSession session=super.getSqlSession();
		IPlanDAO dao= session.getMapper(IPlanDAO.class);
		return planuser=dao.selectPlanById(planId);
	}

}
