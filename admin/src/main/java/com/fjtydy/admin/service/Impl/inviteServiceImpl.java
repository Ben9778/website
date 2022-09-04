package com.fjtydy.admin.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fjtydy.admin.dao.inviteDao;
import com.fjtydy.admin.po.invite;
import com.fjtydy.admin.service.inviteService;


/**
 * 管理员账号操作serviceImpl
 */
@Service("inviteService")
@Transactional
public class inviteServiceImpl implements inviteService{
	@Autowired
	private inviteDao invitedao;
	/**
     * 查询所有用户留言
     * @return List<invite>
     *
     */
	public List<invite> queryMessage() {
		List<invite> invitelist=(List<invite>)this.invitedao.queryMessage();
		return invitelist;
	}
	 /**
     * ͨ根据用户ID修改信息
     * @return Integer
     * @param user_id,username,phone,message
     */
	public Integer updateMessage(Integer user_id, String username,String phone,String message) {
		int rows=this.invitedao.updateMessage(user_id,username,phone,message);
		return rows;
	}
	/**
     * ͨ删除信息
     * @return Integer
     * @param List<Integer> user_id
     */
	public Integer deleteMessage(List<Integer>user_id) {
		int rows=this.invitedao.deleteMessage(user_id);
		return rows;
	}
	 /**
     * ͨ添加信息
     * @return Integer
     * @param username,phone,message
     */
	public Integer insertMessage(String username,String phone,String message) {
		int rows=this.invitedao.insertMessage(username,phone,message);
		return rows;
	}
}
