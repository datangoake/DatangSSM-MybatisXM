package com.newer.manager.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import oracle.net.aso.p;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.newer.common.entry.T_Plan;
import com.newer.manager.dao.IPlanDAO;
import com.newer.manager.util.DBUtil;

@Component("plandao")
public class PlanDAOImpl implements IPlanDAO {
	JdbcTemplate temp=new JdbcTemplate(DBUtil.getDataSource());
	@Override
	//根据task_id查询所有计划
	public List<T_Plan> selectPlan(int planid) {
		String sql="select * from T_PLAN where task_id=?";
		return this.temp.query(sql, new Object[]{planid}, new RowMapper() {			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Plan plan=new T_Plan();
				plan.setPlan_id(rs.getInt("PLAN_ID"));
				plan.setPlan_name(rs.getString("PLAN_NAME"));
				plan.setStatus(rs.getString("STATUS"));
				plan.setIs_feedback(rs.getString("IS_FEEDBACK"));
				plan.setBegin_date(rs.getTimestamp("BEGIN_DATE"));
				plan.setEnd_date(rs.getTimestamp("END_DATE"));
				plan.setFeedback_info(rs.getString("FEEDBACK_INFO"));
				return plan;
			}
		});
	}

	
	@Override
	//根据task_id查询任务的计划总数
	public int selectPlanCount(int planid) {
		String sql="select count(*) from T_PLAN where task_id=?";
		return this.temp.queryForInt(sql, new Object[]{planid});
	}

	//根据plan_id查找单条计划的详细信息
	@Override
	public T_Plan selectPlanById(int planid) {
		String sql="select * from T_PLAN where plan_id=?";
		return (T_Plan) this.temp.queryForObject(sql, new Object[]{planid}, new RowMapper() {			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Plan plan=new T_Plan();
				plan.setPlan_id(rs.getInt("PLAN_ID"));
				plan.setPlan_name(rs.getString("PLAN_NAME"));
				plan.setStatus(rs.getString("STATUS"));
				plan.setIs_feedback(rs.getString("IS_FEEDBACK"));
				plan.setBegin_date(rs.getTimestamp("BEGIN_DATE"));
				plan.setEnd_date(rs.getTimestamp("END_DATE"));
				plan.setFeedback_info(rs.getString("FEEDBACK_INFO"));
				plan.setPlan_desc(rs.getString("PLAN_DESC"));
				plan.setTask(new TaskDAOImpl().selectAllTaskByID(rs.getInt("TASK_ID")));
				return plan;
			}
		});
	}

}
