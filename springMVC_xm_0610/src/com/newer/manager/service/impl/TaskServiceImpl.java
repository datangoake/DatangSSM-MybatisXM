package com.newer.manager.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newer.common.entry.T_Employee;
import com.newer.common.entry.T_Plan;
import com.newer.common.entry.T_Task;
import com.newer.manager.dao.IPlanDAO;
import com.newer.manager.dao.ITaskDAO;
import com.newer.manager.dao.IempDAO;
import com.newer.manager.service.TaskService;
@Service("taskservice")
public class TaskServiceImpl implements TaskService {

	@Resource(name="taskdao")
	ITaskDAO taskdao;
	@Resource(name="plandao")
	IPlanDAO plandao;
	@Resource(name="empdao")
	IempDAO empdao;
	@Override
	public List<T_Task> allTask(T_Employee emp,int pageNo, int pageSize,String status) {
		return taskdao.selectAllTask(emp,pageNo, pageSize, status);
	}
	@Override
	public T_Task selectAllTaskByID(int taskid) {
		return taskdao.selectAllTaskByID(taskid);
	}
	@Override
	public List<T_Plan> selectPlan(int taskid) {		
		return plandao.selectPlan(taskid);
	}
	@Override
	public T_Plan selectPlanById(int planid) {		
		return plandao.selectPlanById(planid);
	}
	@Override
	public List<T_Employee> selectEmpByRarntId(int rarntid,int pageNo, int pageSize) {
		return empdao.selectEmpByRarntId(rarntid, pageNo, pageSize);
	}
	@Override
	public int insertTask(T_Task task, int impID, int assId) {		
		return taskdao.insertTask(task, impID, assId);
	}
	@Override
	public int deleteTask(int taskid) {
		return this.taskdao.deleteTask(taskid);
	}
	@Override
	public int updateTask(T_Task task, int impid) {
		return this.taskdao.updateTask(task, impid);
	}
	@Override
	public T_Employee selectEmpById(int empid) {		
		return this.empdao.selectEmpById(empid);
	}
	@Override
	public int updateTaskstatus(int empid, String status) {		
		return this.taskdao.updateTaskstatus(empid, status);
	}	
}
