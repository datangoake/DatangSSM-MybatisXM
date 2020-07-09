package com.newer.mysm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newer.mysm.data.dao.Tallyselectdao;
import com.newer.mysm.data.util.TallyDTO;
import com.newer.mysm.service.Itallyservice;
@Service("tallyservice")
public class tallyserviceimpl implements Itallyservice {

	@Resource(name="talldao")
	Tallyselectdao dao;
	@Override
	public List selectbypage(Map<String, Object> map) {
		return dao.selectall(map);
	}

	@Override
	public int getTatleCount(TallyDTO dto) {
		return dao.getCount(dto);
	}



}
