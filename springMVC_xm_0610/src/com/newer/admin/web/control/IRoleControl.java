package com.newer.admin.web.control;
/*
 * Ҷ���2020-6-16
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
	  //װ��Ա������������
    @RequestMapping("admin/personadd.do")
 	public String personadd(Model model,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
    	System.out.println("װ������������:personadd");
    	T_Employee emps=new T_Employee();
    	T_Role role=new T_Role();
    	//�հױ�����
    	EmployeeForm  addForm =new  EmployeeForm();
    	addForm.setEmp(emps);//��Ա���հ׶���
    	addForm.setRole(role);//����ɫ�հ׶���
    	System.out.println("123"+role.toString());
    	//����ȷ������Ĭ��Ϊ��
    	addForm.setConfirmPassword("");
    	//��ѯ���н�ɫ
    	List<T_Role> list=this.Service.findAllRoles();
    	model.addAttribute("addForm",addForm);//���հ׶���
    	
       System.out.println("��ɫ"	+list.toString());
    	model.addAttribute("allAddRoles",list);//����������ɫ�����б�װ������
    	return "forward:personadd.jsp";
    }
    //���Ա��
	@RequestMapping("admin/emp_add.do")
	public String addEmployee(@ModelAttribute("addForm") EmployeeForm addForm,Model model, HttpServletResponse response,HttpSession session) throws IOException {
		T_Employee emp=addForm.getEmp();//Ա����������װ��Ա������
		String password = emp.getPassword();//ȡ�����������
		
		if (addForm.getConfirmPassword().equals(password)) {
			//true:��������һ�� ��ӳɹ�
			emp.setRole(addForm.getRole());
			T_Service.insertEmployee(emp);
			//��ִ��һ��ȫ����ѯ
			System.out.println("������������ҳ");
	    	int pageNo = 1;
			int pageSize = 4;
			List<T_Employee> employees=T_Service.selectbypage(pageNo, pageSize);
			for (T_Employee t_Employee : employees) {
				System.out.println("���ݣ�"+t_Employee.getReal_name());
			}
			
			PageBean<T_Employee> pb=new PageBean<T_Employee>();
			pb.setData(employees);
			pb.setPageNo(pageNo);
			pb.setPageSize(pageSize);
			pb.setTotalRecords(T_Service.getTotalCounts());
			//model.addAttribute("empadmin", pb);  ע��ʹ��model.addAttribute jspȡ����ֵ
			session.setAttribute("emps", pb);
			
			
			
			
		
			 return "redirect:/admin/useradmin.jsp";
		}else {
			//�������벻һ�´���
			//����Χ�洢ʧ�ܵ�message
			model.addAttribute("message", "����ʧ�ܣ��������벻һ��");
			 return "error.jsp";//��ת��error.jsp��ʾ����ʧ����Ϣ
		}
		
		
		
	}
}
