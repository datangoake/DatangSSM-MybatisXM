package com.newer.admin.service;

import java.util.List;

import com.newer.common.entry.T_Employee;
/*
 * Ҷ���2020-6-16
 * 
 */
public interface IEmployeeService {

		//�������Ա������v
		public abstract int getTotalCounts();
		//��ѯ�ǹ�����Ա����  v
		public  abstract int getTotalCountnotadmin();
		//������н�ɫΪԱ������  v
		public abstract int getTotalCountemp();
		
		
			//��¼
			public abstract T_Employee checkLogin(String employeeName, String password);
	        //ȫ����ѯv
			public abstract List<T_Employee> selectEmployees(Integer start,Integer end);
			//���Ա��v
		    public abstract int insertEmployee(T_Employee employee) ;
		    //����Ų�ѯԱ��v
			public abstract T_Employee findEmployeeById(Integer employeeId);
		    //�����ɾ��һ��Ա��v
		    public abstract int deleteEmployeebyid(Integer empId);
		    //������޸�һ��Ա��v
		  	public abstract int modifyEmployee(T_Employee employee);
		 
			
			
		   
			
			//��ѯ��������v
			public abstract List<T_Employee> queryAllManager();
			//��ҳ
			public abstract List<T_Employee> selectbypage(int pageNo,int pageSize);
			
			//�޸��ϼ���Ϣ
			public int modifSuperior(T_Employee id);
			//ɾ����ҳ
			public abstract List<T_Employee> querybypage(int pageNo,int pageSize);
			
			//��ѯ���н�ɫΪԱ����Ա��
			public abstract List<T_Employee> querybyemp(int pageNo, int pageSize);
			
			
			// �޸�������assigner_id�������˱�ţ�
			public int updateAssignerId(int id);
			// ɾ�������޸��¼�
			int updateEmployeeParent(Integer id);
}
