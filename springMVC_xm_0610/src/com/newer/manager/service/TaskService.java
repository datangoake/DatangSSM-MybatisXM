package com.newer.manager.service;

import java.util.List;

import com.newer.common.entry.T_Employee;
import com.newer.common.entry.T_Plan;
import com.newer.common.entry.T_Task;

public interface TaskService {
	
	//��ѯ��������
	public List<T_Task> allTask(T_Employee emp,int pageNo, int pageSize,String status);
	
	//��id�鵥������
	public T_Task selectAllTaskByID(int taskid);
	
	//���������id�鵽�������������ļƻ�
	public List<T_Plan> selectPlan(int taskid);
	
	//���ݵ����ƻ�id���ҵ��ƻ���ϸ��Ϣ
	public T_Plan selectPlanById(int planid);
	
	//��ѯ����Ա������������
	public List<T_Employee> selectEmpByRarntId(int rarntid,int pageNo, int pageSize);
	
	//��������
	public int insertTask(T_Task task,int impID,int assId);
	
	//ɾ������
	public int deleteTask(int taskid);
	
	//������޸�����
	public int updateTask(T_Task task,int impid);
	
	//����Ų�ѯ��Ա��ϸ��Ϣ
	public T_Employee selectEmpById(int empid);
	
	//�޸�����״̬
	public int updateTaskstatus(int empid, String status);
}
