package com.newer.mysm.data.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.mysm.data.entity.T_Employee;
import com.newer.mysm.data.entity.T_Task;

public interface ITaskDAO {
	@Insert("insert into T_TASK(TASK_NAME,BEGIN_DATE,END_DATE,STATUS,IMPLEMENTOR_ID,ASSIGNER_ID,TASK_DESC) "
			+ "values(#{task.task_name},#{task.begin_date},#{task.end_date},'未实施',#{task.mp_emp.employee_ID},#{task.as_emp.employee_ID},#{task.task_desc})")
	//新增一条任务
	public int insertTask(@Param("task")T_Task task,int empID,int assignerId);
	
	@Delete("delete from T_TASK where task_id=#{taskid}")
	//删除任务
	public int deleteTask(@Param("taskid")int taskid);

	@Update("update T_TASK set task_name=#{task.task_name},begin_date=#{task.begin_date},end_date=#{task.end_date},"
			+ "implementor_id=#{impid},task_desc=#{task.task_desc} where task_id=#{task.task_id}")
	//根据任务id修改任务
	public int updateTask(@Param("task")T_Task task,@Param("impid")int impid);
	
	@Update("update T_TASK set STATUS=#{status} where task_id=#{taskid}")
	//根据id修改任务状态
	public int updateTaskstatus(@Param("status")String status,@Param("taskid")int taskid);
	
	
	@Select("select * from T_TASK where TASK_ID=#{taskid}")
	@Options(flushCache=false,timeout=10000,useCache=true)
	@Results({
		@Result(id=true,property="task_id",column="TASK_ID"),
		@Result(property="task_name",column="TASK_NAME"),
		@Result(property="begin_date",column="BEGIN_DATE"),
		@Result(property="end_date",column="END_DATE"),
		@Result(property="real_begin_date",column="REAL_BEGIN_DATE_DATE"),
		@Result(property="real_end_date",column="REAL_END_DATE_DATE"),
		@Result(property="status",column="STATUS"),
		@Result(property="mp_emp.employee_ID",column="IMPLEMENTOR_ID"),
		@Result(property="as_emp.employee_ID",column="ASSIGNER_ID"),
		@Result(property="task_desc",column="TASK_DESC")
	})
	//根据任务id查找对应的任务
	public T_Task selectAllTaskByID(@Param("taskid")int taskid);
	
	
	//查看所有任务
	@Select("select * from(select row_number() over(order by t.task_id desc) no,t.* from t_task t where ASSIGNER_ID = #{emp.employee_ID})temp where temp.no between #{startIndex} and #{endIndex}")
	public List<T_Task> selectAllTask(Map<String,Object> map);
	
}
