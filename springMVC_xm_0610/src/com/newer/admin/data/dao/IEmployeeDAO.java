package com.newer.admin.data.dao;

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

import com.newer.common.entry.T_Employee;
import com.newer.common.entry.T_Role;



public interface IEmployeeDAO {
	
	
	//根据用户编号查询角色名
	@Select("select r.role_id,r.role_name,r.role_desc from T_EMPLOYEE e, T_role r where e.role_id=r.role_id and e.employee_id=#{id}")
	public T_Role getroleinfo(@Param("id")Integer id);
	
	//查询表数据
		//
			//孙海俊     已完成
		    //查询所有员工(分页)1  ok v
			@Select("select * from(select row_number() over(order by employee_ID desc) no, e.* from T_EMPLOYEE e) temp where temp.no between #{startIndex} and #{endIndex}")
			@Options(flushCache = false, timeout = 10000,useCache=true)
			@Results({
				@Result(id=true,property="employee_ID",column="employee_ID"),
				@Result(property="employee_name" , column="employee_name"),
				@Result(property="real_name" , column="real_name"),
				@Result(property="sex" , column="sex"),
				@Result(property="birthday" , column="birthday"),
				@Result(property="enrolldate" , column="enrolldate"),
				@Result(property="education" , column="education"),
				@Result(property="major" , column="major"),
				@Result(property="experience" , column="experience"),
				@Result(property="role.role_ID" , column="role_ID"),
				@Result(property="role.role_NAME" , column="role_NAME"),
				@Result(property="role.role_DESC" , column="role_DESC"),
				@Result(property="emp.employee_ID" , column="parent_id")
			})
			public abstract List<T_Employee> selectbypage(Map<String,Object> param);
			
			//分页 查询  查询所有非管理员员工（员工管理）v
			@Select("select * from (select row_number() over(order by employee_id desc) no, temp2.* from " +
						"(select * from T_EMPLOYEE where ROLE_ID <> 2) temp2 )temp where temp.no between #{startIndex} and #{endIndex}")
			@Options(flushCache = false, timeout = 10000,useCache=true)
			@Results({
				@Result(id=true,property="employee_ID",column="employee_ID"),
				@Result(property="employee_name" , column="employee_name"),
				@Result(property="real_name" , column="real_name"),
				@Result(property="sex" , column="sex"),
				@Result(property="birthday" , column="birthday"),
				@Result(property="enrolldate" , column="enrolldate"),
				@Result(property="education" , column="education"),
				@Result(property="major" , column="major"),
				@Result(property="experience" , column="experience"),
				@Result(property="role.role_ID" , column="role_ID"),
				@Result(property="role.role_NAME" , column="role_NAME"),
				@Result(property="role.role_DESC" , column="role_DESC"),
				@Result(property="emp.employee_ID" , column="parent_id")
			})
			public abstract List<T_Employee> querybypage(Map<String,Object> param);
			
			
			//查询所有角色为员工的信息（分配人员）v
			@Select("select * from (select row_number() over(order by employee_id desc) no, temp2.* from "
+"(select * from T_EMPLOYEE where ROLE_ID =4) temp2 )temp where temp.no between #{startIndex} and #{endIndex}")
			@Options(flushCache = false, timeout = 10000,useCache=true)
			@Results({
				@Result(id=true,property="employee_ID",column="employee_ID"),
				@Result(property="employee_name" , column="employee_name"),
				@Result(property="real_name" , column="real_name"),
				@Result(property="sex" , column="sex"),
				@Result(property="birthday" , column="birthday"),
				@Result(property="enrolldate" , column="enrolldate"),
				@Result(property="education" , column="education"),
				@Result(property="major" , column="major"),
				@Result(property="experience" , column="experience"),
				@Result(property="role.role_ID" , column="role_ID"),
				@Result(property="role.role_NAME" , column="role_NAME"),
				@Result(property="role.role_DESC" , column="role_DESC"),
				@Result(property="emp.employee_ID" , column="parent_id")
			})
			public abstract List<T_Employee> querybyemp(Map<String,Object> param);
			
			
			//已完成
		    //总数(查询所有(分页)1)   ok
			@Select("select count(*) from T_EMPLOYEE")
			public abstract int getTotalCounts1();
			//不为管理员（总数）
			@Select("select count(*) from T_EMPLOYEE where ROLE_ID <> 2")
			public abstract int getTotalCounts2();
			//角色为员工（总数）
			@Select("select count(*) from T_EMPLOYEE where ROLE_ID =4")
			public abstract int getTotalCounts3();

			
			
			
			
			
			
			
			
			

		
			//已完成
			//按编号查询一个员工
			@Select("select * from T_EMPLOYEE where employee_ID=#{employeeId}")
			@Options(flushCache = false, timeout = 10000,useCache=true)
			@Results({
				@Result(id=true,property="employee_ID",column="employee_ID"),
				@Result(property="employee_name" , column="employee_name"),
				@Result(property="real_name" , column="real_name"),
				@Result(property="sex" , column="sex"),
				@Result(property="birthday" , column="birthday"),
				@Result(property="enrolldate" , column="enrolldate"),
				@Result(property="education" , column="education"),
				@Result(property="major" , column="major"),
				@Result(property="experience" , column="experience"),
				@Result(property="role.role_ID" , column="role_ID"),
				@Result(property="role.role_NAME" , column="role_NAME"),
				@Result(property="role.role_DESC" , column="role_DESC"),
				@Result(property="emp.employee_ID" , column="parent_id")
			})
			public abstract List<T_Employee> findEmployeeById(@Param("employeeId") Integer employeeId);
			
			
	//修改表数据
	
