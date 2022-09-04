package com.fjtydy.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fjtydy.admin.po.invite;
import com.fjtydy.admin.po.news;
import com.fjtydy.admin.po.product;

public interface newService {
	/**
	 * 后端查询新闻
	 */
	public List<news> queryNew();
	
	/**
	 * 前端查询新闻
	 */
	public List<news> queryNews();
	
	/**
	 * 首页查主新闻
	 */
	public List<news> queryNewsIndex();
	
	/**
	 * 首页查新闻列表
	 */
	public List<news> queryNewsList();
	
	/**
	 * 修改新闻(主图修改)
	 */
	public Integer updateNew(
			int new_id,
			String title, 
			String introduce,
			String dates,
			String content,
			String imgPath,
			String pagePath
	);
	
	/**
	 * 修改新闻(图片没有修改)
	 */
	public Integer setNew(
			int new_id,
			String title, 
			String introduce,
			String dates,
			String content,
			String pagePath
	);
	
	/**
	 * 删除新闻
	 */
	public Integer deleteNew(List<Integer>new_id);
	
	/**
	 * 新增新闻
	 */
	public Integer insertNew(
			String title,
			String introduce,
			String dates,
			String content,
			String imgPath,
			String pagePath
	);

}
