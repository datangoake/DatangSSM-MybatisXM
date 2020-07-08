package com.newer.admin.service;

import java.util.List;

import com.newer.common.entry.T_Employee;
/*
 * 叶金豪2020-6-16
 * 
 */
public interface IEmployeeService {

			//登录
			public abstract T_Employee checkLogin(String employeeName, String password);
	        //全部查询
			public abstract List<T_Employee> selectEmployees(String sql, Object[] params);
			//添加员工
		    public abstract int insertEmployee(T_Employee employee) ;
		    //按编号查询员工
			public abstract T_Employee findEmployeeById(Integer employeeId);
		    //按编号删除一个员工
		    public abstract int deleteEmployee(T_Employee employee);
		    //按编号修改一个员工
		  	public abstract int modifyEmployee(T_Employee employee);
		    //查询所有直接下级
			public abstract List<T_Employee> findEmployeesByManager(int employeeId);
			//查询所有非管理员雇员
			public abstract List<T_Employee> findEmployeesByNonRole(String roleName);
			//查询所有主管
			public abstract List<T_Employee> queryAllManager();
			//分页
			public abstract List<T_Employee> selectbypage(int pageNo,int pageSize);
			//获得所有员工总数
			public abstract int getTotalCounts(String tableName);
			//修改上级信息
			public int modifSuperior(T_Employee id);
			//删除分页
			public abstract List<T_Employee> querybypage(int pageNo,int pageSize);
			//查询非管理人员总数
			public  abstract int getTotalCountnotadmin(String tableName);
			//查询所有角色为员工的员工
			public abstract List<T_Employee> querybyemp(int pageNo, int pageSize);
			//获得所有角色为员工总数
			public abstract int getTotalCountemp(String tableName);
			
			
			// 删除员第二种方法
			boolean deleteEmployee(Integer empId);
			
			// 修改任务表的assigner_id（分配人编号）
			public int updateAssignerId(Object[] obj);
			// 删除主管修改下级
			int updateEmployeeParent(Object[] obj);
}
