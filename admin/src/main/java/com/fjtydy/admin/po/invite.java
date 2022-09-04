package com.fjtydy.admin.po;

import java.sql.Timestamp;


public class invite {
	private int user_id;//用户ID
	private String username;//姓名
	private String phone;//联系方式
	private Timestamp create_time;//创建时间
	private String message;//留言
	private Timestamp update_time;//更新时间

	public invite() {
		
	}
	
	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "invite [user_id=" + user_id + ", username=" + username + ", phone=" + phone + ", create_time="
				+ create_time + ", message=" + message + ", update_time=" + update_time + "]";
	}
	
	
	
}
