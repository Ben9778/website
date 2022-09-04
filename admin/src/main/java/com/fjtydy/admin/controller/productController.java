package com.fjtydy.admin.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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

import com.fjtydy.admin.po.product;
import com.fjtydy.admin.service.productService;
import com.fjtydy.admin.service.Impl.productServiceImpl;
import com.fjtydy.admin.util.status;

@Controller   
@CrossOrigin(origins = "*")
public class productController {  
	@Autowired
	private productService productservice;   
	@Autowired 
	private status statu;
	/* 添加产品 */
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String insertProduct(@RequestParam("product_name") String product_name,
			MultipartFile pic, MultipartFile detail) {
		// 图片路径
		String path = "/usr/local/tydy/products/picture/";
		//页面路径
		String path1="/usr/local/tydy/products/page/";
		// 图片名
		String originalFilename1 = pic.getOriginalFilename();
		String originalFilename2 = detail.getOriginalFilename();
		String picSuffix = originalFilename1.substring(originalFilename1.lastIndexOf("."));
		String detailSuffix = originalFilename2.substring(originalFilename2.lastIndexOf("."));
		String picName = UUID.randomUUID().toString().trim().replaceAll("-", "") + picSuffix;
		String detailName = UUID.randomUUID().toString().trim().replaceAll("-", "") + detailSuffix;
		// 文件路径
		File picFile = new File(path + picName);
		File detailFile = new File(path + detailName);
		if (!picFile.exists() || !detailFile.exists()) {
			picFile.mkdirs();
			detailFile.mkdirs();
		}
		try {
			pic.transferTo(picFile);
			detail.transferTo(detailFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(date);
		String pageName=dateString+"."+"html";
		//添加页面
		this.addPage(product_name,"http://www.fjtydy.com/image/"+detailName,path1+pageName);
		// 插入数据库
		if (pic != null && detail != null && product_name != null) {
			int row=productservice.insertProduct(product_name, "image/" + picName, "image/" + detailName,"page/"+pageName);
			if(row>0) {
				return "redirect:queryProducts";
			}else {
				return "error";
			}
		}
		return "redirect:queryProducts";
	}
	/*删除产品*/
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	@ResponseBody
	public String deleteProduct(@RequestParam("product_id")String[] product_id) {
		List<Integer> list=new ArrayList<Integer>(); 
        int ids;
        for (String string : product_id) { 
            ids=Integer.parseInt(string); 
            list.add(ids); 
            }
		int row=this.productservice.deleteProduct(list);
		String str=null;
		if(row>0) {//删除成功
			str=statu.setStatus("code","yes");
			return str;
		}else {//删除失败 
			str=statu.setStatus("code","no");
			return str;
		}

	}
	/*后端查询产品*/ 
	@RequestMapping(value="/queryProducts", method = RequestMethod.GET)
	public String queryProduct(Model model) {
		List<product>productlist=this.productservice.queryProduct();
		model.addAttribute("productList", productlist);

		return "product";
	}
	
	/*前端查询产品*/
	@RequestMapping(value="/queryProduct", method = RequestMethod.GET)
	public String queryProducts(Model model) {
		List<product>productslist=this.productservice.queryProducts();
		model.addAttribute("productsList", productslist);

		return "products";
	}
	
	
	/**
	 * 该方法用于动态新增product详情页面
	 * */
	public void addPage(String title,String url,String pagePath) {

		File productFile = new File(pagePath);
		if (!productFile.exists()) {
			try {
				productFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>" + "\n");
		sb.append("<html lang=\"zh-CN\">" + "\n");
		sb.append("<head>"+"\n");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">"+"\n");
		sb.append("<title>"+title+"</title>"+"\n");
		sb.append("<meta name=\"keywords\" content=\"天叶丹云,三叶青,关于三叶青,天叶丹云官网,三叶青是什么,天叶丹云公司怎么样,永泰三叶青\">"+"\n");
		sb.append("<meta name=\"description\" content=\"福建天叶丹云生物科技有限公司专注于三叶青产业链，集种植、研发、销售于一体，为客户提供纯天然农副产品。\">"+"\n");
		sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0\">"+"\n");
		sb.append("<meta name=\"author\" content=\"黄滨\">"+"\n");
		sb.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">"+"\n");
		sb.append("<link rel=\"shortcut icon\" href=\"logo-ico.png\">"+"\n");
		sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"public.css\">"+"\n");
		sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"product-tool.css\">"+"\n");
		sb.append("</head>"+"\n");
		sb.append("<noscript>Sorry, your browser does not support JavaScript!</noscript>"+"\n");
		/*头部*/
		sb.append("<div id=\"header\">"+"\n");
		sb.append("<div class=\"logo-area\">"+"\n");
		sb.append("<a href=\"http://www.fjtydy.com/index.html\">"+"\n");
		sb.append("<img src=\"logo.png\" alt=\"天叶丹云\">"+"\n");
		sb.append("</a>"+"\n");
		sb.append("</div>"+"\n");
		sb.append("<div class=\"navigation\">"+"\n");
		sb.append("<ul class=\"nav\">"+"\n");
		sb.append("<li><a href=\"http://www.fjtydy.com/index.html\">首页</a></li>"+"\n");
		sb.append("<li><a href=\"http://www.fjtydy.com/queryProduct\">产品展示</a></li>"+"\n");
		sb.append("<li><a href=\"http://www.fjtydy.com/aboutUs.html\">关于我们</a></li>"+"\n");
		sb.append("<li><a href=\"http://www.fjtydy.com/invite.html\">招商合作</a></li>"+"\n");
		sb.append("<li><a href=\"http://www.fjtydy.com/queryNews\">新闻动态</a></li>"+"\n");
		sb.append("<li><a href=\"http://www.fjtydy.com/contact.html\">联系我们</a></li>"+"\n");
		sb.append("</ul>"+"\n");
		sb.append("</div>"+"\n");
		sb.append("</div>"+"\n");
		/*中间*/
		sb.append("<div id=\"center\">"+"\n");
		sb.append("<div class=\"banners\"></div>"+"\n");
		/*产品*/
		sb.append("<div class=\"displays\">"+"\n");
		sb.append("<img src='"+url+"'>"+"\n");
		sb.append("</div>"+"\n");
		sb.append("</div>"+"\n");
		/*底部*/
		sb.append("<div id=\"footer\">"+"\n");
		sb.append("<div class=\"footer-item\">"+"\n");
		sb.append("<div class=\"contact\">"+"\n");
		sb.append("<p>办公地址: 福州市仓山区金桔一路115号</p>"+"\n");
		sb.append("<p>种植地址：福州市永泰县丹云乡赤岸村</p>"+"\n");
		sb.append("<p>服务热线: 0591-86258511</p>"+"\n");
		sb.append("<p>"+"\n");
		sb.append("<a href=\"http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=35012502000143\" target=\"_blank\" "
				+ "style=\"color: rgb(255,255,255);\"><img src=\"bak.png\" style=\"padding-right: 5px;\">闽公网安备 35012502000143号</a>"+"\n");
		sb.append("<a href=\"https://beian.miit.gov.cn\" target=\"_blank\" style=\"color: rgb(255,255,255);margin-left: 10px;\">闽ICP备2022011206号</a>"+"\n");
		sb.append("</p>"+"\n");
		sb.append("<p>Copyright © 2022 福建天叶丹云生物科技有限公司. All Rights Reserved. 天叶公司 版权所有</p>"+"\n");
		sb.append("</div>"+"\n");
		sb.append("<div class=\"gzh\">"+"\n");
		sb.append("<img src=\"public.jpg\" style=\"width: 119px; height: 117px;\">"+"\n");
		sb.append("<p>微信公众号</p>"+"\n");
		sb.append("</div>"+"\n");
		sb.append("</div>"+"\n");
		sb.append("</div>"+"\n");
		sb.append("<div class=\"backTop\">"+"\n");
		sb.append("<a href=\"#\"><img src=\"back-top.png\" title=\"回到顶部\"></a>"+"\n");
		sb.append("</div>"+"\n");
		sb.append("</body>"+"\n");
		sb.append("</html>"+"\n");
		
		/*写入文件*/
		try {
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(productFile), "UTF-8");
			osw.write(sb.toString());
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
