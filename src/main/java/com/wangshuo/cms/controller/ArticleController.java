package com.wangshuo.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.wangshuo.cms.domain.Article;
import com.wangshuo.cms.service.ArticleService;
import com.wangshuo.cms.util.PageUtil;

@RequestMapping("article")
@Controller
public class ArticleController {

	@Resource
	private ArticleService articleService;
	
	@RequestMapping("selectsByAdmin")
	public String selects(Model model,Article article ,@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "3")Integer pageSize) {
		
		PageInfo<Article> info = articleService.selects(article, page, pageSize);
	    String pages = PageUtil.page(page, info.getPages(), "/selects", pageSize);
	    model.addAttribute("articles", info.getList());
	    model.addAttribute("pages", pages);
	    model.addAttribute("article", article);
	    
		
		return "admin/articles";
		
	}
	
	
	
	
}
