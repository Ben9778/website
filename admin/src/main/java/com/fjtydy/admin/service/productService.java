package com.fjtydy.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fjtydy.admin.po.invite;
import com.fjtydy.admin.po.product;

public interface productService {
	/**
	 * 后端查询产品
	 */
	public List<product> queryProduct();
	
	/**
	 * 前端查询产品
	 * 
	 * */
	public List<product>queryProducts();

	/**
	 *  删除产品
	 */
	public Integer deleteProduct(List<Integer>product_id);

	/**
	 * 添加产品
	 */
	public Integer insertProduct(String product_name,String imgurl,String detailurl,String pageurl);

}
