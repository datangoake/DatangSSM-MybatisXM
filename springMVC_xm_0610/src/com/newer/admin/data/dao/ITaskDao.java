package com.newer.admin.data.dao;

public interface ITaskDao {

	
	    // 修改任务表的assigner_id（分配人编号）
		public int updateAssignerId(String sql, Object[] obj);
}
