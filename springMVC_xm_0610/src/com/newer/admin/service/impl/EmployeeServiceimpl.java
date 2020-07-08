package com.newer.admin.service.impl;
/*
 * 叶金豪2020-6-16
 * 
 */
import java.util.ArrayList;
import java.util.List;

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
	
	//查询所有员工
	@Override
	public List<T_Employee> selectEmployees(String sql, Object[] params) {
		System.out.println("启动业务层执行查询");
		return this.dao.selectEmployees("select * from T_EMPLOYEE", new Object[]{});
	}

	//新增员工：
	@Override
	public int insertEmployee(T_Employee employee) {
		
		return this.dao.insertEmployee(employee);
	}

	//删除员工
	@Override
	public int deleteEmployee(T_Employee employee) {
		
		return this.dao.deleteEmployee(employee);
	}

	//按编号查询员工
	@Override
	public T_Employee findEmployeeById(Integer employeeId) {
		List<T_Employee> employees=this.dao.findEmployeeById(employeeId);
		if (employees.size()>0) {
			return  employees.get(0);
		}else {	
			return null;
		}
		
	}

	//修改员工
	@Override
	public int modifyEmployee(T_Employee employee) {
		System.out.println("启动业务层:modifyEmployee");
		return this.dao.modifyEmployee(employee);
	}

	//查询所有直接下级
	@Override
	public List<T_Employee> findEmployeesByManager(int employeeId) {
		System.out.println("查询所有直接下属");
		String whereSql ="select e.* from t_employee e left join t_employee m on e.parent_id=m.employee_id where m.employee_id=? ";
		Object[] params = new Object[]{employeeId};
		
		return this.dao.selectEmployees(whereSql, params);
	}

	//查询所有非管理员雇员
	@Override
	public List<T_Employee> findEmployeesByNonRole(String roleName) {
		System.out.println("查询所有非管理员雇员");
			String whereSql = 
			"select * from t_employee e " +
			"where e.role_id in" +
			"(select role_id  from t_role r " +
			"where r.role_name not in (?))";
			Object[] params = new Object[]{roleName};
			
		return this.dao.selectEmployees(whereSql, params);
	}
    //登录
	@Override
	public T_Employee checkLogin(String employeeName, String password) {
		System.out.println("启动登录业务层:checkLogin");
		T_Employee employee=null;
		List<T_Employee> employees=new ArrayList<T_Employee>();
		String sql="select * from t_employee where employee_name=? and password=?";
		Object[] params = new Object[] { employeeName, password};
		employees=this.dao.selectEmployees(sql, params);
		if (employees != null && employees.size() > 0) {
			employee=employees.get(0);
			
		}
		
		return employee;
	}
   //查询所有主管
	@Override
	public List<T_Employee> queryAllManager() {
		String sql="select * from t_employee where ROLE_ID=3";
		return this.dao.selectEmployees(sql, null);
	}
	//分页全部查询
	@Override
	public List<T_Employee> selectbypage(int pageNo, int pageSize) {
		System.out.println("启动业务层分页:selectbypage");
		int start = (pageNo - 1) * pageSize + 1;
		int end = pageNo * pageSize;
		String sql="select * from " +
				"(select row_number() over(order by employee_id desc) no, t_employee.* from t_employee ) " +
				"temp where temp.no between ? and ? ";
		List args = new ArrayList();
		args.add(start);
		args.add(end);
		
		List<T_Employee> employees=this.dao.selectbypage(sql, args);
		return employees;
	}
    //总条数
	@Override
	public int getTotalCounts(String tableName) {
		String sql = "select count(*) from "+tableName;
		return this.dao.getTotalCounts(sql);
	}

	@Override
	public int modifSuperior(T_Employee id) {
		// TODO Auto-generated method stub
		return this.dao.modifSuperior(id);
	}

	@Override
	public List<T_Employee> querybypage(int pageNo, int pageSize) {
		int start = (pageNo - 1) * pageSize + 1;
		int end = pageNo * pageSize;
		String sql="select * from (select row_number() over(order by employee_id desc) no, temp2.* from " +
				"(select * from T_EMPLOYEE where ROLE_ID <> 2) temp2 )temp where temp.no between ? and ?";
		List args = new ArrayList();
		args.add(start);
		args.add(end);
		
		List<T_Employee> employees=this.dao.selectbypage(sql, args);
		
		return employees;
	}
     
	@Override
	public int getTotalCountnotadmin(String tableName) {
		String sql = "select count(*) from "+tableName+" where ROLE_ID <> 2";
		return this.dao.getTotalCounts(sql);
	}
	//查询所有角色为员工的员工
	@Override
	public List<T_Employee> querybyemp(int pageNo, int pageSize) {
		int start = (pageNo - 1) * pageSize + 1;
		int end = pageNo * pageSize;
		String sql="select * from (select row_number() over(order by employee_id desc) no, temp2.* from " +
				"(select * from T_EMPLOYEE where ROLE_ID =4) temp2 )temp where temp.no between ? and ? ";
		List args = new ArrayList();
		args.add(start);
		args.add(end);
        List<T_Employee> employees=this.dao.selectbypage(sql, args);
		return employees;
	}
	//获得员工的总数
	@Override
	public int getTotalCountemp(String tableName) {
		String sql = "select count(*) from "+tableName+" where ROLE_ID =4";
		
		return this.dao.getTotalCounts(sql) ;
	}
   //删除员工第二种方法
	@Override
	public boolean deleteEmployee(Integer empId) {
		String sql = "delete from t_employee where employee_id=?";
		Object[] obj = { empId };
		
		int deleteEmployee =dao.deleteEmployee(sql, obj, empId);
		if (deleteEmployee>0 ) {
			return true;
		}else {
			return false;
		}
		
	}
	// 修改任务表的assigner_id（分配人编号）
	@Override
	public int updateAssignerId(Object[] obj) {
		String sql = "update t_task set assigner_id=null where assigner_Id=?";
		return this.tdao.updateAssignerId(sql, obj);
	}
	
	
	// 删除主管，修改下级
	@Override
	public int updateEmployeeParent(Object[] obj) {
		String sql = "update t_employee set parent_id=null where employee_Id in (select employee_Id from t_employee where parent_Id=?)";
		return this.dao.updateEmployeeParent(sql, obj);
	}

}
