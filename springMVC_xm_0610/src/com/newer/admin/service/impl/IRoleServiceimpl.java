package com.newer.admin.service.impl;
/*
 * Ò¶½ðºÀ2020-6-16
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newer.admin.data.dao.IRoleDAO;
import com.newer.admin.service.IRoleService;
import com.newer.common.entry.T_Role;
@Service("IRoleServiceimpl")
public class IRoleServiceimpl implements IRoleService {
	@Resource(name="IRoleDaoImpl")
	IRoleDAO dao;
	
	@Override
	public T_Role selectByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return this.dao.selectByRoleId(roleId);
	}
    
	@Override
	public List<T_Role> findAllRoles() {
		// TODO Auto-generated method stub
		return this.dao.findAllRoles();
	}

}
