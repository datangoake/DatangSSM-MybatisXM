package com.newer.admin.data.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.newer.admin.data.dao.ITaskDao;
import com.newer.admin.web.util.DBUtil;
@Component("TaskDaoImpl")
public class TaskDaoImpl implements ITaskDao {
	JdbcTemplate jdbcTemplate = new JdbcTemplate(DBUtil.getDataSource());
	// 修改任务表的assigner_id（分配人编号）
	@Override
	public int updateAssignerId(String sql, Object[] obj) {
		 System.out.println("启动数据层修改任务表的assigner_id");
		return this.jdbcTemplate.update(sql,obj);
	}

}
