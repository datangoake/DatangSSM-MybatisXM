package com.newer.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;






import com.newer.manager.service.TaskService;
import com.newer.mysm.data.dao.IPlanDAO;
import com.newer.mysm.data.dao.ITaskDAO;
import com.newer.mysm.data.dao.IempDAO;
import com.newer.mysm.data.dao.Tallyselectdao;
import com.newer.mysm.data.entity.T_Employee;
import com.newer.mysm.data.entity.T_Plan;
import com.newer.mysm.data.entity.T_Task;
@Service("taskservice")
public class TaskServiceImpl implements TaskService {

	@Resource(name="TaskDAO")
	ITaskDAO taskdao;
	@Resource(name="PlanDAO")
	IPlanDAO plandao;
	@Resource(name="EmpDAO")
	IempDAO empdao;
	@Resource(name="login")
	Tallyselectdao ctdao;
	@Override
	public List<T_Task> allTask(T_Employee emp,int pageNo, int pageSize,String status) {
		Map<String,Object> map=new HashMap<String, Object>();
		int startIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = pageNo * pageSize;
		map.put("startIndex", startIndex);
		map.put("endIndex", endIndex);
		map.put("status", status);
		map.put("emp", emp);
		return taskdao.selectAllTask(map);
	}
	@Override
	public T_Task selectAllTaskByID(int taskid) {
		return taskdao.selectAllTaskByID(taskid);
	}
	@Override
	public List<T_Plan> selectPlan(int taskid) {		
		return plandao.selectPlan(taskid);
	}
	@Override
	public T_Plan selectPlanById(int planid) {		
		return plandao.selectPlanById(planid);
	}
	@Override
	public List<T_Employee> selectEmpByRarntId(int rarntid,int pageNo, int pageSize) {
		return empdao.selectEmpByRarntId(rarntid, pageNo, pageSize);
	}
	@Override
	public int insertTask(T_Task task, int impID, int assId) {		
		return taskdao.insertTask(task, impID, assId);
	}
	@Override
	public int deleteTask(int taskid) {
		return this.taskdao.deleteTask(taskid);
	}
	@Override
	public int updateTask(T_Task task, int impid) {
		return this.taskdao.updateTask(task, impid);
	}
	@Override
	public T_Employee selectEmpById(int empid) {		
		return this.empdao.selectEmpById(empid);
	}
	@Override
	public int updateTaskstatus(int empid, String status) {		
		return this.taskdao.updateTaskstatus( status,empid);
	}
	@Override
	public int selectPlanCount(int taskid) {
		return this.plandao.selectPlanCount(taskid);
	}
	@Override
	public T_Employee checkLogin(String employee_name, String password) {
		return this.ctdao.selectEmployees(employee_name, password);
	}	
	
}
