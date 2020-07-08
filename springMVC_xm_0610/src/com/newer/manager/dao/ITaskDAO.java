package com.newer.manager.dao;

import java.util.List;
import java.util.Map;

import com.newer.common.entry.T_Employee;
import com.newer.common.entry.T_Task;

public interface ITaskDAO {
	//查看所有任务
	public List<T_Task> selectAllTask(T_Employee emp,int pageNo, int pageSize,String status);
	
	
	//根据任务id查找对应的任务
	public T_Task selectAllTaskByID(int taskid);
	
	//新增任务
	public int insertTask(T_Task task,int impID,int assId);
	
	//删除任务
	public int deleteTask(int taskid);
	
	//根据任务id修改任务
	public int updateTask(T_Task task,int impid);
	
	//根据id修改任务状态
	public int updateTaskstatus(int empid,String status);
}
