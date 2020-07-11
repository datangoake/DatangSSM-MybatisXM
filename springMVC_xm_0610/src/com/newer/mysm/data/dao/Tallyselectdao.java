package com.newer.mysm.data.dao;

import java.util.Date;
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
import org.springframework.format.annotation.DateTimeFormat;

import com.newer.dto.PlanDTO;
import com.newer.mysm.data.entity.T_Plan;
import com.newer.mysm.data.entity.T_Task;
import com.newer.mysm.data.entity.T_Employee;
import com.newer.mysm.data.entity.T_Role;
import com.newer.mysm.data.entity.Tally;
import com.newer.mysm.data.util.TallyDTO;
import com.newer.mysm.data.util.gaojifenye;

public interface Tallyselectdao {
	 
	//查询用户一条记录v
		@Select("select * from t_role where role_id=#{id}")
		@Options(flushCache = false, timeout = 10000,useCache=true)
		@Results({ 
		    @Result(id = true, property="role_ID", column="role_id"),
			@Result(property="role_NAME",column="role_name"),
			@Result(property="role_DESC", column="role_desc")
	})
		public abstract T_Role roleSelect(int id);
		
		@Select("select * from t_employee where employee_id=#{id}")
		@Options(flushCache = false, timeout = 10000,useCache=true)
		@Results({ 
		    @Result(id = true, property="employee_ID", column="employee_id"),
			@Result(property="employee_name",column="employee_name"),
			@Result(property="password", column="password" ),
		    @Result(property="real_name",column="real_name"),
			@Result(property="sex", column="sex" ),
		    @Result(property="birthday",column="birthday"),
			@Result(property="duty", column="duty" ),
		    @Result(property="enrolldate",column="enrolldate"),
			@Result(property="education", column="education" ),
		    @Result(property="major", column="major" ),
		    @Result(property="experience", column="experience" ),
		    @Result(property="role.role_ID", column="role_ID" ),
		    @Result(property="emp.employee_ID", column="parent_id" )
		})
		//v
		public abstract T_Employee personSelect(int id);
		
		@Select("select * from t_task t,T_employee e where  e.employee_ID=implementor_id and t.task_id=#{id}")
		@Options(flushCache = false, timeout = 10000,useCache=true)
		@Results({ 
		    @Result(id = true, property="task_id", column="task_id"),
			@Result(property="task_name",column="task_name"),
			@Result(property="begin_date", column="begin_date" ),
		    @Result(property="end_date",column="end_date"),
			@Result(property="real_begin_date", column="real_begin_date" ),
		    @Result(property="real_end_date",column="real_end_date"),
			@Result(property="status", column="status" ),
		    @Result(property="mp_emp.employee_ID",column="implementor_id"),
		    @Result(property="mp_emp.real_name",column="real_name"),
			@Result(property="as_emp.employee_ID", column="assigner_id" ),
		    @Result(property="task_desc", column="task_desc")
		})
		
		//v
		public abstract T_Task taskSelectById(@Param("id")int id);
		
		@Select("select * from t_plan where plan_id=#{id}")
		@Options(flushCache = false, timeout = 10000,useCache=true)
		@Results({ 
		    @Result(id = true, property="plan_id", column="plan_id"),
			@Result(property="plan_name",column="plan_name"),
			@Result(property="status", column="status" ),
		    @Result(property="is_feedback",column="is_feedback"),
			@Result(property="begin_date", column="begin_date" ),
			@Result(property="end_date",column="end_date"),
		    @Result(property="task.task_id",column="task_id"),
			@Result(property="feedback_info", column="feedback_info" ),
		    @Result(property="plan_desc",column="plan_desc")
		})
		//v
		public abstract T_Plan planSelectById(int id);
		
//v
		@Select("select * from(select row_number() over(order by t.task_id desc) no,t.* from t_task t where implementor_id = #{id} )temp where temp.no between #{pageNo} and #{pageSize}")
		@Results({ 
		    @Result(id = true, property="task_id", column="task_id"),
			@Result(property="task_name",column="task_name"),
			@Result(property="begin_date", column="begin_date" ),
		    @Result(property="end_date",column="end_date"),
			@Result(property="real_begin_date", column="real_begin_date" ),
		    @Result(property="real_end_date",column="real_end_date"),
			@Result(property="status", column="status" ),
		    @Result(property="mp_emp.employee_ID",column="implementor_id"),
			@Result(property="as_emp.employee_ID", column="assigner_id" ),
		    @Result(property="task_desc", column="task_desc")
		})
		public abstract List<T_Task> taskSelect(@Param("id")int implementorId,@Param("pageNo")int pageNo,@Param("pageSize") int pageSize);

		//计划查询根据任务编号v
		@Select("select * from t_plan where task_id=#{id}")
		@Results({ 
		    @Result(id = true, property="plan_id", column="plan_id"),
			@Result(property="plan_name",column="plan_name"),
			@Result(property="status", column="status" ),
		    @Result(property="is_feedback",column="is_feedback"),
			@Result(property="begin_date", column="begin_date" ),
			@Result(property="end_date",column="end_date"),
		    @Result(property="task.task_id",column="task_id"),
			@Result(property="feedback_info", column="feedback_info" ),
		    @Result(property="plan_desc",column="plan_desc")
		})
		public abstract List<T_Plan> planSelect(@Param("id")int taskId);

		//新增员工v
		@Insert("insert into t_plan(plan_name,status,is_feedback,begin_date,end_date,task_id,feedback_info,plan_desc) values(#{plan.plan_name},'未完成','否',#{plan.begin_date},#{plan.end_date},#{plan.task.task_id},null,#{plan.plan_desc})")
		public abstract int PlanInsert(@Param("plan") T_Plan plan);

