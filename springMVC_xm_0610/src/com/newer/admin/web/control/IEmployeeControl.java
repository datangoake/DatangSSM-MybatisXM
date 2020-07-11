package com.newer.admin.web.control;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.newer.admin.service.impl.EmployeeServiceimpl;
import com.newer.admin.web.util.EmployeeForm;
import com.newer.admin.web.util.PageBean;
import com.newer.common.entry.T_Employee;
import com.newer.common.entry.T_Role;

@Controller

public class IEmployeeControl {
	@Resource(name="EmployeeServiceimpl")
	EmployeeServiceimpl Service;
	
	
	
	

    
/*     //ɾ��
     @RequestMapping("admin/delete.do")
 	 public void delete(HttpServletRequest request, Model model,HttpServletResponse response, HttpSession session) throws IOException {
    	String empId=request.getParameter("ID");
       System.out.println("����������ִ��ɾ��");
    	System.out.println("ɾ��Ա��id"+empId);
    	
    	
    	
    	 //��ѯָ����ŵ�Ա��
    	T_Employee employee=Service.findEmployeeById(Integer.parseInt(empId));
    	
    	 System.out.println("��ǰԱ��������"+employee.getEmployee_name());
    	List<T_Employee> employees=Service.findEmployeesByManager(employee.getEmployee_ID());
    	
    	 System.out.println("����ֱ���¼���"+employees.size());
    	 if (employees.size()!=0) {
			for (T_Employee t_Employee : employees) {
				System.out.println("�¼�Ϊ"+t_Employee.toString());
				t_Employee.setRole(null);
				Service.modifSuperior(t_Employee);
				System.out.println("�޸ĵ�:"+t_Employee.toString());
			}
		}else {
			//ɾ��
	    	 Service.deleteEmployee(employee); 
	    	 
	    	    List<T_Employee> employees2=Service.selectEmployees("select * from  t_employee", null);
			    session.setAttribute("employeeList", employees2);
			    System.out.println("����ԱΪ:"+employees2);
				response.sendRedirect("../admin/useradmin.jsp");
		}
    	  
     }
     
     */
     
     
         //�鿴����
        @RequestMapping("admin/persondesc.do")
        public String desc(@RequestParam(value="ID") Integer id  ,Model model,HttpServletRequest request, HttpServletResponse response,HttpSession session){
    	 //String id=request.getParameter("empId");
    	 System.out.println("�����������鿴����:");
    	 T_Employee employee=Service.findEmployeeById(id);
    	 System.out.println("Ա��Ϊ"+employee.toString());
    	 List<T_Employee> list=Service.queryAllManager();
    	 System.out.println("����Ϊ"+list.toString());
    	 //���ÿ��������ֵ
    	 model.addAttribute("empall", list);
    	 
    	 //���ÿ��Ա����ֵ
    	 model.addAttribute("all", employee);
    	 System.out.println("Ա��Ϊ"+employee.toString());
    	 session.setAttribute("person", employee);
		  return "forward:/admin/persondesc.jsp";
     }
     
     //�޸�
    @RequestMapping(value="admin/myupdate.do",method=RequestMethod.POST)
 	public void Myupdate(HttpSession session ,@RequestParam(value="managerid") Integer id  ,Model model, HttpServletResponse response) throws IOException{

    	System.out.println("�����������޸�:update");
    	
    	
    	T_Employee employee=(T_Employee) session.getAttribute("person");
    	System.out.println("Ա��Ϊ"+employee.toString());
    	
    	T_Employee employee2=new T_Employee();
    	employee2.setEmployee_ID(id);
    	
    	employee.setEmp(employee2);
    	Service.modifyEmployee(employee);
    	System.out.println("�޸ĵ�����Ϊ"+employee2.toString());
    	
		
    	response.sendRedirect("All.do");
    	 
     }
    
    
    
    
    //��ѯ���в���ҳ���û�����
    @RequestMapping("admin/queryall.do")
	public String query(HttpSession session,Model model,String pageNo1,String pageSize1){
    	
    	System.out.println("������������ҳ");
    	int pageNo = 1;
		int pageSize = 4;
		System.out.println("ҳ�ţ�"+pageNo1);
        if (!(pageNo1 == null || "".equals(pageNo1.trim()))){
			
			pageNo = Integer.parseInt(pageNo1);}
		if (!(pageSize1 == null || "".equals(pageSize1.trim())))
		{
			pageSize = Integer.parseInt(pageSize1);
		}
		System.out.println("ҳ�ţ�"+pageNo+","+pageSize);
		List<T_Employee> employees=Service.selectbypage(pageNo, pageSize);
		for (T_Employee t_Employee : employees) {
			System.out.println("���ݣ�"+t_Employee.getReal_name());
		}
		
		PageBean<T_Employee> pb=new PageBean<T_Employee>();
		pb.setData(employees);
		pb.setPageNo(pageNo);
		pb.setPageSize(pageSize);
		pb.setTotalRecords(Service.getTotalCounts());
		//model.addAttribute("empadmin", pb);  ע��ʹ��model.addAttribute jspȡ����ֵ
		session.setAttribute("emps", pb);
		
		System.out.println("��ҳ������"+pb.getData().toString());
		return "redirect:/admin/useradmin.jsp";
		
		

    	
    	
    }
    
    
      