	//孙海俊  已完成
	//添加员工
	@Insert("insert into T_EMPLOYEE(employee_name, password, real_name, sex, birthday, duty, enrolldate, education, major, experience, role_id) values"+
	"(#{employee.employee_name},#{employee.password},#{employee.real_name},#{employee.sex},#{employee.birthday},#{employee.duty},#{employee.enrolldate},#{employee.education},#{employee.major},#{employee.experience},#{employee.role.role_ID})")
    public abstract int insertEmployee(@Param("employee")T_Employee employee);		
		//已完成
	  //按编号删除一个员工
	@Delete("delete from t_employee where employee_id=#{id}")
    public abstract int deleteEmployee(@Param("id")Integer id);
	
	
	
	
	//孙海俊  已完成
////按编号修改一个员工
@Update("update t_employee set employee_name=#{employee.employee_name,jdbcType=VARCHAR},"
		+ "password=#{employee.password,jdbcType=VARCHAR},"
		+ " real_name=#{employee.real_name,jdbcType=VARCHAR}, " +
		"sex=#{employee.sex,jdbcType=VARCHAR}, "
		+ "birthday=#{employee.birthday,jdbcType=DATE},"
		+ " duty=#{employee.duty,jdbcType=VARCHAR},"
		+ " enrolldate=#{employee.enrolldate,jdbcType=DATE}, "
		+ "education=#{employee.education,jdbcType=VARCHAR},"
		+ "major=#{employee.major,jdbcType=VARCHAR},"
		+ "experience=#{employee.experience,jdbcType=VARCHAR},"
		+ "role_id=#{employee.role.role_ID,jdbcType=SMALLINT},"
		+ "parent_id=#{employee.emp.employee_ID,jdbcType=SMALLINT}"
		+ "where employee_id=#{employee.employee_ID,jdbcType=SMALLINT}")
public abstract int modifyEmployee(@Param("employee")T_Employee employee);

	//孙海俊  已完成
	//查询所有主管 
		@Select("select * from t_employee where ROLE_ID=3")
		@Options(flushCache = false, timeout = 10000,useCache=true)
		@Results({
			@Result(id=true,property="employee_ID",column="employee_ID"),
			@Result(property="employee_name" , column="employee_name"),
			@Result(property="real_name" , column="real_name"),
			@Result(property="sex" , column="sex"),
			@Result(property="birthday" , column="birthday"),
			@Result(property="enrolldate" , column="enrolldate"),
			@Result(property="education" , column="education"),
			@Result(property="major" , column="major"),
			@Result(property="experience" , column="experience"),
			@Result(property="role.role_ID" , column="role_ID"),
			@Result(property="emp.employee_ID" , column="parent_id")
		})
		public List<T_Employee> queryAllManager();
		
		
		
		
	//修改员工上级信息 已完成
	@Update("update t_employee set parent_id=null where employee_id=#{id}")
	public int modifSuperior(@Param("id")Integer id);
		

	
		
				
		// 删除主管修改下级
		@Delete("update t_employee set parent_id=null where employee_Id in (select employee_Id from t_employee where parent_Id=#{id})")
		int updateEmployeeParent(@Param("id")Integer id);
		
		@Select("select * from t_employee where employee_name=#{emp.employee_name} and password=#{emp.password}")
		public T_Employee checkLogin(@Param("emp")T_Employee emp);
		 
}