		//删除计划v
		@Delete("delete from t_plan where plan_id=#{id}")
		public abstract int PlanDelete(@Param("id")int id);

		//修改计划v
		@Update("update t_plan set plan_name=#{plan.plan_name},plan_desc=#{plan.plan_desc},begin_date=#{plan.begin_date},end_date=#{plan.end_date},status=#{plan.status},is_feedback=#{plan.is_feedback},feedback_info=#{plan.feedback_info} where plan_id=#{plan.plan_id}")
		public abstract int planUpdate(@Param("plan")T_Plan plan);

		//查询计划数量v
		@Select("select count(*) from t_plan where task_id = #{id}")
		public abstract int jihuaCount(@Param("id")int id);
		
		//修改任务状态v
		@Update("update t_task set status=#{statu} where task_id=#{id}")
		public abstract int StatusUpdate(@Param("statu")String statu,@Param("id")int renwuId);
		
		//计划组合查询
		//@Select("select * from (select row_number() over(order by p.plan_id) no,p.*,e.employee_id,r.role_id,t.task_name from t_plan p join t_task t on p.task_id=t.task_id join t_employee e on e.employee_id=t.implementor_id join t_role r on r.role_id=e.role_id where 1=1 and employee_id=?")
		@Select("<script>select * from"
	            + "(select row_number() over(order by p.plan_id) no, p.*,e.employee_id,r.role_id,t.task_name from t_plan p join t_task t on p.task_id=t.task_id join t_employee e on e.employee_id=t.implementor_id join t_role r on r.role_id=e.role_id where 1=1 and employee_id=#{id}"
				+"<if test='dto.plan_name != null'>"
				+"and p.plan_name = #{dto.plan_name}"
				+"</if>"
				+"<if test='dto.task_name != null'>"
				+"and t.task_name = #{dto.task_name}"
				+"</if>"
				+"<if test='dto.begin_date_befor != null and dto.begin_date_after != null'>"
				+"and p.begin_date between #{dto.begin_date_befor} and #{dto.begin_date_after}"
				+"</if>"
				+"<if test='dto.end_date_befor != null and dto.end_date_after != null'>"
				+"and p.end_date between #{dto.end_date_befor} and #{dto.end_date_after}"
				+"</if>"
				+"<if test='dto.feedback_info != null'>"
				+"and p.is_feedback = #{dto.feedback_info}"
				+"</if>"
				+") temp"
				+"<if test='startIndex != null'>"
				+"where temp.no>=#{startIndex}"
				+"</if>"
				+"<if test='endIndex != null'>"
				+"and temp.no &lt;=#{endIndex}"
				+"</if>"
				+ "</script>"
				)
		@Options(flushCache = false, timeout = 10000,useCache=true)
		@Results({ 
		    @Result(id = true, property="plan_id", column="plan_id"),
			@Result(property="plan_name",column="plan_name"),
			@Result(property="status", column="status" ),
		    @Result(property="is_feedback",column="is_feedback"),
			@Result(property="begin_date", column="begin_date" ),
			@Result(property="end_date",column="end_date"),
		    @Result(property="task.task_id",column="task_id"),
			@Result(property="feedback_info", column="feedback_info" ),
		    @Result(property="emp.employee_ID",column="employee_id"),
		    @Result(property="role.role_ID",column="role_ID"),
		    @Result(property="task.task_name",column="task_name")
		})
		
		//v
		public abstract List<gaojifenye> query(Map<String, Object>map);
		
		//获取总条数v
		@Select("<script>select count(*) from t_plan p join t_task t on p.task_id=t.task_id join t_employee e on e.employee_id=t.implementor_id join t_role r on r.role_id=e.role_id where 1=1 and employee_id=#{id}"
				+"<if test='dto.plan_name != null'>"
				+"and p.plan_name = #{dto.plan_name}"
				+"</if>"
				+"<if test='dto.task_name != null'>"
				+"and t.task_name = #{dto.task_name}"
				+"</if>"
				+"<if test='dto.begin_date_befor != null and dto.begin_date_after != null'>"
				+"and p.begin_date between #{dto.begin_date_befor} and #{dto.begin_date_after}"
				+"</if>"
				+"<if test='dto.end_date_befor != null and dto.end_date_after != null'>"
				+"and p.end_date between #{dto.end_date_befor} and #{dto.end_date_after}"
				+"</if>"
				+"<if test='dto.feedback_info != null'>"
				+"and p.is_feedback = #{dto.feedback_info}"
				+"</if>"
				+"</script>"
				)
		public abstract int Count(Map<String, Object> map);
		
		//登录查询v
		@Select("select * from t_employee where employee_name=#{name} and password=#{pwd}")
		@Options(flushCache = false, timeout = 10000,useCache=true)
		@Results({ 
		    @Result(id = true, property="employee_ID", column="employee_id"),
			@Result(property="employee_name",column="employee_name"),
			@Result(property="password", column="password" ),
		    @Result(property="real_name",column="real_name"),
			@Result(property="sex", column="sex" ),
		    @Result(property="birthday",column="birthday"),
			@Result(property="duty", column="duty" ),
		    @Result(property="enrolldate",column="enrolldate"),
			@Result(property="education", column="education" ),
		    @Result(property="major", column="major" ),
		    @Result(property="experience", column="experience" ),
		    @Result(property="role.role_ID", column="role_ID" ),
		    @Result(property="emp.employee_ID", column="parent_id" )
		})
		public abstract T_Employee selectEmployees(@Param("name")String name,@Param("pwd")String pwd);
		@Select("select count(*) from t_task where implementor_id = #{id}")
		public abstract int SelectZongShu(@Param("id")int id);
}
