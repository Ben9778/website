package com.fjtydy.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fjtydy.admin.po.invite;

public interface inviteDao {
	/**
	 * 查询所有用户留言
	 */
	public List<invite> queryMessage();

	/**
	 * 更改用户信息
	 */
	public Integer updateMessage(@Param("user_id") Integer user_id, @Param("username") String username,
			@Param("phone")String phone,@Param("message")String message);
	

	/**
	 *  删除用户信息
	 */
	public int deleteMessage(@Param("user_id") List<Integer>user_id);


	/**
	 * 添加用户信息
	 */
	public Integer insertMessage(@Param("username") String username,
			@Param("phone")String phone,@Param("message")String message);

}
