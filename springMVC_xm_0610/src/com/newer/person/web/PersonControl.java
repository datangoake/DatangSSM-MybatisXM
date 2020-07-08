package com.newer.person.web;

import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newer.common.entry.*;
import com.newer.dto.PlanDTO;
import com.newer.person.data.util.PageBean;
import com.newer.person.data.util.StringUtil;
import com.newer.person.service.PersonService;

@Controller
public class PersonControl {

	public PersonControl() {
		System.out.println("控制器PersonControl实例化");
	}
	@ModelAttribute("plans")
	public T_Plan load() {
		return new T_Plan();
	}
	@Resource(name="PersonService")
	PersonService service;
	
	@RequestMapping("zhuxiao.do")
	public String ZhuXiao(HttpSession session){
		session.removeAttribute("employee");
		return "login.jsp";
	}
	
	@RequestMapping("DaTangLogin.do")
	public String empLogin(String employee_name, String password, Integer role_id,
			ModelMap map,HttpServletRequest request) {
		System.out.println("进入员工登录方法：empLogin");
		String nextAction = "";//跳转路径
		
		//使用业务层验证登录：
		T_Employee employee =  service.checkLogin(employee_name, password);//查询员工表（用户名/密码）-〉外键形式存在RoleId
		
		
		System.out.println("LoginController登录结果："+employee);
		if (employee != null) {
			//验证用户成功（数据库存在员工记录）
			if (employee.getRole().getRole_ID() == role_id) {//EmployeeForm表单提交的RoleID
				System.out.println("角色/用户名/密码");
				//用户名和密码及其角色相一致的登录成功员工保存在会话范围内！！！
				//session.setAttribute("employee", employee);
				request.getSession().setAttribute("employee", employee);
				System.out.println("当前存入会话的值+");
				String roleName = employee.getRole().getRole_NAME();
				if ("系统管理员".equals(roleName)) {
					nextAction = "redirect:/admin/welcome.jsp";
				} else if ("主管".equals(roleName)) {
					nextAction = "redirect:/manager/welcome.jsp";
				} else if ("员工".equals(roleName)) {
					System.out.println("普通员工");
					nextAction = "redirect:/person/welcome.jsp";
				}
			}else{
				map.put("message", "该用户无此角色");
				nextAction = "error.jsp";
			}
			
		}else{
			map.put("message", "登录失败，用户名和密码不一致");
			nextAction = "error.jsp";
		}
		return nextAction;
	}
	
	
	@RequestMapping("renwu.do")
	public String RenWuSelectById(String PageNo,String PageSize,HttpSession session){
		System.out.println("加载RenWuSelect方法根据员工编号查询任务");
		System.out.println("传递的页码："+PageNo);
		System.out.println("传递的数据量："+PageSize);
		int pageNo=1;
		int pageSize=3;
		if(!StringUtil.isBlank(PageNo)){
			pageNo = Integer.parseInt(PageNo);
		}
		if(!StringUtil.isBlank(PageSize)){
			pageSize = Integer.parseInt(PageSize);
		}
		System.out.println("当前页码："+pageNo);
		System.out.println("当前页的数据量："+pageSize);
		T_Employee emp = (T_Employee) session.getAttribute("employee");
		System.out.println("员工编号："+emp.getEmployee_ID()+"，员工姓名："+emp.getReal_name());
		List<T_Task> allRenWu = service.taskSelect(emp.getEmployee_ID(),pageNo,pageSize);
		PageBean<T_Task> page = new PageBean<T_Task>();
		page.setData(allRenWu);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalRecords(service.SelectZongShu(emp.getEmployee_ID()));
		System.out.println("根据员工编号查询任务总数："+service.SelectZongShu(emp.getEmployee_ID()));
		System.out.println("根据员工编号查询任务分页结果："+page.getData());
		session.setAttribute("renwuAll", page);
		return "redirect:/person/task.jsp";
	}
	
	@RequestMapping("renwuById.do")
	public String RenWuSelectById(Integer jihua_id,HttpSession session){
		T_Task task = service.taskSelectById(jihua_id);
		System.out.println("查询对象："+task.toString());
		session.setAttribute("taskById", task);
		session.setAttribute("taskid", task.getTask_id());
		return jiHuaSelect(jihua_id, session);
	}
	
