package com.newer.manager.dao;

import java.util.List;
import java.util.Map;

import com.newer.common.entry.T_Employee;
import com.newer.common.entry.T_Task;

public interface ITaskDAO {
	//�鿴��������
	public List<T_Task> selectAllTask(T_Employee emp,int pageNo, int pageSize,String status);
	
	
	//��������id���Ҷ�Ӧ������
	public T_Task selectAllTaskByID(int taskid);
	
	//��������
	public int insertTask(T_Task task,int impID,int assId);
	
	//ɾ������
	public int deleteTask(int taskid);
	
	//��������id�޸�����
	public int updateTask(T_Task task,int impid);
	
	//����id�޸�����״̬
	public int updateTaskstatus(int empid,String status);
}
