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
	
	
	
	

    
/*     //删除
     @RequestMapping("admin/delete.do")
 	 public void delete(HttpServletRequest request, Model model,HttpServletResponse response, HttpSession session) throws IOException {
    	String empId=request.getParameter("ID");
       System.out.println("启动控制器执行删除");
    	System.out.println("删除员工id"+empId);
    	
    	
    	
    	 //查询指定编号的员工
    	T_Employee employee=Service.findEmployeeById(Integer.parseInt(empId));
    	
    	 System.out.println("当前员工姓名："+employee.getEmployee_name());
    	List<T_Employee> employees=Service.findEmployeesByManager(employee.getEmployee_ID());
    	
    	 System.out.println("所有直接下级："+employees.size());
    	 if (employees.size()!=0) {
			for (T_Employee t_Employee : employees) {
				System.out.println("下级为"+t_Employee.toString());
				t_Employee.setRole(null);
				Service.modifSuperior(t_Employee);
				System.out.println("修改的:"+t_Employee.toString());
			}
		}else {
			//删除
	    	 Service.deleteEmployee(employee); 
	    	 
	    	    List<T_Employee> employees2=Service.selectEmployees("select * from  t_employee", null);
			    session.setAttribute("employeeList", employees2);
			    System.out.println("管理员为:"+employees2);
				response.sendRedirect("../admin/useradmin.jsp");
		}
    	  
     }
     
     */
     
     
         //查看详情
        @RequestMapping("admin/persondesc.do")
        public String desc(@RequestParam(value="ID") Integer id  ,Model model,HttpServletRequest request, HttpServletResponse response,HttpSession session){
    	 //String id=request.getParameter("empId");
    	 System.out.println("启动控制器查看详情:");
    	 T_Employee employee=Service.findEmployeeById(id);
    	 System.out.println("员工为"+employee.toString());
    	 List<T_Employee> list=Service.queryAllManager();
    	 System.out.println("主管为"+list.toString());
    	 //存放每个主管列值
    	 model.addAttribute("empall", list);
    	 
    	 //存放每个员工列值
    	 model.addAttribute("all", employee);
    	 System.out.println("员工为"+employee.toString());
    	 session.setAttribute("person", employee);
		  return "forward:/admin/persondesc.jsp";
     }
     
     //修改
    @RequestMapping(value="admin/myupdate.do",method=RequestMethod.POST)
 	public void Myupdate(HttpSession session ,@RequestParam(value="managerid") Integer id  ,Model model, HttpServletResponse response) throws IOException{

    	System.out.println("启动控制器修改:update");
    	
    	
    	T_Employee employee=(T_Employee) session.getAttribute("person");
    	System.out.println("员工为"+employee.toString());
    	
    	T_Employee employee2=new T_Employee();
    	employee2.setEmployee_ID(id);
    	
    	employee.setEmp(employee2);
    	Service.modifyEmployee(employee);
    	System.out.println("修改的主管为"+employee2.toString());
    	
		
    	response.sendRedirect("All.do");
    	 
     }
    
    
    
    
    //查询所有并分页（用户管理）
    @RequestMapping("admin/queryall.do")
	public String query(HttpSession session,Model model,String pageNo1,String pageSize1){
    	
    	System.out.println("启动控制器分页");
    	int pageNo = 1;
		int pageSize = 4;
		System.out.println("页号："+pageNo1);
        if (!(pageNo1 == null || "".equals(pageNo1.trim()))){
			
			pageNo = Integer.parseInt(pageNo1);}
		if (!(pageSize1 == null || "".equals(pageSize1.trim())))
		{
			pageSize = Integer.parseInt(pageSize1);
		}
		System.out.println("页号："+pageNo+","+pageSize);
		List<T_Employee> employees=Service.selectbypage(pageNo, pageSize);
		for (T_Employee t_Employee : employees) {
			System.out.println("数据："+t_Employee.getReal_name());
		}
		
		PageBean<T_Employee> pb=new PageBean<T_Employee>();
		pb.setData(employees);
		pb.setPageNo(pageNo);
		pb.setPageSize(pageSize);
		pb.setTotalRecords(Service.getTotalCounts());
		//model.addAttribute("empadmin", pb);  注意使用model.addAttribute jsp取不到值
		session.setAttribute("emps", pb);
		
		System.out.println("分页的数据"+pb.getData().toString());
		return "redirect:/admin/useradmin.jsp";
		
		

    	
    	
    }
    
    
      
       //查询所有非管理员员工（员工管理）
	   @RequestMapping("admin/All.do")
	   public String listEmployee(Model model,String pageNo1,String pageSize1,HttpSession session) {
		    int pageNo = 1;
			int pageSize = 4;
			System.out.println("页号："+pageNo1);
			//判断是否传入了页号
			if (!(pageNo1 == null || "".equals(pageNo1.trim()))){
				
				pageNo = Integer.parseInt(pageNo1);}
			if (!(pageSize1 == null || "".equals(pageSize1.trim())))
			{
				pageSize = Integer.parseInt(pageSize1);
			}
			System.out.println("页号："+pageNo+","+pageSize);
			List<T_Employee> pbemps=Service.querybypage(pageNo, pageSize);
			
			
			
			PageBean<T_Employee> pb=new PageBean<T_Employee>();
			pb.setData(pbemps);
			pb.setPageNo(pageNo);
			pb.setPageSize(pageSize);
			pb.setTotalRecords(Service.getTotalCountnotadmin());
		
			
			session.setAttribute("empadmin", pb);
			
			System.out.println("分页的数据"+pb.getData().toString());
			return "redirect:/admin/empadmin.jsp";//员工管理页面
		
		
	}
	   
	   
	   //查询所有角色为员工的信息（分配人员）
		@RequestMapping("admin/update.do")
		public String loader_update(Model model,String pageNo1,String pageSize1,HttpSession session){
			
			
			int pageNo = 1;
			int pageSize = 4;
			System.out.println("页号："+pageNo1);
			//判断是否传入了页号
			if (!(pageNo1 == null || "".equals(pageNo1.trim()))){
				
				pageNo = Integer.parseInt(pageNo1);}
			if (!(pageSize1 == null || "".equals(pageSize1.trim())))
			{
				pageSize = Integer.parseInt(pageSize1);
			}
			
			System.out.println("页号："+pageNo+","+pageSize);
			List<T_Employee> pbemps=Service.querybyemp(pageNo, pageSize);
			//存放查询结果对象
			PageBean<T_Employee> pb=new PageBean<T_Employee>();
			pb.setData(pbemps);
			pb.setPageNo(pageNo);
			pb.setPageSize(pageSize);
			pb.setTotalRecords(Service.getTotalCountemp());
			
			
           session.setAttribute("all", pb);
			
			System.out.println("分页的数据"+pb.getData().toString());
			return "redirect:/admin/empdistribute.jsp";//详情页面
			
			
			
		}
		//删除员工第二种
		@RequestMapping("admin/delete.do")
		public String  deleteEmployee(Integer ID, String empRoleName,String empName,   HttpServletResponse response,HttpSession session) throws IOException {
			try {
				System.out.println("启动删除控制器");
				System.out.println(ID);
				System.out.println(empRoleName);
				
				if ("员工".equals(empRoleName)) {
					this.Service.deleteEmployeebyid(ID);
					
				}else {
					// 修改任务表的assigner_id
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
