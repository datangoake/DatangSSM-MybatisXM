package com.newer.mysm.data.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jms.Session;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Repository;

import com.newer.mysm.data.dao.ITaskDAO;
import com.newer.mysm.data.entity.T_Employee;
import com.newer.mysm.data.entity.T_Task;
@Repository("TaskDAO")
public class TaskDAOImpl extends SqlSessionDaoSupport implements ITaskDAO {
	@Resource(name="sqlSessionFactory")
	SqlSessionFactory sqlSessionFactory;
	
	@PostConstruct
	private void initialize() {
		setSqlSessionFactory(sqlSessionFactory);
	}
	//新增一条任务
	@Override
	public int insertTask(@Param("task")T_Task task,int empID,int assignerId) {
		int Count=0;
		try {
		SqlSession session=super.getSqlSession();
		ITaskDAO dao= session.getMapper(ITaskDAO.class);
		Count=dao.insertTask(task, empID, assignerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Count;
	}
	
	//根据编号删除任务
	@Override
	public int deleteTask(int taskid) {
		int Count=0;
		try {
			SqlSession session=super.getSqlSession();
			ITaskDAO dao=session.getMapper(ITaskDAO.class);
			Count=dao.deleteTask(taskid);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Count;
	}
	
	//根据任务id修改任务
	@Override
	public int updateTask(T_Task task, int impid) {
		int Count=0;
		try {
			SqlSession session=super.getSqlSession();
			ITaskDAO dao =session.getMapper(ITaskDAO.class);
			Count=dao.updateTask(task, impid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Count ;
	}

	//根据编号修改任务状态
	public int updateTaskstatus(String status,int taskid) {
		int count=0;
		try {
			SqlSession session=super.getSqlSession();
			ITaskDAO dao=session.getMapper(ITaskDAO.class);
			count=dao.updateTaskstatus(status,taskid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	//selectAllTask查询所有任务
	@Override
	public List<T_Task> selectAllTask(Map<String,Object> map) {
		 SqlSession session=super.getSqlSession();
			ITaskDAO dao= session.getMapper(ITaskDAO.class);
		return dao.selectAllTask(map);
	}

	@Override
	public T_Task selectAllTaskByID(int taskid) {
			T_Task task=null;
		try {
			 SqlSession session=super.getSqlSession();
				ITaskDAO dao= session.getMapper(ITaskDAO.class);
				task=dao.selectAllTaskByID(taskid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return task;
	}


}
