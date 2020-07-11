package com.newer.admin.data.dao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.newer.admin.data.dao.ITaskDao;
import com.newer.admin.web.util.DBUtil;
@Component("TaskDaoImpl")
public class TaskDaoImpl extends SqlSessionDaoSupport implements ITaskDao {
	JdbcTemplate jdbcTemplate = new JdbcTemplate(DBUtil.getDataSource());
	
	@Resource(name="sqlSessionFactory")
	SqlSessionFactory factory;

	@PostConstruct
    private void  initialize() {
        setSqlSessionFactory(factory);
    }
	
	
	// 修改任务表的assigner_id（分配人编号）
	@Override
	public int updateAssignerId(@Param("id")Integer id) {
		SqlSession session=super.getSqlSession();
		ITaskDao dao=session.getMapper(ITaskDao.class);
		return dao.updateAssignerId(id);
	}

}
