package com.fjtydy.admin.util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 该类是操作数据库返回状态rows工具类
 * Author:黄滨
 * */
public class status {

	public String setStatus(String msgName, String msg) {
		ObjectMapper om = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();
		String str = null;
		map.put(msgName, msg);
		try {
			str = om.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return str;
	}
}
