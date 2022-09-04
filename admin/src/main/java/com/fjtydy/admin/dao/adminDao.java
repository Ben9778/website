package com.fjtydy.admin.dao;

import org.apache.ibatis.annotations.Param;

import com.fjtydy.admin.po.admin;

public interface adminDao {
	/**
	 * 查询管理员账户登陆
	 */
	public admin findAdmin(@Param("admin_name") String admin_name, @Param("admin_password") String admin_password);

	/**
	 * 更改管理员密码
	 */
	public Integer updatePwd(@Param("admin_name") String admin_name, @Param("admin_password") String admin_password);

}
