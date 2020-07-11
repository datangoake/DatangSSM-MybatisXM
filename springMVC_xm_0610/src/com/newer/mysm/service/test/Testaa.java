package com.newer.mysm.service.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.newer.dto.PlanDTO;
import com.newer.mysm.data.dao.Tallyselectdao;
import com.newer.mysm.data.entity.T_Employee;
import com.newer.mysm.data.entity.T_Plan;
import com.newer.mysm.data.entity.T_Role;
import com.newer.mysm.data.entity.T_Task;
import com.newer.mysm.data.entity.Tally;
import com.newer.mysm.data.util.TallyDTO;
import com.newer.mysm.data.util.gaojifenye;
import com.newer.mysm.service.Itallyservice;




@RunWith(SpringJUnit4ClassRunner.class)//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Testaa {
	
	@Resource(name="tallyservice")
	Itallyservice dao;
	
	@Test
	 public void testAll() {
		T_Role role=new T_Role();
		T_Employee emp=new T_Employee();
		T_Plan plan=new T_Plan();
		T_Task task=new T_Task();
		List<T_Task> list=new ArrayList<T_Task>();
//		list=dao.taskSelect(12, 1, 2);
//		System.out.println("1231123123"+list.toString());
//		task=dao.taskSelectById(4);
//		System.out.println(task.toString());
//		role=dao.roleSelect(2);
//		emp=dao.personSelect(1);
//			T_Plan plan=new T_Plan();
//			plan.setPlan_id(8);
//			T_Task task=new T_Task();
//			task.setTask_id(5);
//			plan.setPlan_name("计划6_2");
//		plan.setStatus("已完成");
//			plan.setTask(task);
//			plan.setBegin_date(new Timestamp(new Date().getTime()));
//			plan.setEnd_date(new Timestamp(new Date().getTime()));
//			plan.setFeedback_info("计划6_2顺利完成");
//		plan.setIs_feedback("是");
//			plan.setPlan_desc("aaa");
//			int count=dao.PlanInsert(plan);
//			System.out.println(count);
//			int a=dao.planUpdate(plan);
//			System.out.println(a);
//			int a=dao.jihuaCount(5);
//			System.out.println(a);
//			int a=dao.StatusUpdate("bbb", 5);
//System.out.println(a);
//			List<T_Plan> list=new ArrayList<T_Plan>();
			
//			task=dao.taskSelectById(1);
//			System.out.println(task.toString());
//		List<T_Task> list=new ArrayList<T_Task>();
//		list=dao.taskSelect(12, 1, 2);
//		System.out.println(list.toString());
//		List<T_Employee> list =new ArrayList<T_Employee>();
//		list =dao.selectEmployees("lisi", "123456");
//		System.out.println(list.toString());
//		int a=dao.Count(12);
//		System.out.println(a);
//		List<gaojifenye> list=new ArrayList<gaojifenye>();
		PlanDTO dto=new PlanDTO();
		List<gaojifenye> list2=new ArrayList<gaojifenye>();
		int pageNo = 1;
		int pageSize = 2;
		dto.setTask_name("任务5");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("dto", dto);
		int start = (pageNo - 1) * pageSize + 1;
		int end = pageNo * pageSize;
		map.put("startIndex", start);
		map.put("endIndex", end);
		map.put("id", 12);
		list2=dao.query(map);
		System.out.println("aaaa"+list2.toString());
//		Map<String, Object> map=new HashMap<String, Object>();
//		dto.setPlan_name("计划6_2");
//		map.put("id", 12);
//		map.put("dto", dto);
//		int a=dao.Count(map);
//		System.out.println(dto.toString());
//		System.out.println(a);
//		int a=dao.SelectZongShu(12);
//		System.out.println(a);
	}
	
	
}
