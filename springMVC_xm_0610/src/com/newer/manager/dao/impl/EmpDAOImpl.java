package com.newer.manager.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.newer.common.entry.T_Employee;
import com.newer.manager.dao.IempDAO;
import com.newer.manager.util.DBUtil;

@Component("empdao")
public class EmpDAOImpl implements IempDAO {
	JdbcTemplate temp=new JdbcTemplate(DBUtil.getDataSource());
	@Override
	public T_Employee selectEmpById(int empid) {
		String sql="select * from T_EMPLOYEE where EMPLOYEE_ID=?";
		
		return (T_Employee) this.temp.query(sql, new Object[]{empid}, new RowMapper() {			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Employee emp=new T_Employee();
				emp.setEmployee_name(rs.getString("EMPLOYEE_NAME"));
				emp.setSex(rs.getString("sex"));
				emp.setEnrolldate(rs.getTimestamp("ENROLLDATE"));
				emp.setDuty(rs.getString("duty"));
				emp.setBirthday(rs.getTimestamp("BIRTHDAY"));
				emp.setReal_name(rs.getString("REAL_NAME"));
				emp.setEducation(rs.getString("EDUCATION"));
				emp.setMajor(rs.getString("MAJOR"));
				emp.setExperience(rs.getString("EXPERIENCE"));
				return emp;
			}
		}).get(0);
	}

	//查询手下员工
	@Override
	public List<T_Employee> selectEmpByRarntId(int rarntid,int pageNo, int pageSize) {
		int start = (pageNo -1) * pageSize +1;
		int end = pageNo * pageSize;
		//String sql="select * from T_EMPLOYEE where PARENT_ID=?";
		String sql = "select * from(select row_number() over(order by t.employee_ID) no,t.* from T_EMPLOYEE t where PARENT_ID = ? )temp where temp.no between ? and ?";
		return  this.temp.query(sql, new Object[]{rarntid,start,end}, new RowMapper() {			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Employee emp=new T_Employee();
				emp.setEmployee_ID(rs.getInt("EMPLOYEE_ID"));
				emp.setEmployee_name(rs.getString("EMPLOYEE_NAME"));
				emp.setSex(rs.getString("sex"));
				emp.setEnrolldate(rs.getTimestamp("ENROLLDATE"));
				emp.setDuty(rs.getString("duty"));
				return emp;
			}
		});
	}
	
	public static void main(String[] args) {
		System.out.println(new EmpDAOImpl().selectEmpByRarntId(8,1,2));
	}


}
