package com.newer.admin.data.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.newer.admin.data.dao.IEmployeeDAO;
import com.newer.admin.web.util.DBUtil;
import com.newer.common.entry.T_Employee;
import com.newer.common.entry.T_Role;

@Component("EmployeeDaoImpl")
public class IEmployeeDaoImpl implements IEmployeeDAO {
   
	JdbcTemplate jdbcTemplate = new JdbcTemplate(DBUtil.getDataSource());
	//全部查询
	@Override
	public List<T_Employee> selectEmployees(String sql, Object[] params) {
		System.out.println("启动数据层:selectEmployees");
		
		return this.jdbcTemplate.query(sql, params,new RowMapper() {
			int employee_ID;
			String employee_name;
			String password;
			String real_name;
			String sex;
			@DateTimeFormat(pattern="yyyy-MM-dd")
			Date birthday;
			String duty;
			@DateTimeFormat(pattern="yyyy-MM-dd")
			Date enrolldate;
			String education;
			String major;
			String experience;
			T_Role role;
			T_Employee emp;
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Employee employee=new T_Employee();
				employee.setEmployee_ID(rs.getInt("employee_ID"));
				employee.setEmployee_name(rs.getString("employee_name"));
				employee.setPassword(rs.getString("password"));
				employee.setReal_name(rs.getString("real_name"));
				employee.setSex(rs.getString("sex"));
				employee.setBirthday(rs.getTimestamp("birthday"));
				employee.setDuty(rs.getString("duty"));
				employee.setEnrolldate(rs.getTimestamp("enrolldate"));
				employee.setEducation(rs.getString("education"));
				employee.setMajor(rs.getString("major"));
				employee.setExperience(rs.getString("experience"));
				
				employee.setRole(new IRoleDaoImpl().selectByRoleId(rs.getInt("role_ID")));
				
				T_Employee employee1=new T_Employee();
				employee1.setEmployee_ID(rs.getInt("employee_ID"));
				employee.setEmp(employee1);
				return employee;
			}
		});
	}


    //添加员工
	@Override
	public int insertEmployee(T_Employee employee) {
		System.out.println("启动数据层员工添加:insertEmployee");
		 	String sql="insert  into T_EMPLOYEE(employee_name, password, real_name, sex, birthday, duty, enrolldate, education, major, experience, role_id) values"+
		"(?,?,?,?,?,?,?,?,?,?,?)";
		
		return this.jdbcTemplate.update(sql,new Object[]{
				employee.getEmployee_name(),
				employee.getPassword(),
				employee.getReal_name(),
				employee.getSex(),
				employee.getBirthday(),
				employee.getDuty(),
				employee.getEnrolldate(),
				employee.getEducation(),
				employee.getMajor(),
				employee.getExperience(),
				employee.getRole().getRole_ID(),
				
				
		});
		
	}
   //按编号删除
	@Override
	public int deleteEmployee(T_Employee employee) {
		System.out.println("启动数据层按编号删除:deleteEmployee");
		return this.jdbcTemplate.update("delete from t_employee where employee_id=?" ,new Object[]{employee.getEmployee_ID()});
	}

   //按编号查询一个员工
	@Override
	public List<T_Employee> findEmployeeById(Integer employeeId) {
		System.out.println("启动数据层按编号查询一个员工:findEmployeeById");
		
		return this.jdbcTemplate.query("select * from t_employee where employee_ID=?", new Object[] {employeeId}, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Employee employee=new T_Employee();
				employee.setEmployee_ID(rs.getInt("employee_ID"));
				employee.setEmployee_name(rs.getString("employee_name"));
				employee.setPassword(rs.getString("password"));
				employee.setReal_name(rs.getString("real_name"));
				employee.setSex(rs.getString("sex"));
				employee.setBirthday(rs.getTimestamp("birthday"));
				employee.setDuty(rs.getString("duty"));
				employee.setEnrolldate(rs.getTimestamp("enrolldate"));
				employee.setEducation(rs.getString("education"));
				employee.setMajor(rs.getString("major"));
				employee.setExperience(rs.getString("experience"));
				
				employee.setRole(new IRoleDaoImpl().selectByRoleId(rs.getInt("role_ID")));
				
				T_Employee employee1=new T_Employee();
				employee1.setEmployee_ID(rs.getInt("employee_ID"));
				employee.setEmp(employee1);
				return employee;
			}
			
			
		});
	}

    //按编号修改
	@Override
	public int modifyEmployee(T_Employee employee) {
		System.out.println("启动数据层按编号修改:modifyEmployee");
		final String employeeName = employee.getEmployee_name();
		final String password = employee.getPassword();
		final String realName = employee.getReal_name();
		final String sex = employee.getSex();
		final Date birthday = employee.getBirthday();
		final String duty = employee.getDuty();
		final Date enrolldate = employee.getEnrolldate();
		final String education = employee.getEducation();
		final String major = employee.getMajor();
		final String experience =employee.getExperience();
		final int roleId = employee.getRole().getRole_ID();
		Integer parentId = null;
		if (employee.getEmp()!=null) {
			parentId=employee.getEmp().getEmployee_ID();
			
		}
		
		return this.jdbcTemplate.update("update t_employee set employee_name=?, password=?, real_name=?, " +
				"sex=?, birthday=?, duty=?, enrolldate=?, education=?,major=?,experience=?,role_id=?,parent_id=? where employee_id=?", new Object[]{employeeName,password,realName,sex,
				birthday,duty,enrolldate,education,major,experience,roleId,parentId,
				employee.getEmployee_ID()});
		
	}

     //分页
	@Override
	public List<T_Employee> selectbypage(String sql, List args) {
		System.out.println("启动数据层分页处理:selectbypage");
		
		return this.jdbcTemplate.query(sql, args.toArray(), new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Employee employee=new T_Employee();
				employee.setEmployee_ID(rs.getInt("employee_ID"));
				employee.setEmployee_name(rs.getString("employee_name"));
				employee.setPassword(rs.getString("password"));
				employee.setReal_name(rs.getString("real_name"));
				employee.setSex(rs.getString("sex"));
				employee.setBirthday(rs.getTimestamp("birthday"));
				employee.setDuty(rs.getString("duty"));
				employee.setEnrolldate(rs.getTimestamp("enrolldate"));
				employee.setEducation(rs.getString("education"));
				employee.setMajor(rs.getString("major"));
				employee.setExperience(rs.getString("experience"));
				
				employee.setRole(new IRoleDaoImpl().selectByRoleId(rs.getInt("role_ID")));
				
				T_Employee employee1=new T_Employee();
				employee1.setEmployee_ID(rs.getInt("employee_ID"));
				employee.setEmp(employee1);
				return employee;
			}
			
			
		});
	}

    //获取总条数
	@Override
	public int getTotalCounts(String sql) {
		System.out.println("启动数据层获取总条数");
		
		return this.jdbcTemplate.queryForInt(sql,null);
	}


	@Override
	public int modifSuperior(T_Employee id) {
		System.out.println("启动数据层修改上级");
		int i=0;
		try {
			i=jdbcTemplate.update("update t_employee set parent_id=null where employee_id=?",new Object[]{id});
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return i;
	}

	// 删除员工第二种方法
	@Override
	public int deleteEmployee(String sql, Object[] obj, Integer empId) {
	    
		return this.jdbcTemplate.update(sql, obj);
	}


	@Override
	public int updateEmployeeParent(String sql, Object[] obj) {
		System.out.println("启动数据层删除主管修改下级");
		
		return this.jdbcTemplate.update(sql, obj);
	}
	
}
