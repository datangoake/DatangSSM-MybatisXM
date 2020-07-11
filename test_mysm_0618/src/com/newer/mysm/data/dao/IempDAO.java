package com.newer.mysm.data.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.format.annotation.DateTimeFormat;

import com.newer.mysm.data.entity.T_Employee;
import com.newer.mysm.data.entity.T_Plan;
import com.newer.mysm.data.entity.T_Role;
import com.newer.mysm.data.entity.Tally;
import com.newer.mysm.data.util.TallyDTO;

public interface IempDAO {

	//根据编号查找用户
	@Select("select * from T_EMPLOYEE where employee_id=#{EMPLOYEE_ID}")
	@Options(flushCache=false,timeout=10000,useCache=true)
	@Results({
		@Result(id=true,property="employee_ID",column="EMPLOYEE_ID"),
		@Result(property="employee_name",column="EMPLOYEE_NAME"),
		@Result(property="sex",column="SEX"),
		@Result(property="duty",column="DUTY"),
		@Result(property="enrolldate",column="ENROLLDATE"),
		@Result(property="birthday",column="BIRTHDAY"),
		@Result(property="real_name",column="REAL_NAME"),
		@Result(property="major",column="MAJOR"),
		@Result(property="experience",column="EXPERIENCE"),
		@Result(property="role.role_ID",column="ROLE_ID"),
		@Result(property="emp.employee_ID",column="PARENT_ID")
	})
	public T_Employee selectEmpById(@Param("EMPLOYEE_ID")int empid);
	
	
	//根据编号查询下属员工
	@Select("select * from(select row_number() over(order by t.employee_ID) no,t.* from T_EMPLOYEE t where PARENT_ID =#{id} )temp where temp.no between #{pageNo} and #{pageSize}")
	@Options(flushCache=false,timeout=10000,useCache=true)
	@Results({
		@Result(id=true,property="employee_ID",column="EMPLOYEE_ID"),
		@Result(id=true,property="employee_name",column="EMPLOYEE_NAME"),
		@Result(id=true,property="sex",column="sex"),
		@Result(id=true,property="enrolldate",column="ENROLLDATE"),
		@Result(id=true,property="duty",column="duty")
	})
	public List<T_Employee> selectEmpByRarntId(@Param("id")int rarntid,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	

	

}
