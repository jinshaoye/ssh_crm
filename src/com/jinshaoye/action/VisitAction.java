package com.jinshaoye.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.jinshaoye.entity.Customer;
import com.jinshaoye.entity.User;
import com.jinshaoye.entity.Visit;
import com.jinshaoye.service.CustomerService;
import com.jinshaoye.service.UserService;
import com.jinshaoye.service.VisitService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{

	private Visit visit = new Visit();
	public Visit getModel() {
		return visit;
	}
	private VisitService visitService;
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//拜访列表的方法
	public String list() {
		List<Visit> list = visitService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	//新增方法
	public String addVisit() {
		visitService.addVisit(visit);
		return "addVisit";
	}

	//到新增页面
	public String toAddPage() {
		//查询所有客户
		List<Customer> listCustomer = customerService.findAll();
		
		//查询所有用户
		List<User> listUser = userService.findAll();
		
		//放到域对象
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listCustomer", listCustomer);
		request.setAttribute("listUser", listUser);
		
		return "toAddPage";
	}


}





