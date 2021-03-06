package com.wangshuo.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wangshuo.cms.domain.Article;
import com.wangshuo.cms.domain.Comment;
import com.wangshuo.cms.domain.User;
import com.wangshuo.cms.service.ArticleService;
import com.wangshuo.cms.service.CommentService;
import com.wangshuo.cms.util.PageUtil;
import com.github.pagehelper.PageInfo;

@RequestMapping("article")
@Controller
/**
 * 
 * @ClassName: ArticleController 
 * @Description: TODO
 * @author: 王硕
 * @date: 2019年9月29日 下午2:03:19
 */
public class ArticleController {

	@Resource
	private ArticleService articleService;
	
	@Resource
	private CommentService commentService;

	/**
	 * 添加评论
	 * 
	 * @Title: comment
	 * @Description: TODO
	 * @param comment
	 * @param request
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("comment")
	public boolean comment(Comment comment, HttpServletRequest request) {
		//
		HttpSession session = request.getSession(false);
		if (null == session)
			return false;
		// 评论人
		comment.setUser((User) session.getAttribute("user"));
		comment.setCreated(new Date());
		return commentService.insert(comment) > 0;
	}
	/**
	 *  检查是否有上一篇
	 * @Title: checkPre 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@GetMapping("checkPre")
	public boolean checkPre(Article article) {
		Article pre = articleService.selectPre(article);
		return pre!=null;
	}
	/**
	 *  检查是否有下一篇
	 * @Title: checkPre 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@GetMapping("checkNext")
	public boolean checkNext(Article article) {
		Article pre = articleService.selectNext(article);
		return pre!=null;
	}
/**
 * 
 * @Title: selectPre 
 * @Description: 上一篇
 * @param model
 * @param article
 * @param page
 * @param pageSize
 * @return
 * @return: String
 */
	@GetMapping("selectPre")
	public String selectPre(Model model,Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		//去查询上一篇的内容
		Article pre = articleService.selectPre(article);

		// 评论
		PageInfo<Comment> info = commentService.selects(pre.getId(), page, pageSize);
		String pages = PageUtil.page(page, info.getPages(), "/article/selectByUser", pageSize);

		model.addAttribute("article", pre);//把上一篇内容放model
		model.addAttribute("pages", pages);
		model.addAttribute("comments", info.getList());
		return "my/article";
	}
	
	/**
	 * 
	 * @Title: selectPre 
	 * @Description: 上一篇
	 * @param model
	 * @param article
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
		@GetMapping("selectNext")
		public String selectNext(Model model,Article article, @RequestParam(defaultValue = "1") Integer page,
				@RequestParam(defaultValue = "10") Integer pageSize) {
			//去查询上一篇的内容
			Article next = articleService.selectNext(article);

			// 评论
			PageInfo<Comment> info = commentService.selects(next.getId(), page, pageSize);
			String pages = PageUtil.page(page, info.getPages(), "/article/selectByUser", pageSize);

			model.addAttribute("article", next);//把上一篇内容放model
			model.addAttribute("pages", pages);
			model.addAttribute("comments", info.getList());
			return "my/article";
		}

	/**
	 * 
	 * @Title: select
	 * @Description: 个人查看单个文章
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("selectByUser")
	public String selectByUser(Integer id, Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Article article = articleService.selectByPrimaryKey(id);

		// 评论
		PageInfo<Comment> info = commentService.selects(article.getId(), page, pageSize);
		String pages = PageUtil.page(page, info.getPages(), "/article/selectByUser", pageSize);

		model.addAttribute("article", article);
		model.addAttribute("pages", pages);
		model.addAttribute("comments", info.getList());
		return "my/article";

	}

	/**
	 * 
	 * @Title: selectsByUser
	 * @Description: 个人中心查看文章
	 * @param model
	 * @param article
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@GetMapping("selectsByUser")
	public String selectsByUser(Model model, Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize, HttpServletRequest request) {

		// 个人中心.只能查看自己的文章
		HttpSession session = request.getSession(false);
		if (session == null)
			return "redirect:/passport/login";// session失效,重新登录

		User user = (User) session.getAttribute("user");
		// 只能查看自己的文章
		article.setUserId(user.getId());

		PageInfo<Article> info = articleService.selects(article, page, pageSize);
		String pages = PageUtil.page(page, info.getPages(), "/article/selectsByUser", pageSize);
		model.addAttribute("articles", info.getList());
		model.addAttribute("pages", pages);
		model.addAttribute("article", article);

		return "my/articles";

	}

	/**
	 * 发布文章
	 * 
	 * @Title: publish
	 * @Description: TODO
	 * @param artice
	 * @param file
	 * @return
	 * @return: boolean
	 */

