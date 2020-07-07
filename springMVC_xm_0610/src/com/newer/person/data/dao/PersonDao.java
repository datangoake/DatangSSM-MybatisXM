package com.newer.person.data.dao;

import java.util.List;

import com.newer.common.entry.*;
import com.newer.dto.PlanDTO;

public interface PersonDao {

	//查询用户一条记录
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#roleSelect(int)
	 */
	public abstract T_Role roleSelect(int id);

	//查询员工一条记录
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#personSelect(int)
	 */
	public abstract T_Employee personSelect(int id);

	//查询任务表一条记录
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#taskSelect(int)
	 */
	public abstract T_Task taskSelectById(int id);
	
	//查询计划表一条记录
	public abstract T_Plan planSelectById(int id);

	//任务查询根据员工编号
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#taskSelect()
	 */
	public abstract List<T_Task> taskSelect(int implementorId,int pageNo, int pageSize);

	//计划查询根据任务编号
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#planSelect()
	 */
	public abstract List<T_Plan> planSelect(int taskId);

	//新增员工
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#personInsert(com.newer.common.entry.T_Employee)
	 */
	public abstract int PlanInsert(T_Plan plan);

	//删除计划
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#personDelete(int)
	 */
	public abstract int PlanDelete(int id);

	//修改计划
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#planUpdate(com.newer.common.entry.T_Plan)
	 */
	public abstract int planUpdate(T_Plan plan);

	//查询计划数量
	public abstract int jihuaCount(int rnwuId);
	
	//修改任务状态
	public abstract int StatusUpdate(String statu,int renwuId);
	
	//计划组合查询
	public abstract List<Object> query(PlanDTO dto,int pageNo,int pageSize,int id);
	
	//获取总条数
	public abstract int Count(PlanDTO dto,int id);
	
	//登录查询
	public abstract List<T_Employee> selectEmployees(String sql, Object[] params);
	
	//获取总条数
	public abstract int SelectZongShu(String sql, List<Object> params);
}