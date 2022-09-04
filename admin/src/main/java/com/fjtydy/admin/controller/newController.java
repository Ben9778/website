package com.fjtydy.admin.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjtydy.admin.po.news;
import com.fjtydy.admin.service.newService;
import com.fjtydy.admin.service.Impl.newServiceImpl;
import com.fjtydy.admin.util.status;

@Controller
@CrossOrigin(origins = "*")
public class newController {
	@Autowired
	private newService newservice;
	@Autowired
	private status statu;

	/**
	 * 接收tinyMCE文件并返回存储路径
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(MultipartFile file) {
		String path = "/usr/local/tydy/news/picture/";
		String adress = "http://www.fjtydy.com";
		String str = null;// 存储返回的JSON内容
		ObjectMapper om = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();
		if (file != null) {
			String originalFilename = file.getOriginalFilename();
			String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
			String fileName = UUID.randomUUID().toString().trim().replaceAll("-", "") + fileSuffix;
			File filePath = new File(path + fileName);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			try {
				file.transferTo(filePath);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("location", adress + "/newImage/" + fileName);
			try {
				str = om.writeValueAsString(map);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return str;
	}

	/**
	 * 跳转至新闻页面并查询新闻内容(管理后台)
	 */
	@RequestMapping(value = "/queryNew", method = RequestMethod.GET)
	public String queryNew(Model model) {
		List<news> newList = this.newservice.queryNew();
		model.addAttribute("newList", newList);
		return "new";
	}

	/**
	 * 跳转至新闻页面并查询新闻内容(客户端)
	 */
	@RequestMapping(value = "/queryNews", method = RequestMethod.GET)
	public String queryNews(Model model) {
		List<news> newsList = this.newservice.queryNews();
		model.addAttribute("newsList", newsList);
		return "news";
	}

