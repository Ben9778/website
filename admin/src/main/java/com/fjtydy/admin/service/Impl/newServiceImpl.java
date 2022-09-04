package com.fjtydy.admin.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fjtydy.admin.dao.newDao;
import com.fjtydy.admin.po.news;
import com.fjtydy.admin.service.newService;


/**
 * 新闻管理serviceImpl
 */
@Service("newService")
@Transactional
public class newServiceImpl implements newService{
	@Autowired
	private newDao newdao;
	/**
	 * 后端查询新闻
	 */
	public List<news> queryNew(){
		List<news> newList=(List<news>)this.newdao.queryNew();
		return newList;
	};
	/**
	 * 前端查询新闻
	 */
	public List<news> queryNews(){
		List<news> newsList=(List<news>)this.newdao.queryNews();
		return newsList;
	};
	
	/**
	 * 首页查主新闻
	 */
	public List<news> queryNewsIndex(){
		List<news> list=this.newdao.queryNewsIndex();
		return list;
	};
	
	/**
	 * 首页查新闻列表
	 */
	public List<news> queryNewsList(){
		List<news> newsList=(List<news>)this.newdao.queryNewsList();
		return newsList;
	};
	
	/**
	 * 修改新闻(主图修改)
	 */
	public Integer updateNew(int new_id,String title, String introduce,String dates,String content,
			String imgPath,String pagePath) {
		int rows=this.newdao.updateNew(new_id,title, introduce, dates, content, imgPath,pagePath);
		return rows;
	};
	
	/**
	 * 修改新闻(图片没有修改)
	 */
	public Integer setNew(int new_id,String title, String introduce,String dates,String content,
			String pagePath) {
		int rows=this.newdao.setNew(new_id,title, introduce, dates, content,pagePath);
		return rows;
	};
	/**
	 * 删除新闻
	 */
	public Integer deleteNew(List<Integer>new_id) {
		int rows=this.newdao.deleteNew(new_id);
		return rows;
	};
	
	/**
	 * 新增新闻
	 */
	public Integer insertNew(String title,String introduce,String dates,String content,
			String imgPath,String pagePath) {
		int rows=this.newdao.insertNew(title, introduce, dates, content, imgPath, pagePath);
		return rows;
	};
	

}
