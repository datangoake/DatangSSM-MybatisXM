package com.newer.manager.serviceTest;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.newer.manager.service.TaskService;
import com.newer.mysm.data.dao.IempDAO;
import com.newer.mysm.data.entity.T_Employee;
import com.newer.mysm.data.entity.T_Plan;
import com.newer.mysm.data.entity.T_Task;

@RunWith(SpringJUnit4ClassRunner.class)//��ʾ�̳���SpringJUnit4ClassRunner��
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class serviceTest {
	@Resource(name="taskservice")
	//@Resource(name="EmpDAO")
	TaskService serv;
	//IempDAO empdao;
	@Test
	public void serviceTest() {
		int count=0;
		  // T_Employee EmpUsers = serv.selectEmpById(11);
		   //System.out.println("JUnit����DAO���������û���"+EmpUsers.toString());
		   //List<T_Plan> all=serv.selectPlan(1);
		   //System.out.println("JUnit���Ը���id���ҵ�ǰ����ļƻ���"+all.toString());
			//count=serv.selectPlanCount(1);
			//System.out.println("JUnit���Ը���id���ҵ�ǰ����ļƻ�������"+count);
			T_Plan plan =new T_Plan();
			plan=serv.selectPlanById(1);
			System.out.println("JUnit���Ը���id���ҵ�ǰ����ļƻ�������"+plan.toString());

	}
}
