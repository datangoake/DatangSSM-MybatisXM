package com.newer.person.data.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.newer.common.entry.*;
import com.newer.dto.PlanDTO;
import com.newer.person.data.dao.PersonDao;
import com.newer.person.data.util.DBUtil;
import com.newer.person.data.util.StringUtil;

@Component("PersonDao")

public class PersonDaoImpl implements PersonDao{
	
	JdbcTemplate temp = new JdbcTemplate(DBUtil.getDataSource());
	
	//查询用户一条记录
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#roleSelect(int)
	 */
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.impl.PersonDao#roleSelect(int)
	 */
	@Override
	public T_Role roleSelect(int id){
		
		T_Role role = (T_Role) temp.queryForObject("select * from t_role where role_id=?", new Object[]{id}, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Role roles = new T_Role();
				roles.setRole_ID(rs.getInt("role_ID"));
				roles.setRole_NAME(rs.getString("role_NAME"));
				roles.setRole_DESC(rs.getString("role_DESC"));
				return roles;
			}
		});
		return role;
	}
	
	//查询员工一条记录
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#personSelect(int)
	 */
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.impl.PersonDao#personSelect(int)
	 */
	@Override
	public T_Employee personSelect(int id){
		T_Employee taskAll = (T_Employee) temp.queryForObject("select * from t_employee where employee_id=?", new Object[]{id}, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Employee emp = new T_Employee();
				emp.setEmployee_ID(rs.getInt("employee_ID"));
				emp.setEmployee_name(rs.getString("employee_name"));
				emp.setPassword(rs.getString("password"));
				emp.setReal_name(rs.getString("real_name"));
				emp.setSex(rs.getString("sex"));
				emp.setBirthday(rs.getTimestamp("birthday"));
				emp.setDuty(rs.getString("duty"));
				emp.setEnrolldate(rs.getTimestamp("enrolldate"));
				emp.setEducation(rs.getString("education"));
				emp.setMajor(rs.getString("major"));
				emp.setExperience(rs.getString("experience"));
				emp.setRole(new PersonDaoImpl().roleSelect(rs.getInt("role_id")));
				T_Employee empParente = new T_Employee();
				empParente.setEmployee_ID(rs.getInt("parent_id"));
				emp.setEmp(empParente);
				return emp;
			}
		});
		return taskAll;
	}
	
	//查询任务表一条记录
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#taskSelect(int)
	 */
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.impl.PersonDao#taskSelect(int)
	 */
	@Override
	public T_Task taskSelectById(int id){
		T_Task task = (T_Task) temp.queryForObject("select * from t_task where task_id=?", new Object[]{id}, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Task tasks = new T_Task();
				tasks.setTask_id(rs.getInt("task_id"));
				tasks.setTask_name(rs.getString("task_name"));
				tasks.setBegin_date(rs.getTimestamp("begin_date"));
				tasks.setEnd_date(rs.getTimestamp("end_date"));
				tasks.setReal_begin_date(rs.getTimestamp("real_begin_date"));
				tasks.setReal_end_date(rs.getTimestamp("real_end_date"));
				tasks.setStatus(rs.getString("status"));
				tasks.setAs_emp(new PersonDaoImpl().personSelect(rs.getInt("implementor_id")));
				tasks.setMp_emp(new PersonDaoImpl().personSelect(rs.getInt("assigner_id")));
				return tasks;
			}
		});
		return task;
	}
	
	
	//任务查询根据员工编号
		/* (non-Javadoc)
		 * @see com.newer.person.data.dao.PersonDao#taskSelect()
		 */
		/* (non-Javadoc)
		 * @see com.newer.person.data.dao.impl.PersonDao#taskSelect()
		 */
		@Override
		@SuppressWarnings("unchecked")
		public List<T_Task> taskSelect(int implementorId,int pageNo, int pageSize){
			int start = (pageNo -1) * pageSize +1;
			int end = pageNo * pageSize;
			List<T_Task> personAll = null;	
			String sql = "select * from(select row_number() over(order by t.task_id desc) no,t.* from t_task t where implementor_id = ? )temp where temp.no between ? and ?";
			List<Object> parmas = new ArrayList<Object>();
			parmas.add(implementorId);
			parmas.add(start);
			parmas.add(end);
			personAll = temp.query(sql, parmas.toArray(), new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					T_Task task = new T_Task();
					task.setTask_id(rs.getInt("task_id"));
					task.setTask_name(rs.getString("task_name"));
					task.setBegin_date(rs.getTimestamp("begin_date"));
					task.setEnd_date(rs.getTimestamp("end_date"));
					task.setReal_begin_date(rs.getTimestamp("real_begin_date"));
					task.setReal_end_date(rs.getTimestamp("real_end_date"));
					task.setStatus(rs.getString("status"));
					task.setAs_emp(new PersonDaoImpl().personSelect(rs.getInt("implementor_id")));
					task.setMp_emp(new PersonDaoImpl().personSelect(rs.getInt("assigner_id")));
					task.setTask_desc(Integer.toString(new PersonDaoImpl().jihuaCount(rs.getInt("task_id"))));
					return task;
				}
			});
			System.out.println(sql);
			
			System.out.println("分页："+parmas.toString());
			return personAll;
		}
	
		@Override
		public int SelectZongShu(String sql, List<Object> params) {
			System.out.println("dao层："+sql+",参数："+params);
			return temp.queryForInt(sql, params.toArray());
		}
	//计划查询根据任务编号
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#planSelect()
	 */
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.impl.PersonDao#planSelect()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T_Plan> planSelect(int taskId){
		List<T_Plan> planAll = null;
		
		planAll = temp.query("select * from t_plan where task_id=?",  new Object[]{taskId}, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Plan plan = new T_Plan();
				plan.setPlan_id(rs.getInt("plan_id"));
				plan.setPlan_name(rs.getString("plan_name"));
				plan.setStatus(rs.getString("status"));
				plan.setIs_feedback(rs.getString("is_feedback"));
				plan.setBegin_date(rs.getTimestamp("begin_date"));
				plan.setEnd_date(rs.getTimestamp("end_date"));
				plan.setTask(new PersonDaoImpl().taskSelectById(rs.getInt("task_id")));
				plan.setFeedback_info(rs.getString("feedback_info"));
				plan.setPlan_desc(rs.getString("plan_desc"));
				return plan;
			}
		});
		
		return planAll;
	}
	
	//新增员工
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#personInsert(com.newer.common.entry.T_Employee)
	 */
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.impl.PersonDao#personInsert(com.newer.common.entry.T_Employee)
	 */
	@Override
	public int PlanInsert(T_Plan plan){
		return temp.update("insert into t_plan(plan_name,status,is_feedback,begin_date,end_date,task_id,feedback_info,plan_desc) values(?,?,?,?,?,?,?,?)", new Object[]{
				plan.getPlan_name(),
				plan.getStatus(),
				plan.getIs_feedback(),
				plan.getBegin_date(),
				plan.getEnd_date(),
				plan.getTask().getTask_id(),
				plan.getFeedback_info(),
				plan.getPlan_desc()
		});
	}
	
	//删除计划
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#personDelete(int)
	 */
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.impl.PersonDao#personDelete(int)
	 */
	@Override
	public int PlanDelete(int id){
		return temp.update("delete from t_plan where plan_id=?", new Object[]{id});
	}
	
	//修改计划
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#planUpdate(com.newer.common.entry.T_Plan)
	 */
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.impl.PersonDao#planUpdate(com.newer.common.entry.T_Plan)
	 */
	@Override
	public int planUpdate(T_Plan plan){
		return temp.update("update t_plan set plan_name=?,plan_desc=?,begin_date=?,end_date=?,status=?,is_feedback=?,feedback_info=? where plan_id=?", new Object[]{
				plan.getPlan_name(),
				plan.getPlan_desc(),
				plan.getBegin_date(),
				plan.getEnd_date(),
				plan.getStatus(),
				plan.getIs_feedback(),
				plan.getFeedback_info(),
				plan.getPlan_id()
		});
	}
	
	//查询计划数量
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.impl.PersonDao#jihuaCount(int)
	 */
	@Override
	public int jihuaCount(int rnwuId){
		return temp.queryForInt("select count(*) from t_plan where task_id = ?", new Object[]{rnwuId});
	}

	@Override
	public int StatusUpdate(String statu,int renwuId) {
		return temp.update("update t_task set status=? where task_id=?", new Object[]{statu,renwuId});
	}

	@Override
	public T_Plan planSelectById(int id) {
		T_Plan plans = (T_Plan) temp.queryForObject("select * from t_plan where plan_id=?", new Object[]{id}, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Plan plan = new T_Plan();
				plan.setPlan_id(rs.getInt("plan_id"));
				plan.setPlan_name(rs.getString("plan_name"));
				plan.setStatus(rs.getString("status"));
				plan.setIs_feedback(rs.getString("is_feedback"));
				plan.setBegin_date(rs.getTimestamp("begin_date"));
				plan.setEnd_date(rs.getTimestamp("end_date"));
				plan.setTask(new PersonDaoImpl().taskSelectById(rs.getInt("task_id")));
				plan.setFeedback_info(rs.getString("feedback_info"));
				plan.setPlan_desc(rs.getString("plan_desc"));
				return plan;
			}
		});
		return plans;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object> query(PlanDTO dto, int pageNo, int pageSize,int id) {
		List<Object> all = null;
		int start = (pageNo -1) * pageSize +1;
		int end = pageNo * pageSize;
		String sql = "select * from (select row_number() over(order by p.plan_id) no,p.*,e.employee_id,r.role_id,t.task_name from t_plan p join t_task t on p.task_id=t.task_id join t_employee e on e.employee_id=t.implementor_id join t_role r on r.role_id=e.role_id where 1=1 and employee_id=? ";
		List<Object> parmas = new ArrayList<Object>();
		parmas.add(id);
		if(dto != null){
			if(!StringUtil.isBlank(dto.getPlan_name())){
				sql += " and plan_name = ? ";
				parmas.add(dto.getPlan_name());
			}
			if(!StringUtil.isBlank(dto.getTask_name())){
				sql += " and task_name = ? ";
				parmas.add(dto.getTask_name());
			}
			String bean_time_befor = dto.getBegin_date_befor();
			String bean_time_after = dto.getBegin_date_after();
			String end_time_befor = dto.getEnd_date_befor();
			String end_time_after = dto.getEnd_date_after();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(!StringUtil.isBlank(bean_time_befor) && !StringUtil.isBlank(bean_time_after)){
				try {
					Date bean_befor = sdf.parse(bean_time_befor);
					Date bean_after = sdf.parse(bean_time_after);
					sql += " and p.begin_date between ? and ? ";
					parmas.add(bean_befor);
					parmas.add(bean_after);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(!StringUtil.isBlank(end_time_befor) && !StringUtil.isBlank(end_time_after)){
				try {
					Date end_befor = sdf.parse(end_time_befor);
					Date end_after = sdf.parse(end_time_after);
					sql += " and p.end_date between ? and ? ";
					parmas.add(end_befor);
					parmas.add(end_after);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(!StringUtil.isBlank(dto.getFeedback_info())){
				sql += " and is_feedback = ? ";
				parmas.add(dto.getFeedback_info());
			}
		}
		sql+=")temp where temp.no between ? and ?";
		parmas.add(start);
		parmas.add(end);
		System.out.println("当前高级组合查询sql"+sql);
		all = temp.query(sql, parmas.toArray(), new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				T_Plan plan = new T_Plan();
				plan.setPlan_id(rs.getInt("plan_id"));
				plan.setPlan_name(rs.getString("plan_name"));
				plan.setStatus(rs.getString("status"));
				plan.setIs_feedback(rs.getString("is_feedback"));
				plan.setBegin_date(rs.getTimestamp("begin_date"));
				plan.setEnd_date(rs.getTimestamp("end_date"));
				plan.setTask(new PersonDaoImpl().taskSelectById(rs.getInt("task_id")));
				plan.setFeedback_info(rs.getString("feedback_info"));
				plan.setPlan_desc(rs.getString("plan_desc"));
				return plan;
			}
		});
		return  all ;
	}

	@Override
	public int Count(PlanDTO dto,int id) {
		String sql = "select count(*) from t_plan p join t_task t on p.task_id=t.task_id join t_employee e on e.employee_id=t.implementor_id join t_role r on r.role_id=e.role_id where 1=1 and employee_id=?";
		List<Object> parmas = new ArrayList<Object>();
		parmas.add(id);
		if(dto != null){
			if(!StringUtil.isBlank(dto.getPlan_name())){
				sql += " and plan_name = ? ";
				parmas.add(dto.getPlan_name());
			}
			if(!StringUtil.isBlank(dto.getTask_name())){
				sql += " and task_name = ? ";
				parmas.add(dto.getTask_name());
			}
			String bean_time_befor = dto.getBegin_date_befor();
			String bean_time_after = dto.getBegin_date_after();
			String end_time_befor = dto.getEnd_date_befor();
			String end_time_after = dto.getEnd_date_after();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(!StringUtil.isBlank(bean_time_befor) && !StringUtil.isBlank(bean_time_after)){
				try {
					Date bean_befor = sdf.parse(bean_time_befor);
					Date bean_after = sdf.parse(bean_time_after);
					sql += " and p.begin_date between ? and ? ";
					parmas.add(bean_befor);
					parmas.add(bean_after);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(!StringUtil.isBlank(end_time_befor) && !StringUtil.isBlank(end_time_after)){
				try {
					Date end_befor = sdf.parse(end_time_befor);
					Date end_after = sdf.parse(end_time_after);
					sql += " and p.end_date between ? and ? ";
					parmas.add(end_befor);
					parmas.add(end_after);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(!StringUtil.isBlank(dto.getFeedback_info())){
				sql += " and feedback_info = ? ";
				parmas.add(dto.getFeedback_info());
			}
		}
		
		return temp.queryForInt(sql, parmas.toArray());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T_Employee> selectEmployees(String sql, Object[] params) {
		// TODO Auto-generated method stub
		return this.temp.query
				(sql, params,new RowMapper() {
					
					@Override
					public Object mapRow(ResultSet rs, int index) throws SQLException {
						T_Employee employee = new T_Employee();
						employee.setEmployee_ID(rs.getInt("employee_id"));
						employee.setEmployee_name(rs.getString("employee_name"));
						employee.setPassword(rs.getString("password"));
						employee.setReal_name(rs.getString("real_name"));
						employee.setSex(rs.getString("sex"));
						employee.setBirthday(rs.getTimestamp("birthday"));
						employee.setDuty(rs.getString("duty"));
						employee.setEnrolldate(rs.getTimestamp("enrolldate"));
						employee.setEducation(rs.getString("education"));
						employee.setMajor(rs.getString("major"));
						employee.setExperience(rs.getString("experience"));
						employee.setRole
						(new PersonDaoImpl().roleSelect(rs.getInt("role_id")));
						//根据parentId查询关联的上司员工
						T_Employee parentemployee = new T_Employee();
						employee.setEmp(parentemployee);
						
						return employee;
					}
				});
	}
	
}