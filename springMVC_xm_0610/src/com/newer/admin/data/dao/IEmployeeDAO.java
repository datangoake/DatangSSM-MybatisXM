package com.newer.admin.data.dao;

import java.util.List;

import com.newer.common.entry.T_Employee;



public interface IEmployeeDAO {
	    //全部查询
		public abstract List<T_Employee> selectEmployees(String sql, Object[] params);
		 //添加员工
	    public abstract int insertEmployee(T_Employee employee) ;
	    //按编号查询一个员工
	    public abstract List<T_Employee> findEmployeeById(Integer employeeId);
	    //按编号删除一个员工
	    public abstract int deleteEmployee(T_Employee employee);
	    //按编号修改一个员工
	  	public abstract int modifyEmployee(T_Employee employee);
	    //总数
		public abstract int getTotalCounts(String sql);
	    //分页
		public abstract List<T_Employee> selectbypage(String sql,List args);
		
		
		
		
		//修改员工上级信息
		public int modifSuperior(T_Employee id);
		
		// 删除员工第二种方法
		int deleteEmployee(String sql, Object[] obj, Integer empId);
		
		// 删除主管修改下级
		int updateEmployeeParent(String sql, Object[] obj);
		 
}
