package com.newer.admin.service;

import java.util.List;

import com.newer.common.entry.T_Employee;
/*
 * Ҷ���2020-6-16
 * 
 */
public interface IEmployeeService {

			//��¼
			public abstract T_Employee checkLogin(String employeeName, String password);
	        //ȫ����ѯ
			public abstract List<T_Employee> selectEmployees(String sql, Object[] params);
			//���Ա��
		    public abstract int insertEmployee(T_Employee employee) ;
		    //����Ų�ѯԱ��
			public abstract T_Employee findEmployeeById(Integer employeeId);
		    //�����ɾ��һ��Ա��
		    public abstract int deleteEmployee(T_Employee employee);
		    //������޸�һ��Ա��
		  	public abstract int modifyEmployee(T_Employee employee);
		    //��ѯ����ֱ���¼�
			public abstract List<T_Employee> findEmployeesByManager(int employeeId);
			//��ѯ���зǹ���Ա��Ա
			public abstract List<T_Employee> findEmployeesByNonRole(String roleName);
			//��ѯ��������
			public abstract List<T_Employee> queryAllManager();
			//��ҳ
			public abstract List<T_Employee> selectbypage(int pageNo,int pageSize);
			//�������Ա������
			public abstract int getTotalCounts(String tableName);
			//�޸��ϼ���Ϣ
			public int modifSuperior(T_Employee id);
			//ɾ����ҳ
			public abstract List<T_Employee> querybypage(int pageNo,int pageSize);
			//��ѯ�ǹ�����Ա����
			public  abstract int getTotalCountnotadmin(String tableName);
			//��ѯ���н�ɫΪԱ����Ա��
			public abstract List<T_Employee> querybyemp(int pageNo, int pageSize);
			//������н�ɫΪԱ������
			public abstract int getTotalCountemp(String tableName);
			
			
			// ɾ��Ա�ڶ��ַ���
			boolean deleteEmployee(Integer empId);
			
			// �޸�������assigner_id�������˱�ţ�
			public int updateAssignerId(Object[] obj);
			// ɾ�������޸��¼�
			int updateEmployeeParent(Object[] obj);
}
