package com.wangshuo.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangshuo.cms.domain.Category;
import com.wangshuo.cms.service.CategoryService;
/**
 * 分类controller
 * @ClassName: ChannelController 
 * @Description: TODO
 * @author: 王硕
 * @date: 2019年9月17日 上午9:30:10
 */
@RequestMapping("category")
@Controller
public class CategoryController {
	@Resource
	private CategoryService categoryService;
	
	@ResponseBody
	@GetMapping("selects")
	public List<Category> selects(Integer cid){
		return categoryService.selectsByChannelId(cid)	;
	}

}
