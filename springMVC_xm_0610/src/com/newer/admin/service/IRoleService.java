package com.newer.admin.service;

import java.util.List;

import com.newer.common.entry.T_Role;
/*
 * Ҷ���2020-6-16
 * 
 */
public interface IRoleService {
	  //����id��ѯ
		public abstract T_Role selectByRoleId(Integer roleId);
	      //��ѯԱ����ɫ
		public abstract List<T_Role> findAllRoles();
}
