package com.newer.admin.data.dao;

import java.util.List;

import com.newer.common.entry.T_Employee;



public interface IEmployeeDAO {
	    //ȫ����ѯ
		public abstract List<T_Employee> selectEmployees(String sql, Object[] params);
		 //���Ա��
	    public abstract int insertEmployee(T_Employee employee) ;
	    //����Ų�ѯһ��Ա��
	    public abstract List<T_Employee> findEmployeeById(Integer employeeId);
	    //�����ɾ��һ��Ա��
	    public abstract int deleteEmployee(T_Employee employee);
	    //������޸�һ��Ա��
	  	public abstract int modifyEmployee(T_Employee employee);
	    //����
		public abstract int getTotalCounts(String sql);
	    //��ҳ
		public abstract List<T_Employee> selectbypage(String sql,List args);
		
		
		
		
		//�޸�Ա���ϼ���Ϣ
		public int modifSuperior(T_Employee id);
		
		// ɾ��Ա���ڶ��ַ���
		int deleteEmployee(String sql, Object[] obj, Integer empId);
		
		// ɾ�������޸��¼�
		int updateEmployeeParent(String sql, Object[] obj);
		 
}
