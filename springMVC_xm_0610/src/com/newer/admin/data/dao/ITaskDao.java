package com.newer.admin.data.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface ITaskDao {

	
	    // 修改任务表的assigner_id（分配人编号）

		@Update("update t_task set assigner_id=null where assigner_Id=#{id}")
		public int updateAssignerId(@Param("id")Integer id);
}


