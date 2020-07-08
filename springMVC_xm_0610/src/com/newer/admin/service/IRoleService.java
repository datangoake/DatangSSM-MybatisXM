package com.newer.admin.service;

import java.util.List;

import com.newer.common.entry.T_Role;
/*
 * 叶金豪2020-6-16
 * 
 */
public interface IRoleService {
	  //根据id查询
		public abstract T_Role selectByRoleId(Integer roleId);
	      //查询员工角色
		public abstract List<T_Role> findAllRoles();
}