	@RequestMapping("jihua.do")
	public String jiHuaSelect(int renwu_id,HttpSession session){
		System.out.println("加载jiHuaSelect方法");
		List<T_Plan> plan = service.planSelect(renwu_id);
		System.out.println("查询对象："+plan.toString());
		session.setAttribute("planAll", plan);
		return "redirect:/person/taskview.jsp";
	}
	
	@RequestMapping("xinjian.do")
	public String Xinjian(@ModelAttribute("plan") T_Plan plan,HttpSession seesion){
		T_Plan plans = new T_Plan();
		List<String> statAll = new ArrayList<String>();
		statAll.add("已完成");
		statAll.add("未完成");
		statAll.add("实施中");
		statAll.add("待实施");
		seesion.setAttribute("statAll", statAll);
		
		List<String> feedbackAll = new ArrayList<String>();
		feedbackAll.add("是");
		feedbackAll.add("否");
		seesion.setAttribute("feedbackAll", feedbackAll);
		return "person/newpro.jsp";
	}
	
	@RequestMapping("Insertjihua.do")
	public String jiHuaInsert(@ModelAttribute("plan") T_Plan plan,int task_id,HttpSession session){
		System.out.println("编号："+task_id);
		T_Task task = new T_Task();
		task.setTask_id(task_id);
		plan.setTask(task);
		plan.setStatus("未完成");
		plan.setIs_feedback("否");
		System.out.println("新建计划表单传递的参数："+plan.toString());
		int count = service.PlanInsert(plan);
		return RenWuSelectById(task_id, session);
	}
	
	@RequestMapping("StatsSelected.do")
	public String RenWuStatusUpdate(String PageNo,String PageSize,String statusValue,Integer jihua_id,HttpSession session){
		System.out.println(statusValue);
		System.out.println(jihua_id);
		System.out.println("加载");
		int pageNo=1;
		int pageSize=3;
		if(!StringUtil.isBlank(PageNo)){
			pageNo = Integer.parseInt(PageNo);
		}
		if(!StringUtil.isBlank(PageSize)){
			pageSize = Integer.parseInt(PageSize);
		}
		try {
			String value = "";
			if("1".equals(statusValue)){
				value = "未实施";
			}else if("2".equals(statusValue)){
				value = "实施中";
			}else if("3".equals(statusValue)){
				value = "已完成";
			}
			System.out.println(value);
			service.StatusUpdate(value, jihua_id);
			return RenWuSelectById(Integer.toString(pageNo),Integer.toString(pageSize),session);
		} catch (Exception e) {
			System.out.println("修改失败！！！");
			return RenWuSelectById(jihua_id, session);
		}
	}
	
	
	@RequestMapping("jihua_selectById.do")
	public String JiHuaSelectById(Integer jihuaId,HttpSession session){
		T_Plan planById = service.planSelectById(jihuaId);
		System.out.println("计划："+planById.toString());
		session.setAttribute("planById", planById);
		return JiHuaFrom();
	}
	
	@RequestMapping("jihua_from.do")
	public String JiHuaFrom(){
		T_Plan plan = new T_Plan();
		return "redirect:/person/feedback.jsp";
	}
	
	@RequestMapping("jihua_update.do")
	public String JiHuaUpdate(@ModelAttribute("plan") T_Plan plan,Integer jihua_id,Integer task_id,HttpSession session){
		T_Task task = new T_Task();
		task.setTask_id(jihua_id);
		plan.setTask(task);
		plan.setPlan_id(jihua_id);
		
		int count = service.planUpdate(plan);
		System.out.println(""+count+"修改："+plan);
		System.out.println(RenWuSelectById(task_id,session));
		return RenWuSelectById(task_id,session);
	}
	
