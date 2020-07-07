package com.newer.admin.data.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.newer.admin.data.dao.ITaskDao;
import com.newer.admin.web.util.DBUtil;
@Component("TaskDaoImpl")
public class TaskDaoImpl implements ITaskDao {
	JdbcTemplate jdbcTemplate = new JdbcTemplate(DBUtil.getDataSource());
	// �޸�������assigner_id�������˱�ţ�
	@Override
	public int updateAssignerId(String sql, Object[] obj) {
		 System.out.println("�������ݲ��޸�������assigner_id");
		return this.jdbcTemplate.update(sql,obj);
	}

}
