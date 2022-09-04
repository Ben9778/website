package com.fjtydy.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fjtydy.admin.po.invite;

public interface inviteService {
	/**
	 * 查询所有用户留言
	 */
	public List<invite> queryMessage();

	/**
	 * 更改用户信息
	 */
	public Integer updateMessage(Integer user_id, String username,String phone,String message);

	/**
	 *  删除用户信息
	 */
	public Integer deleteMessage(List<Integer> user_id);


	/**
	 * 添加用户信息
	 */
	public Integer insertMessage(String username,String phone,String message);

}
