package com.fjtydy.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fjtydy.admin.po.admin;
import com.fjtydy.admin.po.news;

public interface newDao {
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
	 * 修改新闻
	 */
	public Integer updateNew(
			@Param("new_id")int new_id,
			@Param("title") String title, 
			@Param("introduce") String introduce,
			@Param("dates")String dates,
			@Param("content")String content,
			@Param("imgPath")String imgPath,
			@Param("pagePath")String pagePath
	);
	/**
	 * 修改新闻(图片没有修改)
	 */
	public Integer setNew(
			@Param("new_id")int new_id,
			@Param("title") String title, 
			@Param("introduce") String introduce,
			@Param("dates")String dates,
			@Param("content")String content,
			@Param("pagePath")String pagePath
	);
	/**
	 * 删除新闻
	 */
	public Integer deleteNew(@Param("new_id")List<Integer>new_id);
	
	/**
	 * 添加新闻
	 */
	public Integer insertNew(
			@Param("title") String title,
			@Param("introduce") String introduce,
			@Param("dates")String dates,
			@Param("content")String content,
			@Param("imgPath")String imgPath,
			@Param("pagePath")String pagePath
	);
	
}