       //��ѯ���зǹ���ԱԱ����Ա������
	   @RequestMapping("admin/All.do")
	   public String listEmployee(Model model,String pageNo1,String pageSize1,HttpSession session) {
		    int pageNo = 1;
			int pageSize = 4;
			System.out.println("ҳ�ţ�"+pageNo1);
			//�ж��Ƿ�����ҳ��
			if (!(pageNo1 == null || "".equals(pageNo1.trim()))){
				
				pageNo = Integer.parseInt(pageNo1);}
			if (!(pageSize1 == null || "".equals(pageSize1.trim())))
			{
				pageSize = Integer.parseInt(pageSize1);
			}
			System.out.println("ҳ�ţ�"+pageNo+","+pageSize);
			List<T_Employee> pbemps=Service.querybypage(pageNo, pageSize);
			
			
			
			PageBean<T_Employee> pb=new PageBean<T_Employee>();
			pb.setData(pbemps);
			pb.setPageNo(pageNo);
			pb.setPageSize(pageSize);
			pb.setTotalRecords(Service.getTotalCountnotadmin());
		
			
			session.setAttribute("empadmin", pb);
			
			System.out.println("��ҳ������"+pb.getData().toString());
			return "redirect:/admin/empadmin.jsp";//Ա������ҳ��
		
		
	}
	   
	   
	   //��ѯ���н�ɫΪԱ������Ϣ��������Ա��
		@RequestMapping("admin/update.do")
		public String loader_update(Model model,String pageNo1,String pageSize1,HttpSession session){
			
			
			int pageNo = 1;
			int pageSize = 4;
			System.out.println("ҳ�ţ�"+pageNo1);
			//�ж��Ƿ�����ҳ��
			if (!(pageNo1 == null || "".equals(pageNo1.trim()))){
				
				pageNo = Integer.parseInt(pageNo1);}
			if (!(pageSize1 == null || "".equals(pageSize1.trim())))
			{
				pageSize = Integer.parseInt(pageSize1);
			}
			
			System.out.println("ҳ�ţ�"+pageNo+","+pageSize);
			List<T_Employee> pbemps=Service.querybyemp(pageNo, pageSize);
			//��Ų�ѯ�������
			PageBean<T_Employee> pb=new PageBean<T_Employee>();
			pb.setData(pbemps);
			pb.setPageNo(pageNo);
			pb.setPageSize(pageSize);
			pb.setTotalRecords(Service.getTotalCountemp());
			
			
           session.setAttribute("all", pb);
			
			System.out.println("��ҳ������"+pb.getData().toString());
			return "redirect:/admin/empdistribute.jsp";//����ҳ��
			
			
			
		}
		//ɾ��Ա���ڶ���
		@RequestMapping("admin/delete.do")
		public String  deleteEmployee(Integer ID, String empRoleName,String empName,   HttpServletResponse response,HttpSession session) throws IOException {
			try {
				System.out.println("����ɾ��������");
				System.out.println(ID);
				System.out.println(empRoleName);
				
				if ("Ա��".equals(empRoleName)) {
					this.Service.deleteEmployeebyid(ID);
					
				}else {
					// �޸�������assigner_id
					System.out.println("11");
					this.Service.updateAssignerId(ID);
					this.Service.updateEmployeeParent(ID);
					this.Service.deleteEmployeebyid(ID);
				}
				return "All.do";
			} catch (Exception e) {
				return "error.jsp";
			}						 									
		}
		
}
