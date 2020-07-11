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
	//全部查询（分页） 已完成（用户管理）
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
	
	//分页 查询  查询所有非管理员员工（员工管理）
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

	
	
	
	
	
	
	//获取总条数  已完成
		@Override
		public int getTotalCounts1() {
			SqlSession session=super.getSqlSession();
			IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
			return dao.getTotalCounts1();
		}
		//获取总条数  已完成
				@Override
				public int getTotalCounts2() {
					SqlSession session=super.getSqlSession();
					IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
					return dao.getTotalCounts2();
				}
				//获取总条数  已完成
				@Override
				public int getTotalCounts3() {
					SqlSession session=super.getSqlSession();
					IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
					return dao.getTotalCounts3();
				}
			   //按编号查询一个员工  已完成
				@Override
				public List<T_Employee> findEmployeeById(@Param("employeeId") Integer employeeId) {
					System.out.println("启动数据层按编号查询一个员工:findEmployeeById");
					SqlSession session=super.getSqlSession();
					IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
					return dao.findEmployeeById(employeeId);
				}
			
				
				
				
				

    //添加员工  已完成
	@Override
	public int insertEmployee(T_Employee employee) {
		SqlSession session=super.getSqlSession();
		IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
		return dao.insertEmployee(employee);
	}
  
	// 删除员工第二种方法
	@Override
	public int deleteEmployee(@Param("id")Integer id) {
		SqlSession session=super.getSqlSession();
		IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
		return dao.deleteEmployee(id);
	}

	
	//  已完成
  //按编号修改(分配员工)
	@Override
	public int modifyEmployee(@Param("employee")T_Employee employee) {
		System.out.println("启动数据层按编号修改:modifyEmployee");
		SqlSession session=super.getSqlSession();
		IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
		return dao.modifyEmployee(employee);
	}
	
	//查询所有主管  已完成
		public List<T_Employee> queryAllManager(){
			SqlSession session=super.getSqlSession();
			IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
			return dao.queryAllManager();
		}
		
		
		//修改员工上级信息
		public int modifSuperior(@Param("id")Integer id) {
			SqlSession session=super.getSqlSession();
			IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
			return dao.modifSuperior(id);
		}
		// 删除主管，修改下级v
		@Override
		public int updateEmployeeParent(@Param("id")Integer id) {
			System.out.println("启动数据层删除主管修改下级");
			SqlSession session=super.getSqlSession();
			IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
			return dao.updateEmployeeParent(id);
		}

		//登录
		@Override
		public T_Employee checkLogin(T_Employee emp) {
			SqlSession session=super.getSqlSession();
			IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
			return dao.checkLogin(emp);
		}

		//根据用户编号查询角色
		@Override
		public T_Role getroleinfo(Integer id) {
			SqlSession session=super.getSqlSession();
			IEmployeeDAO dao=session.getMapper(IEmployeeDAO.class);
			return dao.getroleinfo(id);
		}





	
}
