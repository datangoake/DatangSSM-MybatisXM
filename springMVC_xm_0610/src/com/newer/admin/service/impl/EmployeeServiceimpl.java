package com.newer.admin.service.impl;
/*
 * 叶金豪2020-6-16
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newer.admin.data.dao.IEmployeeDAO;
import com.newer.admin.data.dao.ITaskDao;
import com.newer.admin.service.IEmployeeService;
import com.newer.common.entry.T_Employee;
@Service("EmployeeServiceimpl")
public class EmployeeServiceimpl implements IEmployeeService {
	

	
	@Resource(name="EmployeeDaoImpl")
	IEmployeeDAO dao; 
	
	
	@Resource(name="TaskDaoImpl")
	ITaskDao tdao; 
	
	//分页全部查询总条数 v
	@Override
	public int getTotalCounts() {
		return this.dao.getTotalCounts1();
	}
	//查询不为管理员的用户总数
	@Override
	public int getTotalCountnotadmin() {
		return this.dao.getTotalCounts2();
	}
	//获得用户角色为员工的总数
	@Override
	public int getTotalCountemp() {	
		return this.dao.getTotalCounts3() ;
	}
	
	
	
	
//	//查询所有员工（分页） v
//	@Override
//	public List<T_Employee> selectEmployees(Integer start,Integer end) {
//		System.out.println("启动业务层执行查询");
//		return this.dao.selectEmployees(start,end);
//	}
	//查询所有并分页（用户管理）
	@Override
	public List<T_Employee> selectbypage(int pageNo, int pageSize) {
				System.out.println("启动业务层分页:selectbypage");
				int start = (pageNo - 1) * pageSize + 1;
				int end = pageNo * pageSize;
				Map<String,Object> param=new HashMap<String, Object>();
				param.put("startIndex",start);
				param.put("endIndex",end);
				List<T_Employee> employees=this.dao.selectbypage(param);
				return employees;
			}
	
			
			//分页 查询  查询所有非管理员员工（员工管理）
			@Override
			public List<T_Employee> querybypage(int pageNo, int pageSize) {
				int start = (pageNo - 1) * pageSize + 1;
				int end = pageNo * pageSize;
				Map<String,Object> param=new HashMap<String, Object>();
				param.put("startIndex",start);
				param.put("endIndex",end);
				List<T_Employee> employees=this.dao.querybypage(param);
				
				return employees;
			}
			
			//查询所有角色为员工的信息（分配人员）
			@Override
			public List<T_Employee> querybyemp(int pageNo, int pageSize) {
				int start = (pageNo - 1) * pageSize + 1;
				int end = pageNo * pageSize;
				String sql="select * from (select row_number() over(order by employee_id desc) no, temp2.* from " +
						"(select * from T_EMPLOYEE where ROLE_ID =4) temp2 )temp where temp.no between ? and ? ";
				Map<String,Object> param=new HashMap<String, Object>();
				param.put("startIndex",start);
				param.put("endIndex",end);
		        List<T_Employee> employees=this.dao.querybyemp(param);
				return employees;
			}
			//新增员工： v
			@Override
			public int insertEmployee(T_Employee employee) {
				
				return this.dao.insertEmployee(employee);
			}
			
			//按编号查询员工 v
			@Override
			public T_Employee findEmployeeById(Integer employeeId) {
				List<T_Employee> employees=this.dao.findEmployeeById(employeeId);
				if (employees.size()>0) {
					employees.get(0).setRole(dao.getroleinfo(employees.get(0).getEmployee_ID()));
					return  employees.get(0);
				}else {	
					return null;
				}
				
			}
			
	
	
	


	//修改员工 v
	@Override
	public int modifyEmployee(T_Employee employee) {
		System.out.println("启动业务层:modifyEmployee");
		return this.dao.modifyEmployee(employee);
	}

	   //查询所有主管 v
		@Override
		public List<T_Employee> queryAllManager() {
			String sql="select * from t_employee where ROLE_ID=3";
			return this.dao.queryAllManager();
		}
		
	  
		//修改员工上级 v
		@Override
		public int modifSuperior(T_Employee id) {
			// TODO Auto-generated method stub
			return this.dao.modifSuperior(id.getEmployee_ID());
		}


	
	
	//查询所有直接下级
//	@Override
//	public List<T_Employee> findEmployeesByManager() {
//		System.out.println("查询所有直接下属");
//		String whereSql ="select e.* from t_employee e left join t_employee m on e.parent_id=m.employee_id where m.employee_id=? ";
//		Object[] params = new Object[]{employeeId};
//		
//		return this.dao.selectEmployees();
//	}

	//查询所有非管理员雇员
//	@Override
//	public List<T_Employee> findEmployeesByNonRole(String roleName) {
//		System.out.println("查询所有非管理员雇员");
//			String whereSql = 
//			"select * from t_employee e " +
//			"where e.role_id in" +
//			"(select role_id  from t_role r " +
//			"where r.role_name not in (?))";
//			Object[] params = new Object[]{roleName};
//			
//		return this.dao.selectEmployees(whereSql, params);
//	}
    //登录
	@Override
	public T_Employee checkLogin(String employeeName, String password) {
		System.out.println("启动登录业务层:checkLogin");
		T_Employee employee=new T_Employee();
		employee.setEmployee_name(employeeName);
		employee.setPassword(password);
		return this.dao.checkLogin(employee);
	}
	
	
   
	//按编号删除
   //删除员工第二种方法
	@Override
	public int deleteEmployeebyid(Integer empId) {

		int deleteEmployee =dao.deleteEmployee(empId);
		if (deleteEmployee>0 ) {
			return 1;
		}else {
			return 0;
		}
		
	}
	// 修改任务表的assigner_id（分配人编号）  v
	@Override
	public int updateAssignerId(int id) {
		return this.tdao.updateAssignerId(id);
	}
	
	
	// 删除主管，修改下级v
	@Override
	public int updateEmployeeParent(Integer id) {
		return this.dao.updateEmployeeParent(id);
	}
	
	@Override
	public List<T_Employee> selectEmployees(Integer start, Integer end) {
		// TODO Auto-generated method stub
		return null;
	}


}
