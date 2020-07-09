package com.newer.mysm.service;

import java.util.List;
import java.util.Map;

import com.newer.mysm.data.util.TallyDTO;

public interface Itallyservice {

	public List selectbypage(Map<String,Object> map);
	public int getTatleCount(TallyDTO dto);
}
