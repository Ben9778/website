package com.fjtydy.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fjtydy.admin.po.product;

public interface productDao {
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
	public Integer deleteProduct(@Param("product_id") List<Integer>product_id);


	/**
	 * 添加产品
	 */
	public Integer insertProduct(@Param("product_name") String product_name,
			@Param("imgurl")String imgurl,@Param("detailurl")String detailurl,@Param("pageurl")String pageurl);

}
