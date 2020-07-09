package com.newer.manager.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newer.common.entry.T_Employee;
import com.newer.common.entry.T_Task;
import com.newer.manager.service.TaskService;
import com.newer.person.data.util.PageBean;
import com.newer.person.data.util.StringUtil;

@Controller
public class MyControl {
	
	@Resource(name="taskservice")
	TaskService tasks;
	
	@ModelAttribute("task")
	public T_Task load(){
		return new T_Task();
	};
	
	
	//��Ա����
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
		PageBean<T_Employee> page = new PageBean<T_Employee>();
		page.setData(allemp);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalRecords(this.tasks.selectEmpByRarntId(emp.getEmployee_ID(),1,100).size());
		System.out.println("����Ա����Ų�ѯ����������"+this.tasks.selectEmpByRarntId(emp.getEmployee_ID(),1,100).size());
		System.out.println("����Ա����Ų�ѯ�����ҳ�����"+page.getData());
//		T_Employee emp=new T_Employee();
//		emp.setEmployee_ID(8);
		request.getSession().setAttribute("allemp", page);
		response.sendRedirect("checkper.jsp");
	}
	
	//�鿴����
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
		System.out.println("��ǰ�Ựid"+request.getSession().getId());
		T_Employee emp = (T_Employee) request.getSession().getAttribute("employee");
		System.out.println("��ǰ�Ự�����ֵ+"+emp);
		//T_Employee emp=new T_Employee();
		//emp.setEmployee_ID(8);
		
		List<T_Task> allTask = this.tasks.allTask(emp,pageNo,pageSize,"all");
		PageBean<T_Task> page = new PageBean<T_Task>();
		page.setData(allTask);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalRecords(this.tasks.allTask(emp,1,100,"all").size());
		System.out.println("����Ա����Ų�ѯ����������"+this.tasks.allTask(emp,1,100,"all").size());
		System.out.println("����Ա����Ų�ѯ�����ҳ�����"+page.getData());
		request.getSession().setAttribute("allTask", page);
		response.sendRedirect("taskview.jsp");
	}
	//�����ƶ������������
	@RequestMapping("manager/lgtaskput.do")
	public void taskput(HttpServletRequest request,HttpServletResponse response) throws IOException {
		T_Employee emp = (T_Employee) request.getSession().getAttribute("employee");
		//T_Employee emp=new T_Employee();
		//emp.setEmployee_ID(8);
		request.getSession().setAttribute("selectTask", this.tasks.selectEmpByRarntId(emp.getEmployee_ID(),1,100));
		response.sendRedirect("task.jsp");
	}
	
	//��������
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
		List<T_Task> list=this.tasks.allTask(emp,pageNo,pageSize,"δʵʩ");
		PageBean<T_Task> page = new PageBean<T_Task>();
		page.setData(list);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalRecords(this.tasks.allTask(emp,1,100,"δʵʩ").size());
		System.out.println("����Ա����Ų�ѯ����������"+this.tasks.allTask(emp,1,100,"δʵʩ").size());
		System.out.println("����Ա����Ų�ѯ�����ҳ�����"+page.getData());
		request.getSession().setAttribute("allTask",page);
		response.sendRedirect("taskundone.jsp");
	}

	//����ʵʩ�е�����
	@RequestMapping("manager/lgtaskdance.do")
	public void alltaskdance(String PageNo,String PageSize,HttpServletRequest request,HttpServletResponse response) throws IOException {
		T_Employee emp = (T_Employee) request.getSession().getAttribute("employee");
		//T_Employee emp=new T_Employee()��
		//emp.setEmployee_ID(8);
		int pageNo=1;
		int pageSize=3;
		if(!StringUtil.isBlank(PageNo)){
			pageNo = Integer.parseInt(PageNo);
		}
		if(!StringUtil.isBlank(PageSize)){
			pageSize = Integer.parseInt(PageSize);
		}
		List<T_Task> list=this.tasks.allTask(emp,pageNo,pageSize,"ʵʩ��");
		PageBean<T_Task> page = new PageBean<T_Task>();
		page.setData(list);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalRecords(this.tasks.allTask(emp,1,100,"ʵʩ��").size());
		System.out.println("����Ա����Ų�ѯ����������"+this.tasks.allTask(emp,1,100,"ʵʩ��").size());
		System.out.println("����Ա����Ų�ѯ�����ҳ�����"+page.getData());
		request.getSession().setAttribute("allTaskdance",page);
		response.sendRedirect("intendance.jsp");
	}
	
	
	//������ϸ��Ϣ+�ƻ�
	@RequestMapping("manager/lgtaskinfo.do")
	public void taskinfo(@RequestParam("radiobutton") String task,HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("��ǰpingjie���ַ���"+task);
		String[] str=task.split("-");
		System.out.println("��ǰ����ļƻ���"+str[1]);
		request.getSession().setAttribute("lgtaskinfo", this.tasks.selectAllTaskByID(Integer.parseInt(str[0])));
		request.getSession().setAttribute("lgtask_plan", this.tasks.selectPlan(Integer.parseInt(str[0])));
		response.sendRedirect("taskparticular.jsp?count="+str[1]);
	}
	
	//����ƻ�����
	@RequestMapping("manager/lgplaninfo.do")
	public void planinfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
		try {
			String planid = request.getParameter("radiobutton");
			System.out.println("��ǰpingjie���ַ���"+planid);
			String[] str=planid.split("-");
			System.out.println("��ǰ����ļƻ���"+str[1]);
			request.getSession().setAttribute("planinfo", this.tasks.selectPlanById(Integer.parseInt(str[0])));
			response.sendRedirect("program.jsp?count="+str[1]);
		} catch (Exception e) {
			response.sendRedirect("error.jsp");
		}				
	}
	
	
	//��������
	@RequestMapping("manager/lgtaskinsert.do")
	public void taskinsert(@RequestParam("select") Integer impId,
			@ModelAttribute("task") T_Task task,@RequestParam("task_desc") String desc,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("��������"+desc);
		task.setTask_desc(desc);
		//�����˵�id
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

	//ɾ������
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
	
	//��ת���޸�δʵʩ�����ҳ��
	@RequestMapping("manager/lgtaskupdate.do")
	public void taskup(@RequestParam("taskid") Integer taskid,HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("��ǰ��������taskid"+taskid);
		T_Employee emp = (T_Employee) request.getSession().getAttribute("employee");
		//T_Employee emp=new T_Employee();
		//emp.setEmployee_ID(8);
		request.getSession().setAttribute("selectTask", this.tasks.selectEmpByRarntId(emp.getEmployee_ID(),1,100));
		request.getSession().setAttribute("uptask", this.tasks.selectAllTaskByID(taskid));
		response.sendRedirect("adjust.jsp");
	}
	
	//������޸�����
	@RequestMapping("manager/lgtaskupdateto.do")
	public void taskupdate(@RequestParam("select") Integer impId,
			@ModelAttribute("task") T_Task task,@RequestParam("taskid") Integer taskid,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("��������"+task.getTask_desc());
		task.setTask_id(taskid);
		int count = this.tasks.updateTask(task, impId);
		
		if (count>0) {
			response.sendRedirect("lgtaskundone.do");
		}else{
			response.sendRedirect("error.jsp");
		}		
	}
	
	//��ѯ��Ա����
		@RequestMapping("manager/lgempinfo.do")
		public void empinfo(@RequestParam("radiobutton") Integer empId,
				HttpServletRequest request,HttpServletResponse response) throws IOException {
			System.out.println("��ǰҪ��ѯ����Աid"+empId);
			request.getSession().setAttribute("empinfo", this.tasks.selectEmpById(empId));
			response.sendRedirect("personinfo.jsp");
		}
		
		
		//����������ϸ��Ϣ+�ƻ�
		@RequestMapping("manager/lgtaskdanceinfo.do")
		public void taskdanceinfo(@RequestParam("radiobutton") Integer taskid,HttpServletRequest request,HttpServletResponse response) throws IOException {
			request.getSession().setAttribute("lgtaskinfo", this.tasks.selectAllTaskByID(taskid));
			request.getSession().setAttribute("lgtask_plan", this.tasks.selectPlan(taskid));
			response.sendRedirect("programinten.jsp");
		}
		
		//�޸������״̬
		@RequestMapping("manager/lgtaskupdatestatus.do")
		public void taskupdatestatus(@RequestParam("select") String selectstr,
				HttpServletRequest request,HttpServletResponse response) throws IOException {
			String[] str=selectstr.split("-");
			System.out.println("��������������"+selectstr);
			int count =0;
			if (str[0].equals("undone")) {
				//ʵʩ��
				count=this.tasks.updateTaskstatus(Integer.parseInt(str[1]), "ʵʩ��");
			}else if(str[0].equals("end")){
				//�����
				count=this.tasks.updateTaskstatus(Integer.parseInt(str[1]), "�����");
			}
			
			if (count>0) {
				response.sendRedirect("lgtaskdance.do");
			}else{
				response.sendRedirect("error.jsp");
			}		
		}
}