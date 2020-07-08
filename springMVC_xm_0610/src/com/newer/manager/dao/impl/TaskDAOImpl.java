package com.newer.manager.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.newer.common.entry.T_Employee;
import com.newer.common.entry.T_Task;
import com.newer.manager.dao.ITaskDAO;
import com.newer.manager.util.DBUtil;

@Component("taskdao")
public class TaskDAOImpl implements ITaskDAO {
	JdbcTemplate temp=new JdbcTemplate(DBUtil.getDataSource());
	
	@Override
	public List<T_Task> selectAllTask(T_Employee emp,int pageNo, int pageSize,String status) {
		int start = (pageNo -1) * pageSize +1;
		int end = pageNo * pageSize;
		//String sql="select * from T_TASK where ASSIGNER_ID=?";
		if (status.equals("all")) {
			String sql = "select * from(select row_number() over(order by t.task_id desc) no,t.* from t_task t where ASSIGNER_ID = ?)temp where temp.no between ? and ?";
			return this.temp.query(sql,new Object[]{emp.getEmployee_ID(),start,end}, new RowMapper() {
				
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					T_Task ts=new T_Task();
					ts.setTask_id(rs.getInt("TASK_ID"));
					ts.setTask_name(rs.getString("TASK_NAME"));
					ts.setMp_emp(new EmpDAOImpl().selectEmpById(rs.getInt("IMPLEMENTOR_ID")));
					ts.setBegin_date(rs.getTimestamp("BEGIN_DATE"));
					ts.setEnd_date(rs.getTimestamp("END_DATE"));
					ts.setStatus(rs.getString("STATUS"));
					ts.setTask_desc(Integer.toString(new PlanDAOImpl().selectPlanCount(rs.getInt("TASK_ID"))));
					return ts;
				}
			});
		}else{
			String sql = "select * from(select row_number() over(order by t.task_id desc) no,t.* from t_task t where ASSIGNER_ID = ? and status=?)temp where temp.no between ? and ?";
			return this.temp.query(sql,new Object[]{emp.getEmployee_ID(),status,start,end}, new RowMapper() {
				
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					T_Task ts=new T_Task();
					ts.setTask_id(rs.getInt("TASK_ID"));
					ts.setTask_name(rs.getString("TASK_NAME"));
					ts.setMp_emp(new EmpDAOImpl().selectEmpById(rs.getInt("IMPLEMENTOR_ID")));
					ts.setBegin_date(rs.getTimestamp("BEGIN_DATE"));
					ts.setEnd_date(rs.getTimestamp("END_DATE"));
					ts.setStatus(rs.getString("STATUS"));
					ts.setTask_desc(Integer.toString(new PlanDAOImpl().selectPlanCount(rs.getInt("TASK_ID"))));
					return ts;
				}
			});
		}
		
	}
	@Override
	public T_Task selectAllTaskByID(int taskid) {
		String sql="select * from T_TASK where TASK_ID=?";
		return (T_Task) this.temp.queryForObject(sql, new Object[]{taskid}, new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Task ts=new T_Task();
				ts.setTask_id(rs.getInt("TASK_ID"));
				ts.setTask_name(rs.getString("TASK_NAME"));
				ts.setMp_emp(new EmpDAOImpl().selectEmpById(rs.getInt("IMPLEMENTOR_ID")));
				ts.setBegin_date(rs.getTimestamp("BEGIN_DATE"));
				ts.setEnd_date(rs.getTimestamp("END_DATE"));
				ts.setReal_begin_date(rs.getTimestamp("REAL_BEGIN_DATE"));
				ts.setReal_end_date(rs.getTimestamp("REAL_END_DATE"));
				ts.setStatus(rs.getString("STATUS"));
				ts.setTask_desc(rs.getString("TASK_DESC"));
				return ts;
			}
		});
	}
	
	@Override
	//,int impID实施人,int assId分配人
	public int insertTask(T_Task task,int impID,int assId) {
		String sql="insert into T_TASK(TASK_NAME,BEGIN_DATE,END_DATE,STATUS,IMPLEMENTOR_ID,ASSIGNER_ID,TASK_DESC) values(?,?,?,?,?,?,?)";
		return this.temp.update(sql, new Object[]{task.getTask_name(),new Timestamp(task.getBegin_date().getTime()),
				new Timestamp(task.getEnd_date().getTime()),"未实施",impID,assId,task.getTask_desc()});
	}
	
	@Override
	public int deleteTask(int taskid) {
		String sql="delete from T_TASK where task_id=?";
		return this.temp.update(sql, new Object[]{taskid});
	}
	
	@Override
	public int updateTask(T_Task task,int impid) {
		String sql="update T_TASK set task_name=?,begin_date=?,end_date=?,implementor_id=?,task_desc=? where task_id=?";
		return this.temp.update(sql, new Object[]{
				task.getTask_name(),new Timestamp(task.getBegin_date().getTime()),
				new Timestamp(task.getEnd_date().getTime()),impid,task.getTask_desc(),task.getTask_id()
		});
	}
	@Override
	public int updateTaskstatus(int empid, String status) {
		String sql="update T_TASK set STATUS=? where task_id=?";
		return this.temp.update(sql, new Object[]{status,empid});
	}
	
	





}
