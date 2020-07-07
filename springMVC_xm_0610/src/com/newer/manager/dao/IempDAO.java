package com.newer.manager.dao;

import java.util.List;

import com.newer.common.entry.T_Employee;

public interface IempDAO {
	//根据id查找员工
	public T_Employee selectEmpById(int empid);
	
	//根据id查找手下员工
	public List<T_Employee> selectEmpByRarntId(int rarntid,int pageNo, int pageSize);
}
