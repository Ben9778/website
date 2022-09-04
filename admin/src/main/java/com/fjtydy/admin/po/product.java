package com.fjtydy.admin.po;

import java.sql.Timestamp;

public class product {
	private int product_id;
	private String product_name;
	private String imgurl;
	private String detailurl;
	private String pageurl;
	private Timestamp create_time;
	
	public product() {
		super();
	}
	
	public String getPageurl() {
		return pageurl;
	}

	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}

	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDetailurl() {
		return detailurl;
	}
	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "product [product_id=" + product_id + ", product_name=" + product_name + ", imgurl=" + imgurl
				+ ", detailurl=" + detailurl + ", pageurl=" + pageurl + ", create_time=" + create_time + "]";
	}
	
	
}