	/**
	 * 新闻列表(客户端)
	 */
	@RequestMapping(value = "/newList", method = RequestMethod.GET)
	@ResponseBody
	public String queryNewsList() {
		List<news> newList = this.newservice.queryNewsList();
		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, newList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	/**
	 * 最新新闻(客户端)
	 */
	@RequestMapping(value = "/newIndex", method = RequestMethod.GET)
	@ResponseBody
	public String queryNewsIndex() {
		List<news> newIndex = this.newservice.queryNewsIndex();
		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, newIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	/**
	 * 添加新闻并生成新闻页面
	 */
	@RequestMapping(value = "/insertNew", method = RequestMethod.POST)
	@ResponseBody
	public String insertNew(@RequestParam("title") String title, @RequestParam("introduce") String introduce,
			@RequestParam("dates") String dates, @RequestParam("content") String content,
			@RequestParam("imgPath") MultipartFile imgPath) {

		// 存储图片并生成路径
		String imgName = this.addImgPath(imgPath);
		// 新增页面并生成路径
		String pageName = this.addNewPage(title, dates, content);
		// 添加数据库
		int row = this.newservice.insertNew(title, introduce, dates, content, "newImage/" + imgName,
				"newPage/" + pageName);
		if (row > 0) {
			String str = statu.setStatus("code", "yes");
			return str;
		} else {
			String str = statu.setStatus("code", "no");
			return str;
		}
	}

	/**
	 * 编辑新闻(主图修改)
	 */
	@RequestMapping(value = "/updateNew", method = RequestMethod.POST)
	@ResponseBody
	public String updateNew(@RequestParam("new_id") int new_id, @RequestParam("title") String title,
			@RequestParam("introduce") String introduce, @RequestParam("dates") String dates,
			@RequestParam("content") String content, @RequestParam("imgPath") MultipartFile imgPath) {

		// 存储图片并生成路径
		String imgName = this.addImgPath(imgPath);
		// 新增页面并生成路径
		String pageName = this.addNewPage(title, dates, content);
		// 更新数据
		int row = this.newservice.updateNew(new_id, title, introduce, dates, content, "newImage/" + imgName,
				"newPage/" + pageName);
		if (row > 0) {
			String str = statu.setStatus("code", "yes");
			return str;
		} else {
			String str = statu.setStatus("code", "no");
			return str;
		}
	}

	/**
	 * 编辑新闻(主图没有修改)
	 */
	@RequestMapping(value = "/setNew", method = RequestMethod.POST)
	@ResponseBody
	public String setNew(@RequestParam("new_id") int new_id, @RequestParam("title") String title,
			@RequestParam("introduce") String introduce, @RequestParam("dates") String dates,
			@RequestParam("content") String content) {

		// 新增页面并生成路径
		String pageName = this.addNewPage(title, dates, content);
		// 更新数据
		int row = this.newservice.setNew(new_id, title, introduce, dates, content, "newPage/" + pageName);
		if (row > 0) {
			String str = statu.setStatus("code", "yes");
			return str;
		} else {
			String str = statu.setStatus("code", "no");
			return str;
		}
	}

	/**
	 * 删除新闻
	 */
	@RequestMapping(value = "/deleteNew", method = RequestMethod.GET)
	@ResponseBody
	public String deleteNew(String[] new_id) {
		List<Integer> list = new ArrayList<Integer>();
		int ids;
		for (String string : new_id) {
			ids = Integer.parseInt(string);
			list.add(ids);
		}
		int row = this.newservice.deleteNew(list);
		String str = null;
		if (row > 0) {// 删除成功
			str = statu.setStatus("code", "yes");
			return str;
		} else {// 删除失败
			str = statu.setStatus("code", "no");
			return str;
		}
	}

	/**
	 * 该方法用于存储新闻主图并返回存储路径
	 */
	public String addImgPath(MultipartFile imgPath) {
		String path = "/usr/local/tydy/news/picture/";
		String originalFilename = imgPath.getOriginalFilename();
		String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
		String imgName = UUID.randomUUID().toString().trim().replaceAll("-", "") + suffixName;
		File filePath = new File(path + imgName);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		try {
			imgPath.transferTo(filePath);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return imgName;
	}

	/**
	 * 该方法用于动态新增新闻页面 参数：标题、日期、内容
	 */
	public String addNewPage(String title, String dates, String content) {
		String pagePath = "/usr/local/tydy/news/page/";
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(date);
		String pageName = dateString + "." + "html";
		File newFile = new File(pagePath + pageName);
		if (!newFile.exists()) {
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>" + "\n");
		sb.append("<html lang=\"zh-CN\">" + "\n");
		sb.append("<head>" + "\n");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">" + "\n");
		sb.append("<title>" + title + "</title>" + "\n");
		sb.append("<meta name=\"keywords\" content=\"天叶丹云,三叶青,关于三叶青,天叶丹云官网,三叶青是什么,天叶丹云公司怎么样,永泰三叶青\">" + "\n");
		sb.append("<meta name=\"description\" content=\"福建天叶丹云生物科技有限公司专注于三叶青产业链，集种植、研发、销售于一体，为客户提供纯天然农副产品。\">" + "\n");
		sb.append(
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0\">"
						+ "\n");
		sb.append("<meta name=\"author\" content=\"黄滨\">" + "\n");
		sb.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">" + "\n");
		sb.append("<link rel=\"shortcut icon\" href=\"logo-ico.png\">" + "\n");
		sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"public.css\">" + "\n");
		sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"new-tool.css\">" + "\n");
		sb.append("</head>" + "\n");
		sb.append("<noscript>Sorry, your browser does not support JavaScript!</noscript>" + "\n");
		/* 头部 */
		sb.append("<div id=\"header\">" + "\n");
		sb.append("<div class=\"logo-area\">" + "\n");
		sb.append("<a href=\"http://www.fjtydy.com/index.html\">" + "\n");
		sb.append("<img src=\"logo.png\" alt=\"天叶丹云\">" + "\n");
		sb.append("</a>" + "\n");
		sb.append("</div>" + "\n");
		sb.append("<div class=\"navigation\">" + "\n");
		sb.append("<ul class=\"nav\">" + "\n");
		sb.append("<li><a href=\"http://www.fjtydy.com/index.html\">首页</a></li>" + "\n");
		sb.append("<li><a href=\"http://www.fjtydy.com/queryProduct\">产品展示</a></li>" + "\n");
		sb.append("<li><a href=\"http://www.fjtydy.com/aboutUs.html\">关于我们</a></li>" + "\n");
		sb.append("<li><a href=\"http://www.fjtydy.com/invite.html\">招商合作</a></li>" + "\n");
		sb.append("<li><a href=\"http://www.fjtydy.com/queryNews\">新闻动态</a></li>" + "\n");
		sb.append("<li><a href=\"http://www.fjtydy.com/contact.html\">联系我们</a></li>" + "\n");
		sb.append("</ul>" + "\n");
		sb.append("</div>" + "\n");
		sb.append("</div>" + "\n");
		/* 中间 */
		sb.append("<div id=\"center\">" + "\n");
		/* 头部导航 */
		sb.append("<div class=\"top-nav\">" + "\n");
		sb.append("<ul>" + "\n");
		sb.append("<li><a href=\"http://www.fjtydy.com/index.html\" target=\"_blank\">首页</a></li>" + "\n");
		sb.append("<li><img src=\"nav.png\" style=\"width: 8px;height: 12px;\"></li>" + "\n");
		sb.append("<li><a href=\"http://www.fjtydy.com/queryNews\">新闻动态</a></li>" + "\n");
		sb.append("<li><img src=\"nav.png\" style=\"width: 8px;height: 12px;\"></li>" + "\n");
		sb.append("<li>文章详情</li>" + "\n");
		sb.append("</ul>" + "\n");
		sb.append("</div>" + "\n");
		/* 文章内容 */
		sb.append("<div class=\"article\">" + "\n");
		sb.append("<div class=\"titleArea\">" + "\n");
		/* 新闻标题 */
		sb.append("<p class=\'title\'>" + title + "</p>" + "\n");
		/* 新闻日期 */
		sb.append("<p class=\'date\'>" + dates + "</p>" + "\n");
		sb.append("</div>" + "\n");
		sb.append("<div class=\"words\">" + "\n");
		/* 新闻 */
		sb.append(content);
		sb.append("" + "\n");
		sb.append("</div>" + "\n");
		sb.append("</div>" + "\n");
		sb.append("</div>" + "\n");
		/* 底部 */
		sb.append("<div id=\"footer\">" + "\n");
		sb.append("<div class=\"footer-item\">" + "\n");
		sb.append("<div class=\"contact\">" + "\n");
		sb.append("<p>办公地址: 福州市仓山区金桔一路115号</p>" + "\n");
		sb.append("<p>种植地址：福州市永泰县丹云乡赤岸村</p>" + "\n");
		sb.append("<p>服务热线: 0591-86258511</p>" + "\n");
		sb.append("<p>" + "\n");
		sb.append(
				"<a href=\"http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=35012502000143\" target=\"_blank\" "
						+ "style=\"color: rgb(255,255,255);\"><img src=\"bak.png\" style=\"padding-right: 5px;\">闽公网安备 35012502000143号</a>"
						+ "\n");
		sb.append(
				"<a href=\"https://beian.miit.gov.cn\" target=\"_blank\" style=\"color: rgb(255,255,255);margin-left: 10px;\">闽ICP备2022011206号</a>"
						+ "\n");
		sb.append("</p>" + "\n");
		sb.append("<p>Copyright © 2022 福建天叶丹云生物科技有限公司. All Rights Reserved. 天叶公司 版权所有</p>" + "\n");
		sb.append("</div>" + "\n");
		sb.append("<div class=\"gzh\">" + "\n");
		sb.append("<img src=\"public.jpg\" style=\"width: 119px; height: 117px;\">" + "\n");
		sb.append("<p>微信公众号</p>" + "\n");
		sb.append("</div>" + "\n");
		sb.append("</div>" + "\n");
		sb.append("</div>" + "\n");
		sb.append("<div class=\"backTop\">" + "\n");
		sb.append("<a href=\"#\"><img src=\"back-top.png\" title=\"回到顶部\"></a>" + "\n");
		sb.append("</div>" + "\n");
		sb.append("</body>" + "\n");
		sb.append("</html>" + "\n");

		/* 写入文件 */
		try {
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(newFile), "UTF-8");
			osw.write(sb.toString());
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageName;
	}

}