	@Value("${filePath}")
	private String filePath;// 文件上传路径

	@ResponseBody
	@PostMapping("publish")
	public boolean publish(Article artice, MultipartFile file, HttpServletRequest request) {

		// 1.上传文章标题图片
		if (!file.isEmpty()) {

			// 获取原始上传文件的名称//a.jpg
			String originalFilename = file.getOriginalFilename();
			// 为了防止图片名称重复.使用UUID 统一处理上传的名称名称
			String newFilename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			// 文件上传路径
			// String path="d:/pic/";
			File file2 = new File(filePath + newFilename);

			try {
				// 把文件写入硬盘
				file.transferTo(file2);

				// 封装标题图片的地址
				artice.setPicture(newFilename);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		// 2.把文章内容保存到数据库
		// 默认文章的基本属性
		artice.setHot(0);// 文章为非热门
		artice.setStatus(0);// 文章为待审核
		artice.setHits(0);// 文章点击量默认为0
		artice.setDeleted(0);// 文章删除状态0
		artice.setCreated(new Date());// 文章发布时间
		artice.setUpdated(new Date());// 文章修改时间
		// 发布人
		// false:从request获取session.对象,如果没有则返回null.如果有则返回session..
		HttpSession session = request.getSession(false);
		if (session != null) {
			User user = (User) session.getAttribute("user");
			artice.setUserId(user.getId());// 发布人
		} else {
			return false;// 没有登录.不能发布
		}
		return articleService.insertSelective(artice) > 0;

	}
	
	
/**
 * 修改发布文章
 * @Title: publishupdate 
 * @Description: TODO
 * @param artice
 * @param file
 * @param request
 * @return
 * @return: boolean
 */
	@ResponseBody
	@PostMapping("publishupdate")
	public boolean publishupdate(Article artice, MultipartFile file, HttpServletRequest request) {

		// 1.上传文章标题图片
		if (!file.isEmpty()) {

			// 获取原始上传文件的名称//a.jpg
			String originalFilename = file.getOriginalFilename();
			// 为了防止图片名称重复.使用UUID 统一处理上传的名称名称
			String newFilename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			// 文件上传路径
			// String path="d:/pic/";
			File file2 = new File(filePath + newFilename);

			try {
				// 把文件写入硬盘
				file.transferTo(file2);

				// 封装标题图片的地址
				artice.setPicture(newFilename);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		// 2.把文章内容保存到数据库
		// 默认文章的基本属性
		
		artice.setUpdated(new Date());// 文章修改时间
		// 发布人
		// false:从request获取session.对象,如果没有则返回null.如果有则返回session..
		HttpSession session = request.getSession(false);
		if (session != null) {
			User user = (User) session.getAttribute("user");
			artice.setUserId(user.getId());// 发布人
		} else {
			return false;// 没有登录.不能发布
		}
		return articleService.updateByPrimaryKeySelective(artice) > 0;

	}


	/**
	 * 去发布文章页面
	 * 
	 * @Title: publish
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@GetMapping("publish")
	public String publish() {

		return "my/publish";
	}

	/**
	 * 
	 * @Title: selects
	 * @Description: 管理员查看文章
	 * @param model
	 * @param article
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping("selectsByAdmin")
	public String selects(Model model, Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize) {

		if (article.getStatus() == null || article.getStatus().equals(""))
			article.setStatus(0);// 默认待审核状态

		PageInfo<Article> info = articleService.selects(article, page, pageSize);
		String pages = PageUtil.page(page, info.getPages(), "/article/selectsByAdmin?status=" + article.getStatus(),
				pageSize);
		model.addAttribute("articles", info.getList());
		model.addAttribute("pages", pages);
		model.addAttribute("article", article);

		return "admin/articles";

	}

	/**
	 * 
	 * @Title: select
	 * @Description: 管理员查看单个文章
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("selectByAdmin")
	public String select(Integer id, Model model) {
		Article article = articleService.selectByPrimaryKey(id);

		model.addAttribute("article", article);
		return "admin/article";

	}

	/**
	 * 去文章修改页面
	 * 
	 * @Title: update
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@GetMapping("update")
	public String update(Integer id, Model model) {

		Article article = articleService.selectByPrimaryKey(id);

		model.addAttribute("article", article);

		return "/my/articleupdate";

	}

	/**
	 * 
	 * @Title: update
	 * @Description: 更新文章--审核文章,删除文章
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("update")
	public boolean update(Article article) {
		return articleService.updateByPrimaryKeySelective(article) > 0;
	}

	
}
