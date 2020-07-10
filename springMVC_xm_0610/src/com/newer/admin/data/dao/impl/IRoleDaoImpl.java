package com.newer.admin.data.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.management.relation.Role;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.newer.admin.data.dao.IRoleDAO;
import com.newer.admin.web.util.DBUtil;
import com.newer.common.entry.T_Role;

@Component("IRoleDaoImpl")
public class IRoleDaoImpl implements IRoleDAO {
	JdbcTemplate jdbcTemplate = new JdbcTemplate(DBUtil.getDataSource());
	
	
	//���ݱ�Ų�ѯ
	@Override
	public T_Role selectByRoleId(Integer roleId) {
		System.out.println("�������ݲ�:selectByRoleId");
		String sql="select * from t_role where role_id=?";
		return  (T_Role) this.jdbcTemplate.queryForObject(sql, new Object[]{roleId}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Role role = new T_Role();
			    role.setRole_ID(rs.getInt("role_ID"));
			    role.setRole_NAME(rs.getString("role_NAME"));
			    role.setRole_DESC(rs.getString("role_DESC"));
				return role;
			}
		});
	}

   //ȫ����ѯ
	@Override
	public List<T_Role> findAllRoles() {
		System.out.println("�������ݲ��ѯ��ɫ:findAllRoles");
		String sql="select * from t_role";
		return  this.jdbcTemplate.query(sql, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Role role = new T_Role();
			    role.setRole_ID(rs.getInt("role_ID"));
			    role.setRole_NAME(rs.getString("role_NAME"));
			    role.setRole_DESC(rs.getString("role_DESC"));
				return role;
			}
		
		});
	}
	
}
