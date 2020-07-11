package com.newer.mysm.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.testng.annotations.Optional;

import com.newer.mysm.data.entity.T_Plan;

public interface IPlanDAO {
	@Select("select count(*) from T_PLAN where task_id=#{taskid}")
	//����Id���ҵ�ǰ����ļƻ�����
	public int selectPlanCount(int taskid);
	
	
	@Select("select * from T_PLAN where PLAN_ID=#{planId}")
	@Options(flushCache=false,timeout=10000,useCache=true)
	@Results({
		@Result(id=true,property="plan_id",column="PLAN_ID"),
		@Result(property="plan_name",column="PLAN_NAME"),
		@Result(property="status",column="STATUS"),
		@Result(property="is_feedback",column="IS_FEEDBACK"),
		@Result(property="begin_date",column="BEGIN_DATE"),
		@Result(property="end_date",column="END_DATE"),
		@Result(property="task.task_id",column="TASK_ID"),
		@Result(property="feedback_info",column="FEEDBACK_INFO"),
		@Result(property="plan_desc",column="PLAN_DESC")
	})
	//���ݵ����ƻ�Id���ҵ��ƻ���ϸ��Ϣ
	public T_Plan selectPlanById(int planId);
	
	
	
	//����task_id��ѯ���мƻ�
	@Select("select * from T_PLAN where task_id=#{taskid}")
	@Options(flushCache=false,timeout=10000,useCache=true)
	@Results({
		@Result(id=true,property="plan_id",column="PLAN_ID"),
		@Result(property="plan_name",column="PLAN_NAME"),
		@Result(property="status",column="STATUS"),
		@Result(property="is_feedback",column="IS_FEEDBACK"),
		@Result(property="begin_date",column="BEGIN_DATE"),
		@Result(property="end_date",column="END_DATE"),
		@Result(property="task.task_id",column="TASK_ID"),
		@Result(property="feedback_info",column="FEEDBACK_INFO"),
		@Result(property="plan_desc",column="PLAN_DESC")
	})
	public List<T_Plan> selectPlan(@Param("taskid")int taskId);
	
	

	
}
