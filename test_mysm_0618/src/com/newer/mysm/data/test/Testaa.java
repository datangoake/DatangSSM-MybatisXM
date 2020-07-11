package com.newer.mysm.data.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.newer.mysm.data.dao.IPlanDAO;
import com.newer.mysm.data.dao.ITaskDAO;
import com.newer.mysm.data.dao.IempDAO;
import com.newer.mysm.data.entity.T_Employee;


@RunWith(SpringJUnit4ClassRunner.class)//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Testaa {
//	@Resource(name="EmpDAO")
	//@Resource(name="PlanDAO")
	@Resource(name="TaskDAO")
	//@Resource(name="talldao")
	//empdao empdao;
	//IPlanDAO plandao;
	ITaskDAO taskdao;
//	IempDAO empdao;
	@Test
	 public void testAll() {
		T_Employee emp=new T_Employee();
		emp.setEmployee_ID(8);
		int startIndex =1;
		int endIndex=4;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("startIndex", startIndex);
		map.put("endIndex", endIndex);
		map.put("emp", emp);
		System.out.println(taskdao.selectAllTask(map));
//		   T_Employee EmpUsers = empdao.selectEmpById(11);
//		   System.out.println("JUnit测试DAO返回所有用户："+EmpUsers.toString());
		  //List<T_Plan> allplan= plandao.selectPlan(1);
		 // System.out.println("根据Id查询到的当前任务为"+allplan.toString());
		 //int count=plandao.selectPlanCount(1);
		 //System.out.println("根据ID查询总数量为"+count);
		// T_Plan Planuser=plandao.selectPlanById(1);
		// System.out.println("根据ID查询计划的详情信息为"+Planuser.toString());
//		T_Task ta=new T_Task();
//		T_Employee emp1=new T_Employee();
//		T_Employee emp2=new T_Employee();
//		ta.setTask_name("任务7");
//		ta.setBegin_date(new Timestamp(new Date().getTime()));
//		ta.setEnd_date(new Timestamp(new Date().getTime()));
//		emp1.setEmployee_ID(12);
//		emp2.setEmployee_ID(8);
//		ta.setMp_emp(emp1);
//		ta.setAs_emp(emp2);
//		ta.setStatus("实施中");
//		ta.setTask_desc("测试");
//		int count= taskdao.insertTask(ta, 12, 8);
//		System.out.println("添加成功行数"+count);
//		System.out.println("用户信息"+ta.toString());
		//int count=taskdao.deleteTask(111);
		//System.out.println("删除成功的数量"+count);
		//T_Task ta=new T_Task();
		//T_Employee emp1=new T_Employee();
//		task_name='母狗',begin_date=to_date('2020-10-16','yyyy-mm-dd'),
//				end_date=to_date('2021-10-16','yyyy-mm-dd'),implementor_id=10,
//				task_desc='测试' where task_id=99
//		ta.setTask_id(99);
//		ta.setTask_name("任务8");
//		ta.setBegin_date(new Timestamp(new Date().getTime()));
//		ta.setEnd_date(new Timestamp(new Date().getTime()));
//		emp1.setEmployee_ID(7);
//		ta.setMp_emp(emp1);
//		ta.setTask_desc("测试2");
//		int count=taskdao.updateTask(ta,7);
//		System.out.println("修改成功后的数量"+count);
		//int count=taskdao.updateTaskstatus("实施中",99);
		//System.out.println("修改成功后的数量"+count);
		
//		ta=taskdao.selectAllTaskByID(99);
//		System.out.println("根据ID查询到的信息为"+ta.toString());
	}
	
	
	
}