	@RequestMapping("jihua_delete.do")
	public String JiHuaDelete(Integer jihua_id,Integer task_id,HttpSession session){
		if(jihua_id != null){
			int count = service.PlanDelete(jihua_id);
			System.out.println("删除结果："+count);
			return RenWuSelectById(task_id, session);
		}else{
			T_Task t_id = (T_Task) session.getAttribute("taskById");
			System.out.println("错误原因："+t_id.getTask_id());
			session.setAttribute("msg", "删除失败！");
			return "redirect:/person/error.jsp";
		}
		
	}
	
	
	@RequestMapping("jiazairenwu.do")
	public String JiaZaiRenWu(Integer peronId,HttpSession session){
		T_Employee emp = (T_Employee) session.getAttribute("employee");
		System.out.println("组合查询传递的员工编号："+emp.getEmployee_ID());
		List<T_Task> allrenwu = service.taskSelect(emp.getEmployee_ID(),1,100);
		System.out.println(allrenwu);
		List<String> renuw_all = new ArrayList<String>();
		for (T_Task string : allrenwu) {
			System.out.println("根据员工编号查询所属任务名称："+string.getTask_name());
			renuw_all.add(string.getTask_name());
		}
		session.setAttribute("renuw_all", renuw_all);
		return "redirect:/person/checkpro.jsp";
	}
	
	@RequestMapping("person/zuhe_select.do")
	public String JiHuaZuHeSelect(@ModelAttribute("plans") T_Plan plan,@RequestParam("select") String select,String PageNo,String PageSize,String beanFore,String beanAfter,
			String endFore,String endAfter,String flag,HttpSession session){
		System.out.println(plan.getPlan_name());
		T_Employee emp = (T_Employee) session.getAttribute("employee");
		System.out.println("组合查询传递的员工编号："+emp.getEmployee_ID());
		int pageNo=1;
		int pageSize=3;
		if(!StringUtil.isBlank(PageNo)){
			pageNo = Integer.parseInt(PageNo);
		}
		if(!StringUtil.isBlank(PageSize)){
			pageSize = Integer.parseInt(PageSize);
		}
		
		PlanDTO dto = null;
		if("1".equals(flag)){
			dto = new PlanDTO();
			if(!StringUtil.isBlank(plan.getPlan_name())){
				dto.setPlan_name(plan.getPlan_name());
			}
			if(!StringUtil.isBlank(select) && !select.equals("请选择")){
				dto.setTask_name(select);
			}
			if(!StringUtil.isBlank(plan.getIs_feedback())){
				dto.setFeedback_info(plan.getIs_feedback());
			}
			if(!StringUtil.isBlank(beanAfter) && !StringUtil.isBlank(beanFore)){
				dto.setBegin_date_befor(beanFore);
				dto.setBegin_date_after(beanAfter);
			}
			if(!StringUtil.isBlank(endFore) && !StringUtil.isBlank(endAfter)){
				dto.setEnd_date_befor(endFore);
				dto.setEnd_date_after(endAfter);
			}
			session.setAttribute("plan_dto", dto);
		}else{
			dto = (PlanDTO) session.getAttribute("plan_dto");
		}
		List<Object> allPlan = service.query(dto, pageNo, pageSize, emp.getEmployee_ID());
		System.out.println("当前高级组合查询的数据"+allPlan);
		PageBean<Object> page = new PageBean<Object>();
		page.setData(allPlan);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalRecords(service.Count(dto, emp.getEmployee_ID()));
		session.setAttribute("page", page);
		return "redirect:/person/checkpro.jsp";
	}
	
	@RequestMapping("person/zuhe_select2.do")
	public String JiHuaZuHeSelect2(String PageNo,String PageSize,HttpSession session){
		T_Employee emp = (T_Employee) session.getAttribute("employee");
		System.out.println("组合查询传递的员工编号："+emp.getEmployee_ID());
		int pageNo=1;
		int pageSize=3;
		if(!StringUtil.isBlank(PageNo)){
			pageNo = Integer.parseInt(PageNo);
		}
		if(!StringUtil.isBlank(PageSize)){
			pageSize = Integer.parseInt(PageSize);
		}
		
		PlanDTO dto = null;
			dto = (PlanDTO) session.getAttribute("plan_dto");
		List<Object> allPlan = service.query(dto, pageNo, pageSize, emp.getEmployee_ID());
		System.out.println("当前高级组合查询的数据"+allPlan);
		PageBean<Object> page = new PageBean<Object>();
		page.setData(allPlan);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalRecords(service.Count(dto, emp.getEmployee_ID()));
		session.setAttribute("page", page);
		return "redirect:/person/checkpro.jsp";
	}
}
