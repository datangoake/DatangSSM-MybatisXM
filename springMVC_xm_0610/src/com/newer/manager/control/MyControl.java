package com.newer.manager.control;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newer.manager.service.TaskService;
import com.newer.mysm.data.entity.T_Employee;
import com.newer.mysm.data.entity.T_Task;
import com.newer.mysm.data.util.StringUtil;
import com.newer.mysm.data.util.pageBean;



@Controller
public class MyControl {
	
	@Resource(name="taskservice")
	TaskService tasks;
	
	@ModelAttribute("task")
	public T_Task load(){
		return new T_Task();
	};
	
	@RequestMapping("DaTangLogin.do")
	public String empLogin(String employee_name, String password, Integer role_id,
			ModelMap map,HttpServletRequest request) {
		System.out.println("进入员工登录方法：empLogin");
		String nextAction = "";//跳转路径
		
		//使用业务层验证登录：
		
		T_Employee employee =tasks.checkLogin(employee_name, password);//查询员工表（用户名/密码）-〉外键形式存在RoleId
		
		
		System.out.println("LoginController登录结果："+employee);
		if (employee != null) {
			//验证用户成功（数据库存在员工记录）
			if (employee.getRole().getRole_ID() == role_id) {//EmployeeForm表单提交的RoleID
				System.out.println("角色/用户名/密码");
				//用户名和密码及其角色相一致的登录成功员工保存在会话范围内！！！
				//session.setAttribute("employee", employee);
				request.getSession().setAttribute("employee", employee);
				
				int roleName =employee.getRole().getRole_ID();
				System.out.println("当前存入会话的值+"+roleName);
				if (2==roleName) {
					nextAction = "redirect:/admin/welcome.jsp";
				} else if (3==roleName) {
					nextAction = "redirect:/manager/welcome.jsp";
				} else if (4==roleName) {
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
	//人员管理
	@RequestMapping("manager/lgemp.do")
	public void allemp(String PageNo,String PageSize,HttpServletRequest request,HttpServletResponse response) throws IOException {
		int pageNo=1;
		int pageSize=3;
		if(!StringUtil.isBlank(PageNo)){
			pageNo = Integer.parseInt(PageNo);
		}
		if(!StringUtil.isBlank(PageSize)){
			pageSize = Integer.parseInt(PageSize);
		}
		T_Employee emp = (T_Employee) request.getSession().getAttribute("employee");
		
		List<T_Employee> allemp = this.tasks.selectEmpByRarntId(emp.getEmployee_ID(),pageNo,pageSize);
		pageBean<T_Employee> page = new pageBean<T_Employee>();
		page.setData(allemp);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalRecords(this.tasks.selectEmpByRarntId(emp.getEmployee_ID(),1,100).size());
		System.out.println("根据员工编号查询任务总数："+this.tasks.selectEmpByRarntId(emp.getEmployee_ID(),1,100).size());
		System.out.println("根据员工编号查询任务分页结果："+page.getData());
//		T_Employee emp=new T_Employee();
//		emp.setEmployee_ID(8);
		request.getSession().setAttribute("allemp", page);
		response.sendRedirect("checkper.jsp");
	}
	
	//查看任务
	@RequestMapping("manager/lgtask.do")
	public void alltask(String PageNo,String PageSize,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		int pageNo=1;
		int pageSize=3;
		if(!StringUtil.isBlank(PageNo)){
			pageNo = Integer.parseInt(PageNo);
		}
		if(!StringUtil.isBlank(PageSize)){
			pageSize = Integer.parseInt(PageSize);
		}
		System.out.println("当前会话id"+request.getSession().getId());
		T_Employee emp = (T_Employee) request.getSession().getAttribute("employee");
		System.out.println("当前会话里面的值+"+emp);
		//T_Employee emp=new T_Employee();
		//emp.setEmployee_ID(8);
		
		List<T_Task> allTask = this.tasks.allTask(emp,pageNo,pageSize,"all");
		pageBean<T_Task> page = new pageBean<T_Task>();
		page.setData(allTask);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalRecords(this.tasks.allTask(emp,1,100,"all").size());
		System.out.println("根据员工编号查询任务总数："+this.tasks.allTask(emp,1,100,"all").size());
		System.out.println("根据员工编号查询任务分页结果："+page.getData());
		request.getSession().setAttribute("allTask", page);
		response.sendRedirect("taskview.jsp");
	}
	//加载制定任务的下拉框
	@RequestMapping("manager/lgtaskput.do")
	public void taskput(HttpServletRequest request,HttpServletResponse response) throws IOException {
		T_Employee emp = (T_Employee) request.getSession().getAttribute("employee");
		//T_Employee emp=new T_Employee();
		//emp.setEmployee_ID(8);
		request.getSession().setAttribute("selectTask", this.tasks.selectEmpByRarntId(emp.getEmployee_ID(),1,100));
		response.sendRedirect("task.jsp");
	}
	
	//调整任务
	@RequestMapping("manager/lgtaskundone.do")
	public void alltaskundone(String PageNo,String PageSize,HttpServletRequest request,HttpServletResponse response) throws IOException {
		T_Employee emp = (T_Employee) request.getSession().getAttribute("employee");
		//T_Employee emp=new T_Employee();
		//emp.setEmployee_ID(8);
		int pageNo=1;
		int pageSize=3;
		if(!StringUtil.isBlank(PageNo)){
			pageNo = Integer.parseInt(PageNo);
		}
		if(!StringUtil.isBlank(PageSize)){
			pageSize = Integer.parseInt(PageSize);
		}
		List<T_Task> list=this.tasks.allTask(emp,pageNo,pageSize,"未实施");
		pageBean<T_Task> page = new pageBean<T_Task>();
		page.setData(list);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalRecords(this.tasks.allTask(emp,1,100,"未实施").size());
		System.out.println("根据员工编号查询任务总数："+this.tasks.allTask(emp,1,100,"未实施").size());
		System.out.println("根据员工编号查询任务分页结果："+page.getData());
		request.getSession().setAttribute("allTask",page);
		response.sendRedirect("taskundone.jsp");
	}

	//跟踪实施中的任务
	@RequestMapping("manager/lgtaskdance.do")
	public void alltaskdance(String PageNo,String PageSize,HttpServletRequest request,HttpServletResponse response) throws IOException {
		T_Employee emp = (T_Employee) request.getSession().getAttribute("employee");
		//T_Employee emp=new T_Employee()；
		//emp.setEmployee_ID(8);
		int pageNo=1;
		int pageSize=3;
		if(!StringUtil.isBlank(PageNo)){
			pageNo = Integer.parseInt(PageNo);
		}
		if(!StringUtil.isBlank(PageSize)){
			pageSize = Integer.parseInt(PageSize);
		}
		List<T_Task> list=this.tasks.allTask(emp,pageNo,pageSize,"实施中");
		pageBean<T_Task> page = new pageBean<T_Task>();
		page.setData(list);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalRecords(this.tasks.allTask(emp,1,100,"实施中").size());
		System.out.println("根据员工编号查询任务总数："+this.tasks.allTask(emp,1,100,"实施中").size());
		System.out.println("根据员工编号查询任务分页结果："+page.getData());
		request.getSession().setAttribute("allTaskdance",page);
		response.sendRedirect("intendance.jsp");
	}
	
	
	//任务详细信息+计划
	@RequestMapping("manager/lgtaskinfo.do")
	public void taskinfo(@RequestParam("radiobutton") String task,HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("当前pingjie的字符串"+task);
		String[] str=task.split("-");
		System.out.println("当前任务的计划数"+str[1]);
		request.getSession().setAttribute("lgtaskinfo", this.tasks.selectAllTaskByID(Integer.parseInt(str[0])));
		request.getSession().setAttribute("lgtask_plan", this.tasks.selectPlan(Integer.parseInt(str[0])));
		response.sendRedirect("taskparticular.jsp?count="+str[1]);
	}
	
	//点击计划详情
	@RequestMapping("manager/lgplaninfo.do")
	public void planinfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
		try {
			String planid = request.getParameter("radiobutton");
			System.out.println("当前pingjie的字符串"+planid);
			String[] str=planid.split("-");
			System.out.println("当前任务的计划数"+str[1]);
			request.getSession().setAttribute("planinfo", this.tasks.selectPlanById(Integer.parseInt(str[0])));
			response.sendRedirect("program.jsp?count="+str[1]);
		} catch (Exception e) {
			response.sendRedirect("error.jsp");
		}				
	}
	
	
	//新添任务
	@RequestMapping("manager/lgtaskinsert.do")
	public void taskinsert(@RequestParam("select") Integer impId,
			@ModelAttribute("task") T_Task task,@RequestParam("task_desc") String desc,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("任务描述"+desc);
		task.setTask_desc(desc);
		//发布人的id
		T_Employee emp = (T_Employee) request.getSession().getAttribute("employee");
		//T_Employee emp=new T_Employee();
		//emp.setEmployee_ID(8);
		
		int count = this.tasks.insertTask(task, impId, emp.getEmployee_ID());
		
		if (count>0) {
			response.sendRedirect("lgtask.do");
		}else{
			response.sendRedirect("error.jsp");
		}		
	}

	//删除任务
	@RequestMapping("manager/lgtaskdelete.do")
	public void taskdelete(HttpServletRequest request,HttpServletResponse response) throws IOException {
		try {
			String[] taskids =request.getParameterValues("radiobutton");
		int count=0;
		for (int i = 0; i < taskids.length; i++) {
			 count += this.tasks.deleteTask(Integer.parseInt(taskids[i]));
		}		
		if (count>(taskids.length-1)) {
			response.sendRedirect("lgtaskundone.do");
		}else{
			response.sendRedirect("error.jsp");
		}
		
		
	} catch (Exception e) {
		response.sendRedirect("error.jsp");
	}
	}
	
	//跳转到修改未实施任务的页面
	@RequestMapping("manager/lgtaskupdate.do")
	public void taskup(@RequestParam("taskid") Integer taskid,HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("当前传过来的taskid"+taskid);
		T_Employee emp = (T_Employee) request.getSession().getAttribute("employee");
		//T_Employee emp=new T_Employee();
		//emp.setEmployee_ID(8);
		request.getSession().setAttribute("selectTask", this.tasks.selectEmpByRarntId(emp.getEmployee_ID(),1,100));
		request.getSession().setAttribute("uptask", this.tasks.selectAllTaskByID(taskid));
		response.sendRedirect("adjust.jsp");
	}
	
	//按编号修改任务
	@RequestMapping("manager/lgtaskupdateto.do")
	public void taskupdate(@RequestParam("select") Integer impId,
			@ModelAttribute("task") T_Task task,@RequestParam("taskid") Integer taskid,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("任务描述"+task.getTask_desc());
		task.setTask_id(taskid);
		int count = this.tasks.updateTask(task, impId);
		
		if (count>0) {
			response.sendRedirect("lgtaskundone.do");
		}else{
			response.sendRedirect("error.jsp");
		}		
	}
	
	//查询人员详情
		@RequestMapping("manager/lgempinfo.do")
		public void empinfo(@RequestParam("radiobutton") Integer empId,
				HttpServletRequest request,HttpServletResponse response) throws IOException {
			System.out.println("当前要查询的人员id"+empId);
			request.getSession().setAttribute("empinfo", this.tasks.selectEmpById(empId));
			response.sendRedirect("personinfo.jsp");
		}
		
		
		//跟踪任务详细信息+计划
		@RequestMapping("manager/lgtaskdanceinfo.do")
		public void taskdanceinfo(@RequestParam("radiobutton") Integer taskid,HttpServletRequest request,HttpServletResponse response) throws IOException {
			request.getSession().setAttribute("lgtaskinfo", this.tasks.selectAllTaskByID(taskid));
			request.getSession().setAttribute("lgtask_plan", this.tasks.selectPlan(taskid));
			response.sendRedirect("programinten.jsp");
		}
		
		//修改任务的状态
		@RequestMapping("manager/lgtaskupdatestatus.do")
		public void taskupdatestatus(@RequestParam("select") String selectstr,
				HttpServletRequest request,HttpServletResponse response) throws IOException {
			String[] str=selectstr.split("-");
			System.out.println("传过来的下拉框"+selectstr);
			int count =0;
			if (str[0].equals("undone")) {
				//实施中
				count=this.tasks.updateTaskstatus(Integer.parseInt(str[1]), "实施中");
			}else if(str[0].equals("end")){
				//已完成
				count=this.tasks.updateTaskstatus(Integer.parseInt(str[1]), "已完成");
			}
			
			if (count>0) {
				response.sendRedirect("lgtaskdance.do");
			}else{
				response.sendRedirect("error.jsp");
			}		
		}
}
