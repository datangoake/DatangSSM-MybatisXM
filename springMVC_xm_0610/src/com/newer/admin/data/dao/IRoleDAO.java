package com.newer.admin.data.dao;

import java.util.List;

import com.newer.common.entry.T_Role;





public interface IRoleDAO {
      //����id��ѯ
	public abstract T_Role selectByRoleId(Integer roleId);
      //��ѯԱ����ɫ
	public abstract List<T_Role> findAllRoles();
	
	

}