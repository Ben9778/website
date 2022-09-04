package com.fjtydy.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjtydy.admin.po.admin;
import com.fjtydy.admin.po.invite;
import com.fjtydy.admin.service.adminService;
import com.fjtydy.admin.service.inviteService;
import com.fjtydy.admin.util.status;

@Controller
@CrossOrigin(origins = "*")
public class indexController {
	@Autowired
	private inviteService inviteservice; 
	@Autowired
	private status statu;
	/* 跳转首页并查询用户留言 */
	@RequestMapping(value = "/adminSys", method = RequestMethod.GET)
	public ModelAndView queryMessage(Model model) {
		
		List<invite> invitelist=this.inviteservice.queryMessage();
		model.addAttribute("inviteList", invitelist);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/home");
		return mv;
	}
	
	/* 更改用户留言 */
	@RequestMapping(value = "/updateMessage", method = RequestMethod.POST)
	@ResponseBody
	public String updateMessage(@RequestParam("user_id")int user_id,
			@RequestParam("username")String username,@RequestParam("phone")String phone,
			@RequestParam("message")String message,Model model) {
		
		int row=this.inviteservice.updateMessage(user_id,username,phone,message);
		String str=null;
		if(row>0) {//修改成功
			str=statu.setStatus("code","yes");
			return str;
		}else {//修改失败
			str=statu.setStatus("code","no");
			return str;
		}
	}
	/* 删除用户留言 */
	@RequestMapping(value = "/deleteMessage", method = RequestMethod.POST)
	@ResponseBody
	public String updateMessage(@RequestParam("user_id") String[] user_id) {
		List<Integer> list=new ArrayList<Integer>(); 
        int ids;
        for (String string : user_id) { 
            ids=Integer.parseInt(string); 
            list.add(ids); 
            }
		int row=this.inviteservice.deleteMessage(list);
		String str=null;
		if(row>0) {//删除成功
			str=statu.setStatus("code","yes");
			return str;
		}else {//删除失败
			str=statu.setStatus("code","no");
			return str;
		}
	}
	/* 添加用户留言 */
	@RequestMapping(value = "/insertMessage", method = RequestMethod.POST)
	@ResponseBody
	public String insertMessage(@RequestParam("username")String username,@RequestParam("phone")String phone,
			@RequestParam("message")String message,Model model) {
		
		int row=this.inviteservice.insertMessage(username,phone,message);
		status statu=new status();
		String str=null;
		if(row>0) {//添加成功
			str=statu.setStatus("code","yes");
			return str;
		}else {//添加失败
			str=statu.setStatus("code","no");
			return str;
		}
	}
	
		
}
