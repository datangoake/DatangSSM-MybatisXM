package com.newer.manager.dao;

import java.util.List;

import com.newer.common.entry.T_Plan;

public interface IPlanDAO {
	//根据id查找当前任务的计划
	public List<T_Plan> selectPlan(int planid);
	
	//根据id查找当前任务的计划条数
	public int selectPlanCount(int planid);
	
	//根据单条计划id查找到计划详细信息
	public T_Plan selectPlanById(int planid);
}
