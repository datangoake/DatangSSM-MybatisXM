package com.newer.mysm.web.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newer.mysm.data.entity.Tally;
import com.newer.mysm.data.util.TallyDTO;
import com.newer.mysm.data.util.pageBean;
import com.newer.mysm.service.Itallyservice;

@Controller
public class UsersControl {
	//业务层
	@Resource(name="tallyservice")
	Itallyservice service;
	
	private boolean isBlank(String s) {
		return s == null || "".equals(s.trim());
	}
	
	
	//全部查询所有用户信息打印在JSP页面上
	@RequestMapping(value="/selectpage.do")
	public void findAll(@ModelAttribute("form") TallyDTO dto,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		int pageNo = 1;
		int pageSize = 2;
		if (!isBlank(request.getParameter("pageNo")))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		if (!isBlank(request.getParameter("pageSize")))
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		System.out.println("dto"+dto.toString());
		Map<String, Object> hashMap=new HashMap<String, Object>();
		hashMap.put("dto", dto);
		System.out.println("数据"+hashMap.toString());
		int start = (pageNo - 1) * pageSize + 1;
		int end = pageNo * pageSize;
		hashMap.put("startIndex", start);
		hashMap.put("endIndex", end);
		
		
	    List<Tally> data=service.selectbypage(hashMap);
	    
	    
		pageBean<Tally> pb=new pageBean<Tally>();
		pb.setData(data);
		pb.setPageNo(pageNo);
		pb.setPageSize(pageSize);
		pb.setTotalRecords(service.getTatleCount(dto));
		session.setAttribute("pb", pb);
		session.setAttribute("dto", dto);
		response.sendRedirect("showall.jsp");
	}
	

}







