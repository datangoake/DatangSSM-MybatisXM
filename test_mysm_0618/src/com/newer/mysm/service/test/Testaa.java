package com.newer.mysm.service.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.newer.mysm.data.dao.Tallyselectdao;
import com.newer.mysm.data.entity.T_Role;
import com.newer.mysm.data.entity.Tally;
import com.newer.mysm.data.util.TallyDTO;




@RunWith(SpringJUnit4ClassRunner.class)//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Testaa {
	
	@Resource(name="talldao")
	Tallyselectdao dao;
	
	@Test
	 public void testAll() {
//		T_Role role=new T_Role();
//		Map<String,Object> parme=new HashMap<String, Object>();
//		parme.put("startIndex",1);
//		parme.put("ednIndex",2);
//		parme.put("dto",new TallyDTO());
//		List<Tally> list=dao.selectall(parme);
//		System.out.println(list.toString());
		T_Role role=new T_Role();
		role=dao.roleSelect(2);
		System.out.println(role.toString());

	}
	
	
}
