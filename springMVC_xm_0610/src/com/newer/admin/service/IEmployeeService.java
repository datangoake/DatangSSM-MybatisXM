package com.newer.admin.service;

import java.util.List;

import com.newer.common.entry.T_Employee;
/*
 * 叶金豪2020-6-16
 * 
 */
public interface IEmployeeService {

		//获得所有员工总数v
		public abstract int getTotalCounts();
		//查询非管理人员总数  v
		public  abstract int getTotalCountnotadmin();
		//获得所有角色为员工总数  v
		public abstract int getTotalCountemp();
		
		
			//登录
			public abstract T_Employee checkLogin(String employeeName, String password);
	        //全部查询v
			public abstract List<T_Employee> selectEmployees(Integer start,Integer end);
			//添加员工v
		    public abstract int insertEmployee(T_Employee employee) ;
		    //按编号查询员工v
			public abstract T_Employee findEmployeeById(Integer employeeId);
		    //按编号删除一个员工v
		    public abstract int deleteEmployeebyid(Integer empId);
		    //按编号修改一个员工v
		  	public abstract int modifyEmployee(T_Employee employee);
		 
			
			
		   
			
			//查询所有主管v
			public abstract List<T_Employee> queryAllManager();
			//分页
			public abstract List<T_Employee> selectbypage(int pageNo,int pageSize);
			
			//修改上级信息
			public int modifSuperior(T_Employee id);
			//删除分页
			public abstract List<T_Employee> querybypage(int pageNo,int pageSize);
			
			//查询所有角色为员工的员工
			public abstract List<T_Employee> querybyemp(int pageNo, int pageSize);
			
			
			// 修改任务表的assigner_id（分配人编号）
			public int updateAssignerId(int id);
			// 删除主管修改下级
			int updateEmployeeParent(Integer id);
}
