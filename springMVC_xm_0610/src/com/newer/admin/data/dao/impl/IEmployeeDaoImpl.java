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
	//ȫ����ѯ
	@Override
	public List<T_Employee> selectEmployees(String sql, Object[] params) {
		System.out.println("�������ݲ�:selectEmployees");
		
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


    //���Ա��
	@Override
	public int insertEmployee(T_Employee employee) {
		System.out.println("�������ݲ�Ա�����:insertEmployee");
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
   //�����ɾ��
	@Override
	public int deleteEmployee(T_Employee employee) {
		System.out.println("�������ݲ㰴���ɾ��:deleteEmployee");
		return this.jdbcTemplate.update("delete from t_employee where employee_id=?" ,new Object[]{employee.getEmployee_ID()});
	}

   //����Ų�ѯһ��Ա��
	@Override
	public List<T_Employee> findEmployeeById(Integer employeeId) {
		System.out.println("�������ݲ㰴��Ų�ѯһ��Ա��:findEmployeeById");
		
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

    //������޸�
	@Override
	public int modifyEmployee(T_Employee employee) {
		System.out.println("�������ݲ㰴����޸�:modifyEmployee");
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

     //��ҳ
	@Override
	public List<T_Employee> selectbypage(String sql, List args) {
		System.out.println("�������ݲ��ҳ����:selectbypage");
		
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

    //��ȡ������
	@Override
	public int getTotalCounts(String sql) {
		System.out.println("�������ݲ��ȡ������");
		
		return this.jdbcTemplate.queryForInt(sql,null);
	}


	@Override
	public int modifSuperior(T_Employee id) {
		System.out.println("�������ݲ��޸��ϼ�");
		int i=0;
		try {
			i=jdbcTemplate.update("update t_employee set parent_id=null where employee_id=?",new Object[]{id});
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return i;
	}

	// ɾ��Ա���ڶ��ַ���
	@Override
	public int deleteEmployee(String sql, Object[] obj, Integer empId) {
	    
		return this.jdbcTemplate.update(sql, obj);
	}


	@Override
	public int updateEmployeeParent(String sql, Object[] obj) {
		System.out.println("�������ݲ�ɾ�������޸��¼�");
		
		return this.jdbcTemplate.update(sql, obj);
	}
	
}
