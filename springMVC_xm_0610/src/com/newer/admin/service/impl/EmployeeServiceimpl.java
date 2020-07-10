package com.newer.admin.service.impl;
/*
 * Ҷ���2020-6-16
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
	
	//��ҳȫ����ѯ������ v
	@Override
	public int getTotalCounts() {
		return this.dao.getTotalCounts1();
	}
	//��ѯ��Ϊ����Ա���û�����
	@Override
	public int getTotalCountnotadmin() {
		return this.dao.getTotalCounts2();
	}
	//����û���ɫΪԱ��������
	@Override
	public int getTotalCountemp() {	
		return this.dao.getTotalCounts3() ;
	}
	
	
	
	
//	//��ѯ����Ա������ҳ�� v
//	@Override
//	public List<T_Employee> selectEmployees(Integer start,Integer end) {
//		System.out.println("����ҵ���ִ�в�ѯ");
//		return this.dao.selectEmployees(start,end);
//	}
	//��ѯ���в���ҳ���û�����
	@Override
	public List<T_Employee> selectbypage(int pageNo, int pageSize) {
				System.out.println("����ҵ����ҳ:selectbypage");
				int start = (pageNo - 1) * pageSize + 1;
				int end = pageNo * pageSize;
				Map<String,Object> param=new HashMap<String, Object>();
				param.put("startIndex",start);
				param.put("endIndex",end);
				List<T_Employee> employees=this.dao.selectbypage(param);
				return employees;
			}
	
			
			//��ҳ ��ѯ  ��ѯ���зǹ���ԱԱ����Ա������
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
			
			//��ѯ���н�ɫΪԱ������Ϣ��������Ա��
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
			//����Ա���� v
			@Override
			public int insertEmployee(T_Employee employee) {
				
				return this.dao.insertEmployee(employee);
			}
			
			//����Ų�ѯԱ�� v
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
			
	
	
	


	//�޸�Ա�� v
	@Override
	public int modifyEmployee(T_Employee employee) {
		System.out.println("����ҵ���:modifyEmployee");
		return this.dao.modifyEmployee(employee);
	}

	   //��ѯ�������� v
		@Override
		public List<T_Employee> queryAllManager() {
			String sql="select * from t_employee where ROLE_ID=3";
			return this.dao.queryAllManager();
		}
		
	  
		//�޸�Ա���ϼ� v
		@Override
		public int modifSuperior(T_Employee id) {
			// TODO Auto-generated method stub
			return this.dao.modifSuperior(id.getEmployee_ID());
		}


	
	
	//��ѯ����ֱ���¼�
//	@Override
//	public List<T_Employee> findEmployeesByManager() {
//		System.out.println("��ѯ����ֱ������");
//		String whereSql ="select e.* from t_employee e left join t_employee m on e.parent_id=m.employee_id where m.employee_id=? ";
//		Object[] params = new Object[]{employeeId};
//		
//		return this.dao.selectEmployees();
//	}

	//��ѯ���зǹ���Ա��Ա
//	@Override
//	public List<T_Employee> findEmployeesByNonRole(String roleName) {
//		System.out.println("��ѯ���зǹ���Ա��Ա");
//			String whereSql = 
//			"select * from t_employee e " +
//			"where e.role_id in" +
//			"(select role_id  from t_role r " +
//			"where r.role_name not in (?))";
//			Object[] params = new Object[]{roleName};
//			
//		return this.dao.selectEmployees(whereSql, params);
//	}
    //��¼
	@Override
	public T_Employee checkLogin(String employeeName, String password) {
		System.out.println("������¼ҵ���:checkLogin");
		T_Employee employee=new T_Employee();
		employee.setEmployee_name(employeeName);
		employee.setPassword(password);
		return this.dao.checkLogin(employee);
	}
	
	
   
	//�����ɾ��
   //ɾ��Ա���ڶ��ַ���
	@Override
	public int deleteEmployeebyid(Integer empId) {

		int deleteEmployee =dao.deleteEmployee(empId);
		if (deleteEmployee>0 ) {
			return 1;
		}else {
			return 0;
		}
		
	}
	// �޸�������assigner_id�������˱�ţ�  v
	@Override
	public int updateAssignerId(int id) {
		return this.tdao.updateAssignerId(id);
	}
	
	
	// ɾ�����ܣ��޸��¼�v
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
