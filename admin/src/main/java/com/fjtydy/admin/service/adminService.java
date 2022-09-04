package com.fjtydy.admin.service;

import com.fjtydy.admin.po.admin;

public interface adminService {
	/**
	 * 查询管理员账户登陆
	 */
	public admin findAdmin(String admin_name, String admin_password);

	/**
	 * 更改管理员密码
	 */
	public Integer updatePwd(String admin_name, String admin_password);
}
