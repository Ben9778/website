package com.fjtydy.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjtydy.admin.po.admin;
import com.fjtydy.admin.service.adminService;
import com.fjtydy.admin.util.status;

/**
 * 该类用于管理员登陆验证、修改密码
 * 开发者: 黄滨
 * */
@Controller
@CrossOrigin(origins = "*")
public class loginController { 
	@Autowired
	private adminService adminservice;
	@Autowired
	private status statu;
	/*验证登陆*/
	@RequestMapping(value="/checkLogin",method=RequestMethod.POST) 
	@ResponseBody
	public String findAdmin(@RequestParam("admin_name")String admin_name,  
			@RequestParam("admin_password")String admin_password,HttpSession httpSession) {

		admin am=this.adminservice.findAdmin(admin_name, admin_password);
		ObjectMapper mapper=new ObjectMapper();
		Map<String,String>map=new HashMap<String,String>();
		String str=null;
		if(am!=null) {
			httpSession.setAttribute("user", am);
			map.put("message", "yes");
			try {
				str=mapper.writeValueAsString(map); 
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return str;
		}else {
			map.put("message", "no");
			try {
				str=mapper.writeValueAsString(map);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
	/**
	 * 重置密码
	 * 参数：旧用户名、密码，新用户名、密码
	 * */
	@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	@ResponseBody
	public String updatePwd(@RequestParam("admin_name")String admin_name,
			@RequestParam("admin_password")String admin_password,
			String newpwd,HttpSession session) {
		//验证用户名密码
		admin am=this.adminservice.findAdmin(admin_name, admin_password);
		if(am!=null) {//用户名密码正确
			int rows=this.adminservice.updatePwd(admin_name, newpwd);
			if(rows>0) {
				session.removeAttribute("user");
				String str=statu.setStatus("code", "yes");
				return str;
			}else {
				String str=statu.setStatus("code", "no");
				return str;
			}
		}else {//用户名密码不正确
			String str=statu.setStatus("code", "error");
			return str;
		}
	}
	/**
	 * 跳转登陆后台
	 * */
	@RequestMapping(value="/tydyLogin",method=RequestMethod.GET)
	public String toLogin() {
		return "login";
	}
}
