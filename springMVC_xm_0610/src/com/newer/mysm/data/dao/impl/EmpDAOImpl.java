package com.newer.mysm.data.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.newer.mysm.data.dao.IPlanDAO;
import com.newer.mysm.data.dao.IempDAO;
import com.newer.mysm.data.entity.T_Employee;
import com.newer.mysm.data.entity.T_Plan;
import com.newer.mysm.data.entity.T_Role;
import com.newer.mysm.data.entity.Tally;
import com.newer.mysm.data.util.TallyDTO;


@Repository("EmpDAO")
public class EmpDAOImpl extends SqlSessionDaoSupport implements IempDAO{

	@Resource(name="sqlSessionFactory")
	SqlSessionFactory sqlSessionFactory;

	@PostConstruct
    private void  initialize() {
		setSqlSessionFactory(sqlSessionFactory);
    }
	//根据EMP ID查询
	public T_Employee selectEmpById(int empid) {
		T_Employee empuser=null;
		SqlSession session=super.getSqlSession();
		IempDAO dao= session.getMapper(IempDAO.class);
		System.out.println("进入方法selectEmpById");
		return empuser=dao.selectEmpById(empid);
	}

	//根据EMP编号查询PARENT_ID的下属或上属
	public List<T_Employee> selectEmpByRarntId(int rarntid, int pageNo,
			int pageSize) {
		
		T_Employee empuser=null;
		SqlSession session=super.getSqlSession();
		IempDAO dao=session.getMapper(IempDAO.class);	
		List<T_Employee>allemp=dao.selectEmpByRarntId(rarntid, pageNo, pageSize);
		return  allemp;
	}

}
