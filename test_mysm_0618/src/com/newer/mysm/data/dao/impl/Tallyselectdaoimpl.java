package com.newer.mysm.data.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.newer.mysm.data.dao.Tallyselectdao;
import com.newer.mysm.data.entity.T_Role;
import com.newer.mysm.data.entity.Tally;
import com.newer.mysm.data.util.TallyDTO;


@Repository("talldao")
public class Tallyselectdaoimpl extends SqlSessionDaoSupport implements Tallyselectdao {
	
	
	@Resource(name="sqlSessionFactory")
	SqlSessionFactory factory;

	@PostConstruct
    private void  initialize() {
        setSqlSessionFactory(factory);
    }

	@Override
	public List<Tally> selectall(Map<String,Object> parme) {
		List<Tally> list=null;
		System.out.println(121212563);
		
		SqlSession session=super.getSqlSession();
		Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
		list=dao.selectall(parme);
		return list;
	}

	@Override
	public int getCount(TallyDTO tally) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T_Role roleSelect(@Param("id") int id) {
		T_Role role=new T_Role();
		SqlSession session=super.getSqlSession();
		Tallyselectdao dao=session.getMapper(Tallyselectdao.class);
		return role=dao.roleSelect(id);
	}
	
	

}
