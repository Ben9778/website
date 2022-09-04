package com.fjtydy.admin.po;

public class news {
	private int new_id;//ID
	private String title;//标题
	private String introduce;//简述
	private String dates;//新闻日期
	private String content;//内容
	private String imgPath;//主图路径
	private String pagePath;//页面存放路径
	
	
	public news() {
		super();
	}
	public int getNew_id() {
		return new_id;
	}
	public void setNew_id(int new_id) {
		this.new_id = new_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getPagePath() {
		return pagePath;
	}
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}
	@Override
	public String toString() {
		return "news [new_id=" + new_id + ", title=" + title + ", introduce=" + introduce + ", dates=" + dates
				+ ", content=" + content + ", imgPath=" + imgPath + ", pagePath=" + pagePath + "]";
	}
	
	
	
}
