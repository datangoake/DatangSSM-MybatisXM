package com.newer.mysm.data.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.TTCCLayout;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.newer.dto.PlanDTO;
import com.newer.mysm.data.dao.Tallyselectdao;
import com.newer.mysm.data.entity.T_Employee;
import com.newer.mysm.data.entity.T_Plan;
import com.newer.mysm.data.entity.T_Role;
import com.newer.mysm.data.entity.T_Task;
import com.newer.mysm.data.entity.Tally;
import com.newer.mysm.data.util.TallyDTO;
import com.newer.mysm.data.util.gaojifenye;



@Repository("talldao")
public class Tallyselectdaoimpl extends SqlSessionDaoSupport implements Tallyselectdao {
	
	
	@Resource(name="sqlSessionFactory")
	SqlSessionFactory factory;

	@PostConstruct
    private void  initialize() {
        setSqlSessionFactory(factory);
    }

	@Override
//	public List<Tally> selectall(Map<String,Object> parme) {
//		List<Tally> list=null;
//		System.out.println(121212563);
//		
//		SqlSession session=super.getSqlSession();
//		Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
//		list=dao.selectall(parme);
//		return list;
//	}
//
//	@Override
//	public int getCount(TallyDTO tally) {
//		// TODO Auto-generated method stub
//		return 0;
//	}


	public T_Role roleSelect(@Param("id") int id) {
		T_Role role=new T_Role();
		SqlSession session=super.getSqlSession();
		Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
		return role=dao.roleSelect(id);
	}

	@Override
	public T_Employee personSelect(@Param("id") int id) {
		T_Employee employee=new T_Employee();
		SqlSession session=super.getSqlSession();
		Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
		return  employee= dao.personSelect(id);
		
	}

	@Override
	public T_Task taskSelectById(@Param("id") int id) {
		T_Task task=new T_Task();
		SqlSession session=super.getSqlSession();
		Tallyselectdao dao	=session.getMapper(Tallyselectdao.class);
		return task=dao.taskSelectById(id);
	}

	@Override
	public T_Plan planSelectById(@Param("id") int id) {
	
		SqlSession session=super.getSqlSession();
		Tallyselectdao dao	=session.getMapper(Tallyselectdao.class);
		
		return dao.planSelectById(id);
	}

	@Override
	public List<T_Task> taskSelect(int implementorId, int pageNo, int pageSize) {
		List<T_Task> list=null;
		SqlSession session=super.getSqlSession();
		Tallyselectdao dao	=session.getMapper(Tallyselectdao.class);
		list=dao.taskSelect(implementorId, pageNo, pageSize);
		
		for (T_Task t_Task : list) {
			T_Employee employee=t_Task.getAs_emp();
			employee=(T_Employee)dao.personSelect(t_Task.getAs_emp().getEmployee_ID());
			System.out.println("****************"+t_Task.getTask_id());
			t_Task.setAs_emp(employee);
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+t_Task.getTask_id());
			int count=dao.jihuaCount(t_Task.getTask_id());
			System.out.println("aaaaaaaaaa"+count);
			t_Task.setTask_desc(String.valueOf(count));
		}
		return list;
	}

	@Override
	public List<T_Plan> planSelect(int taskId) {
		List<T_Plan> list=null;
		SqlSession session= super.getSqlSession();
		   Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
		   list=dao.planSelect(taskId);
		   for (T_Plan t_Plan : list) {
			T_Employee employee=t_Plan.getTask().getMp_emp();
			
		}
		return list;
	}

	@Override
	public int PlanInsert(@Param("plan") T_Plan plan) {
	   SqlSession session= super.getSqlSession();
	   Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
	   int count=dao.PlanInsert(plan);
		return count;
	}

	@Override
	public int PlanDelete(@Param("id")int id) {
		SqlSession session= super.getSqlSession();
		   Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
		int flage=dao.PlanDelete(id);
		   
		   return flage;
	}

	@Override
	public int planUpdate(T_Plan plan) {
		SqlSession session= super.getSqlSession();
		   Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
		int flage=dao.planUpdate(plan);
		return flage;
	}

	@Override
	public int jihuaCount(int rnwuId) {
		SqlSession session= super.getSqlSession();
		   Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
		int flage=dao.jihuaCount(rnwuId);
		return flage;
	}

	@Override
	public int StatusUpdate(String statu, int renwuId) {
		SqlSession session= super.getSqlSession();
		   Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
		int flage=dao.StatusUpdate(statu, renwuId);
		return flage;
	}

	@Override
	public List<gaojifenye> query(Map<String, Object>map) {
		List<gaojifenye> list=null;
		SqlSession session= super.getSqlSession();
		   Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
		   list=dao.query(map);
		   System.out.println(map.toString());
		   System.out.println(list.toString());
		return list;
	}

	@Override
	public int Count(Map<String, Object> map) {
		SqlSession session= super.getSqlSession();
		   Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
		   System.out.println(map.toString());
		 int a=  dao.Count(map);
		
		return a;
	}

	@Override
	public T_Employee selectEmployees(@Param("name")String name,@Param("pwd")String pwd) {
		
		SqlSession session= super.getSqlSession();
		   Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
		    
		return dao.selectEmployees(name, pwd);
	}

	@Override
	public int SelectZongShu(int id) {
		SqlSession session= super.getSqlSession();
		   Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
		   int a=dao.SelectZongShu(id);
		return a;
	}

	
	

}
