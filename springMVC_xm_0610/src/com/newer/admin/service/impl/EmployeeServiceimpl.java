package com.newer.admin.service.impl;
/*
 * Ҷ���2020-6-16
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
	
	//��ѯ����Ա��
	@Override
	public List<T_Employee> selectEmployees(String sql, Object[] params) {
		System.out.println("����ҵ���ִ�в�ѯ");
		return this.dao.selectEmployees("select * from T_EMPLOYEE", new Object[]{});
	}

	//����Ա����
	@Override
	public int insertEmployee(T_Employee employee) {
		
		return this.dao.insertEmployee(employee);
	}

	//ɾ��Ա��
	@Override
	public int deleteEmployee(T_Employee employee) {
		
		return this.dao.deleteEmployee(employee);
	}

	//����Ų�ѯԱ��
	@Override
	public T_Employee findEmployeeById(Integer employeeId) {
		List<T_Employee> employees=this.dao.findEmployeeById(employeeId);
		if (employees.size()>0) {
			return  employees.get(0);
		}else {	
			return null;
		}
		
	}

	//�޸�Ա��
	@Override
	public int modifyEmployee(T_Employee employee) {
		System.out.println("����ҵ���:modifyEmployee");
		return this.dao.modifyEmployee(employee);
	}

	//��ѯ����ֱ���¼�
	@Override
	public List<T_Employee> findEmployeesByManager(int employeeId) {
		System.out.println("��ѯ����ֱ������");
		String whereSql ="select e.* from t_employee e left join t_employee m on e.parent_id=m.employee_id where m.employee_id=? ";
		Object[] params = new Object[]{employeeId};
		
		return this.dao.selectEmployees(whereSql, params);
	}

	//��ѯ���зǹ���Ա��Ա
	@Override
	public List<T_Employee> findEmployeesByNonRole(String roleName) {
		System.out.println("��ѯ���зǹ���Ա��Ա");
			String whereSql = 
			"select * from t_employee e " +
			"where e.role_id in" +
			"(select role_id  from t_role r " +
			"where r.role_name not in (?))";
			Object[] params = new Object[]{roleName};
			
		return this.dao.selectEmployees(whereSql, params);
	}
    //��¼
	@Override
	public T_Employee checkLogin(String employeeName, String password) {
		System.out.println("������¼ҵ���:checkLogin");
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
   //��ѯ��������
	@Override
	public List<T_Employee> queryAllManager() {
		String sql="select * from t_employee where ROLE_ID=3";
		return this.dao.selectEmployees(sql, null);
	}
	//��ҳȫ����ѯ
	@Override
	public List<T_Employee> selectbypage(int pageNo, int pageSize) {
		System.out.println("����ҵ����ҳ:selectbypage");
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
    //������
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
	//��ѯ���н�ɫΪԱ����Ա��
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
	//���Ա��������
	@Override
	public int getTotalCountemp(String tableName) {
		String sql = "select count(*) from "+tableName+" where ROLE_ID =4";
		
		return this.dao.getTotalCounts(sql) ;
	}
   //ɾ��Ա���ڶ��ַ���
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
	// �޸�������assigner_id�������˱�ţ�
	@Override
	public int updateAssignerId(Object[] obj) {
		String sql = "update t_task set assigner_id=null where assigner_Id=?";
		return this.tdao.updateAssignerId(sql, obj);
	}
	
	
	// ɾ�����ܣ��޸��¼�
	@Override
	public int updateEmployeeParent(Object[] obj) {
		String sql = "update t_employee set parent_id=null where employee_Id in (select employee_Id from t_employee where parent_Id=?)";
		return this.dao.updateEmployeeParent(sql, obj);
	}

}
