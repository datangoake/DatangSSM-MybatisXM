package com.newer.admin.data.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface ITaskDao {

	
	    // �޸�������assigner_id�������˱�ţ�

		@Update("update t_task set assigner_id=null where assigner_Id=#{id}")
		public int updateAssignerId(@Param("id")Integer id);
}


