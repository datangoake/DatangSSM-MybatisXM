package com.newer.manager.dao;

import java.util.List;

import com.newer.common.entry.T_Employee;

public interface IempDAO {
	//����id����Ա��
	public T_Employee selectEmpById(int empid);
	
	//����id��������Ա��
	public List<T_Employee> selectEmpByRarntId(int rarntid,int pageNo, int pageSize);
}
