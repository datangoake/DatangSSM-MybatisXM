package com.newer.admin.data.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.newer.admin.data.dao.IEmployeeDAO;
import com.newer.admin.web.util.DBUtil;
import com.newer.common.entry.T_Employee;
import com.newer.common.entry.T_Role;

@Component("EmployeeDaoImpl")
public class IEmployeeDaoImpl extends SqlSessionDaoSupport implements IEmployeeDAO {
   
	@Resource(name="sqlSessionFactory")
	SqlSessionFactory factory;

	@PostConstruct
    private void  initialize() {
        setSqlSessionFactory(factory);
    }
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate(DBUtil.getDataSource());
	//ȫ����ѯ����ҳ�� ����ɣ��û�����
	@Override
	public List<T_Employee> selectbypage(Map<String,Object> param) {
		SqlSession session=super.getSqlSession();
		IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
		List<T_Employee> list=dao.selectbypage(param);
		for (T_Employee t_Employee : list) {
			T_Role role=this.getroleinfo(t_Employee.getEmployee_ID());
			t_Employee.setRole(role);
		}
		return list;
	}
	
	//��ҳ ��ѯ  ��ѯ���зǹ���ԱԱ����Ա������
	@Override
	public List<T_Employee> querybypage(Map<String, Object> param) {
		SqlSession session=super.getSqlSession();
		IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
		List<T_Employee> list=dao.querybypage(param);
		for (T_Employee t_Employee : list) {
			T_Role role=this.getroleinfo(t_Employee.getEmployee_ID());
			t_Employee.setRole(role);
		}
		return list;
	}
	@Override
	public List<T_Employee> querybyemp(Map<String, Object> param) {
		SqlSession session=super.getSqlSession();
		IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
		List<T_Employee> list=dao.querybyemp(param);
		for (T_Employee t_Employee : list) {
			T_Role role=this.getroleinfo(t_Employee.getEmployee_ID());
			t_Employee.setRole(role);
		}
		return list;
	}

	
	
	
	
	
	
	//��ȡ������  �����
		@Override
		public int getTotalCounts1() {
			SqlSession session=super.getSqlSession();
			IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
			return dao.getTotalCounts1();
		}
		//��ȡ������  �����
				@Override
				public int getTotalCounts2() {
					SqlSession session=super.getSqlSession();
					IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
					return dao.getTotalCounts2();
				}
				//��ȡ������  �����
				@Override
				public int getTotalCounts3() {
					SqlSession session=super.getSqlSession();
					IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
					return dao.getTotalCounts3();
				}
			   //����Ų�ѯһ��Ա��  �����
				@Override
				public List<T_Employee> findEmployeeById(@Param("employeeId") Integer employeeId) {
					System.out.println("�������ݲ㰴��Ų�ѯһ��Ա��:findEmployeeById");
					SqlSession session=super.getSqlSession();
					IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
					return dao.findEmployeeById(employeeId);
				}
			
				
				
				
				

    //���Ա��  �����
	@Override
	public int insertEmployee(T_Employee employee) {
		SqlSession session=super.getSqlSession();
		IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
		return dao.insertEmployee(employee);
	}
  
	// ɾ��Ա���ڶ��ַ���
	@Override
	public int deleteEmployee(@Param("id")Integer id) {
		SqlSession session=super.getSqlSession();
		IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
		return dao.deleteEmployee(id);
	}

	
	//  �����
  //������޸�(����Ա��)
	@Override
	public int modifyEmployee(@Param("employee")T_Employee employee) {
		System.out.println("�������ݲ㰴����޸�:modifyEmployee");
		SqlSession session=super.getSqlSession();
		IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
		return dao.modifyEmployee(employee);
	}
	
	//��ѯ��������  �����
		public List<T_Employee> queryAllManager(){
			SqlSession session=super.getSqlSession();
			IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
			return dao.queryAllManager();
		}
		
		
		//�޸�Ա���ϼ���Ϣ
		public int modifSuperior(@Param("id")Integer id) {
			SqlSession session=super.getSqlSession();
			IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
			return dao.modifSuperior(id);
		}
		// ɾ�����ܣ��޸��¼�v
		@Override
		public int updateEmployeeParent(@Param("id")Integer id) {
			System.out.println("�������ݲ�ɾ�������޸��¼�");
			SqlSession session=super.getSqlSession();
			IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
			return dao.updateEmployeeParent(id);
		}

		//��¼
		@Override
		public T_Employee checkLogin(T_Employee emp) {
			SqlSession session=super.getSqlSession();
			IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
			return dao.checkLogin(emp);
		}

		//�����û���Ų�ѯ��ɫ
		@Override
		public T_Role getroleinfo(Integer id) {
			SqlSession session=super.getSqlSession();
			IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
			return dao.getroleinfo(id);
		}





	
}
