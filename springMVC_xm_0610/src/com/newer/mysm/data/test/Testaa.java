package com.newer.mysm.data.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.newer.mysm.data.entity.Tally;
import com.newer.mysm.data.util.TallyDTO;
import com.newer.mysm.service.Itallyservice;




@RunWith(SpringJUnit4ClassRunner.class)//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Testaa {
	
	@Resource(name="tallyservice")
	Itallyservice service;
	
	@Test
	 public void testAll() {
		
//		
//		Map<String,Object> hashMap=new HashMap<String, Object>();
//		TallyDTO dto=new TallyDTO();
//		dto.setSearch_tallyid(2);
//		int pageNo=1;
//		int pageSize=2;
//		int startIndex=(pageNo - 1) * pageSize + 1;
//		int endIndex=pageNo * pageSize;
//		
//		hashMap.put("dto", dto);
//		hashMap.put("startIndex", startIndex);
//		hashMap.put("endIndex", endIndex);
//		
//		List<Tally> list=service.selectbypage(hashMap);
//		System.out.println(list.toString());
//		
//	}
	
	
}
}