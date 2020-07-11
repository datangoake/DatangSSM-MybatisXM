package com.newer.mysm.service;

import java.util.List;
import java.util.Map;

import com.newer.mysm.data.entity.T_Employee;
import com.newer.mysm.data.entity.T_Plan;
import com.newer.mysm.data.entity.T_Role;
import com.newer.mysm.data.entity.T_Task;
import com.newer.dto.PlanDTO;
import com.newer.mysm.data.util.TallyDTO;
import com.newer.mysm.data.util.gaojifenye;

public interface Itallyservice {

//	public List selectbypage(Map<String,Object> map);
//	public int getTatleCount(TallyDTO dto);
	//查询用户一条记录
		public abstract T_Role roleSelect(int id);
		
		//查询员工一条记录
		public abstract T_Employee personSelect(int id);

		//查询任务表一条记录
		public abstract T_Task taskSelectById(int id);

		//任务全部查询
		public abstract List<T_Task> taskSelect(int implementorId,int pageNo, int pageSize);

		//计划全部查询
		public abstract List<T_Plan> planSelect(int taskId);

		//新建计划
		public abstract int PlanInsert(T_Plan plan);

		//删除计划
		public abstract int PlanDelete(int id);

		//修改计划
		public abstract int planUpdate(T_Plan plan);
		
		//根据任务编号查询计划数量
		public abstract int jihuaCount(int rnwuId);
		
		//修改任务状态
		public abstract int StatusUpdate(String statu,int renwuId);
		
		//查询计划表一条记录
		public abstract T_Plan planSelectById(int id);
		
		//计划组合查询
		public abstract List<gaojifenye> query(Map<String, Object>map);
			
		//获取总条数
		public abstract int Count(Map<String, Object> map);
		
		//登录查询
		public abstract T_Employee checkLogin(String employeeName, String password);
		//获取总条数
			public abstract int SelectZongShu(int id);
}
