package com.newer.manager.dao;

import java.util.List;

import com.newer.common.entry.T_Plan;

public interface IPlanDAO {
	//����id���ҵ�ǰ����ļƻ�
	public List<T_Plan> selectPlan(int planid);
	
	//����id���ҵ�ǰ����ļƻ�����
	public int selectPlanCount(int planid);
	
	//���ݵ����ƻ�id���ҵ��ƻ���ϸ��Ϣ
	public T_Plan selectPlanById(int planid);
}
