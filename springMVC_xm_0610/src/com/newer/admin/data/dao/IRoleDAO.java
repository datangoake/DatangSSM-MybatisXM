package com.newer.admin.data.dao;

import java.util.List;

import com.newer.common.entry.T_Role;





public interface IRoleDAO {
      //根据id查询
	public abstract T_Role selectByRoleId(Integer roleId);
      //查询员工角色
	public abstract List<T_Role> findAllRoles();
	
	

}