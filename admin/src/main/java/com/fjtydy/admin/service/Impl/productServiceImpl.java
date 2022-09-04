package com.fjtydy.admin.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fjtydy.admin.dao.productDao;
import com.fjtydy.admin.po.product;
import com.fjtydy.admin.service.inviteService;
import com.fjtydy.admin.service.productService;


/**
 * 产品管理serviceImpl
 */
@Service("productService")
@Transactional
public class productServiceImpl implements productService{
	@Autowired
	private productDao productdao;
	/**
	 * 查询产品
	 */
	public List<product> queryProduct() {
		List<product> productlist=(List<product>)this.productdao.queryProduct();
		return productlist;
	}
	/**
	 * 前端查询产品
	 * 
	 * */
	public List<product>queryProducts(){
		List<product> productslist=(List<product>)this.productdao.queryProducts();
		return productslist;
	}

	/**
     * ͨ删除产品
     * @return Integer
     * @param List<Integer> product_id
     */
	public Integer deleteProduct(List<Integer>product_id) {
		int rows=this.productdao.deleteProduct(product_id);
		return rows;
	}
	 /**
     * ͨ添加信息
     * @return Integer
     * @param product_name,url,detail
     */
	public Integer insertProduct(String product_name,String url,String detail,String pageurl) {
		int rows=this.productdao.insertProduct(product_name,url,detail,pageurl);
		return rows;
	}
}
