package com.newer.admin.web.control;
/*
 * 叶金豪2020-6-16
 * 
 */
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newer.admin.service.impl.EmployeeServiceimpl;
import com.newer.admin.service.impl.IRoleServiceimpl;
import com.newer.admin.web.util.EmployeeForm;
import com.newer.admin.web.util.PageBean;
import com.newer.common.entry.T_Employee;
import com.newer.common.entry.T_Role;

@Controller
public class IRoleControl {

	
	@Resource(name="EmployeeServiceimpl")
	EmployeeServiceimpl T_Service;
	
	@Resource(name="IRoleServiceimpl")
	IRoleServiceimpl Service;
	  //装填员工新增表单请求
    @RequestMapping("admin/personadd.do")
 	public String personadd(Model model,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
    	System.out.println("装填新增表单数据:personadd");
    	T_Employee emps=new T_Employee();
    	T_Role role=new T_Role();
    	//空白表单对象
    	EmployeeForm  addForm =new  EmployeeForm();
    	addForm.setEmp(emps);//表单员工空白对象
    	addForm.setRole(role);//表单角色空白对象
    	System.out.println("123"+role.toString());
    	//设置确认密码默认为空
    	addForm.setConfirmPassword("");
    	//查询所有角色
    	List<T_Role> list=this.Service.findAllRoles();
    	model.addAttribute("addForm",addForm);//表单空白对象
    	
       System.out.println("角色"	+list.toString());
    	model.addAttribute("allAddRoles",list);//给新增表单角色下拉列表装填数据
    	return "forward:personadd.jsp";
    }
    //添加员工
	@RequestMapping("admin/emp_add.do")
	public String addEmployee(@ModelAttribute("addForm") EmployeeForm addForm,Model model, HttpServletResponse response,HttpSession session) throws IOException {
		T_Employee emp=addForm.getEmp();//员工新增表单封装的员工对象
		String password = emp.getPassword();//取得输入的密码
		
		if (addForm.getConfirmPassword().equals(password)) {
			//true:两次密码一致 添加成功
			emp.setRole(addForm.getRole());
			T_Service.insertEmployee(emp);
			//在执行一次全部查询
			System.out.println("启动控制器分页");
	    	int pageNo = 1;
			int pageSize = 4;
			List<T_Employee> employees=T_Service.selectbypage(pageNo, pageSize);
			for (T_Employee t_Employee : employees) {
				System.out.println("数据："+t_Employee.getReal_name());
			}
			
			PageBean<T_Employee> pb=new PageBean<T_Employee>();
			pb.setData(employees);
			pb.setPageNo(pageNo);
			pb.setPageSize(pageSize);
			pb.setTotalRecords(T_Service.getTotalCounts());
			//model.addAttribute("empadmin", pb);  注意使用model.addAttribute jsp取不到值
			session.setAttribute("emps", pb);
			
			
			
			
		
			 return "redirect:/admin/useradmin.jsp";
		}else {
			//两次密码不一致错误
			//请求范围存储失败的message
			model.addAttribute("message", "新增失败，两次密码不一致");
			 return "error.jsp";//跳转到error.jsp显示新增失败信息
		}
		
		
		
	}
}
