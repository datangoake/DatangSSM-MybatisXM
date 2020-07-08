package com.newer.manager.service;

import java.util.List;

import com.newer.common.entry.T_Employee;
import com.newer.common.entry.T_Plan;
import com.newer.common.entry.T_Task;

public interface TaskService {
	
	//查询所有任务
	public List<T_Task> allTask(T_Employee emp,int pageNo, int pageSize,String status);
	
	//按id查单个任务
	public T_Task selectAllTaskByID(int taskid);
	
	//根据任务的id查到它下面所创建的计划
	public List<T_Plan> selectPlan(int taskid);
	
	//根据单条计划id查找到计划详细信息
	public T_Plan selectPlanById(int planid);
	
	//查询手下员工放入下拉框
	public List<T_Employee> selectEmpByRarntId(int rarntid,int pageNo, int pageSize);
	
	//发布任务
	public int insertTask(T_Task task,int impID,int assId);
	
	//删除任务
	public int deleteTask(int taskid);
	
	//按编号修改任务
	public int updateTask(T_Task task,int impid);
	
	//按编号查询人员详细信息
	public T_Employee selectEmpById(int empid);
	
	//修改任务状态
	public int updateTaskstatus(int empid, String status);
}
