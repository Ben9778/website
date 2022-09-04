package com.fjtydy.admin.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjtydy.admin.dao.adminDao;
import com.fjtydy.admin.po.admin;
import com.fjtydy.admin.service.adminService;

/**
 * 管理员账号操作serviceImpl
 */
@Service("adminService")
@Transactional
public class adminServiceImpl implements adminService{
	@Autowired
	private adminDao admins;
	/**
     * 查询管理员账户登陆
     * @return admin
     * @param phone,admin_password
     */
	public admin findAdmin(String admin_name, String admin_password) {
		admin am=this.admins.findAdmin(admin_name, admin_password);
		return am;
	}
	 /**
     * ͨ根据管理员用户名修改管理员密码
     * @return Integer
     * @param admin_name,admin_password
     */
	public Integer updatePwd(String admin_name, String admin_password) {
		int rows=this.admins.updatePwd(admin_name, admin_password);
		return rows;
	}
	
}
