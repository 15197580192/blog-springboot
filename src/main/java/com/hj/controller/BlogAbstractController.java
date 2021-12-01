package com.hj.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hj.common.dto.BlogEditDto;
import com.hj.common.dto.CommentDto;
import com.hj.common.dto.DelCommentDto;
import com.hj.common.dto.GetMyCommentDto;
import com.hj.common.lang.Result;
import com.hj.entity.*;
import com.hj.service.*;
import com.hj.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 博客摘要表 前端控制器
 * </p>
 *
 * @author hzy
 * @since 2021-11-21
 */
@RestController
public class BlogAbstractController {
    @Autowired
    BlogDetailsService blogDetailsService;
    @Autowired
    BlogAbstractService blogAbstractService;
    @Autowired
    AllBlogService allBlogService;
    @Autowired
    CommentService commentService;
    @Autowired
    AllCommentService allCommentService;


    //新闻博客列表
    @GetMapping("/blogs")
    public Result blogs(Integer currentPage) {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 5);
        IPage pageData = allBlogService.page(page, new QueryWrapper<AllBlog>().orderByDesc("blog_id"));
        return Result.success(pageData);
    }

    //我的博客列表
    @GetMapping("/my/blogs")
    public Result myBlogs(String userId,Integer currentPage) {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 5);
        IPage pageData = allBlogService.page(page, new QueryWrapper<AllBlog>().orderByDesc("blog_id").eq("user_id",Long.parseLong(userId)));
        return Result.success(pageData);
    }

    //查看博客详情
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        BlogDetails blog = blogDetailsService.getById(id);
        BlogAbstract aBlog = blogAbstractService.getById(id);
        Assert.notNull(blog, "该博客已删除！");
        //return Result.success(blog);
        return Result.success(MapUtil.builder()
                .put("userId",aBlog.getUserId())
                .put("blogDetails",blog)
                .map()
        );
    }

    //删除博客
    @PostMapping("/blog/delete/{id}")
    public Result deleteBlog(@RequestBody @PathVariable(name = "id")Long id) {
        blogDetailsService.removeById(id);
        blogAbstractService.removeById(id);

        //return Result.success(blog);
        return Result.success("博客删除成功");
    }

    //查看评论
    @PostMapping("/blog/{id}/comments")
    public Result getComment(@RequestBody @PathVariable(name = "id") Long id) {
        List<AllComment> comment = allCommentService.list(new QueryWrapper<AllComment>().eq("blog_id",id).eq("parent_comment_id",0).orderByAsc("comment_date"));
        return Result.success(comment);
    }

    //查看我的评论
    @GetMapping("/my/comments")
    public Result getMyComment(String userId,Integer currentPage) {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 5);
        IPage pageData = allCommentService.page(page, new QueryWrapper<AllComment>().orderByDesc("comment_date").eq("user_id",Long.parseLong(userId)));
        return Result.success(pageData);
    }

    //查看子评论
    @PostMapping("/blog/{id}/comments/child")
    public Result getChildComment(@PathVariable(name = "id") Long id, @RequestBody Comment commentDto) {
        List<Comment> comments = commentService.list(new QueryWrapper<Comment>().eq("blog_id",id).eq("parent_comment_id",commentDto.getCommentId()).orderByAsc("comment_date"));
        System.out.println(comments);
        return Result.success(comments);
    }

    //增加评论
    @PostMapping("/blog/{id}/comment")
    public Result comment(@PathVariable(name = "id") Long id, @Validated @RequestBody CommentDto commentDto) {

        // 读取数据库中最大评论id
        Comment comment = commentService.getOne(new QueryWrapper<Comment>().orderByDesc("comment_id").last("limit 1"));
        // 新增评论的id为最大评论id加1
        Long count = comment.getCommentId();
        count++;
        Comment comment1 = new Comment();
        BeanUtil.copyProperties(commentDto, comment1, "commentId","blogId");
        comment1.setCommentId(count);
        comment1.setCommentDate(LocalDateTime.now());
        comment1.setBlogId(id);
        //没有父评论，设置自己为父评论
        if(commentDto.getParentCommentId()==null) {
            comment1.setParentCommentId(0L);
        }
        commentService.saveOrUpdate(comment1);
        return Result.success(comment1);
    }

    //删除评论
    @PostMapping("/blog/{id}/comment/delete")
    public Result delComment(@PathVariable(name = "id") Long id, @Validated @RequestBody DelCommentDto commentDto) {
        //评论发表人
        String userId = commentService.getOne(new QueryWrapper<Comment>().eq("comment_id",commentDto.getCommentId())).getUserId();
        //博客作者
        String blogUserId = allBlogService.getOne(new QueryWrapper<AllBlog>().eq("blog_id",id)).getUserId();

        //评论发表人或博客作者有权限删除评论
        if((!userId.equals(commentDto.getUserId()))&&(!blogUserId.equals(commentDto.getUserId()))) {
            return Result.fail("没有权限删除评论！");
        }
        commentService.removeById(commentDto.getCommentId());
        return Result.success("删除评论成功");
    }

    //登录权限
    @RequiresAuthentication
    @PostMapping("/blog/edit")
    @CrossOrigin
    public Result edit(@Validated @RequestBody BlogEditDto blog) {
        //打印前端传来的数据
        System.out.println(blog.toString());
        BlogDetails temp = null;
        BlogAbstract aTemp = null;
        if(blog.getBlogId() != null) {
            // 编辑状态
            temp = blogDetailsService.getOne(new QueryWrapper<BlogDetails>().eq("blog_id", blog.getBlogId()));
            aTemp = blogAbstractService.getOne(new QueryWrapper<BlogAbstract>().eq("blog_id", blog.getBlogId()));
            //只能编辑自己的文章
            System.out.println(aTemp.getUserId());
            System.out.println(ShiroUtil.getProfile().getUserId());
            Assert.isTrue(aTemp.getUserId() != ShiroUtil.getProfile().getUserId(), "没有权限编辑");
        }
        else { // 发布状态
            temp = new BlogDetails();
            aTemp = new BlogAbstract();
            //读取数据库中博客的数目
            AllBlog allBlog = allBlogService.getOne(new QueryWrapper<AllBlog>().orderByDesc("blog_id").last("limit 1"));

            Long count = allBlog.getBlogId();//Long.valueOf(blogDetailsService.count(new QueryWrapper<BlogDetails>().isNotNull("blog_id")));
            System.out.println(count);
            //数据库博客数加1为新的博客的编号
            count++;
            temp.setBlogId(count);
            aTemp.setBlogId(count);
            //同步博主的id
            aTemp.setUserId(ShiroUtil.getProfile().getUserId());
            aTemp.setBlogPublishTime(LocalDateTime.now());
            // temp.setStatus(0);
        }
        //忽略博客id赋值前端传来的博客信息
        BeanUtil.copyProperties(blog, temp, "blogId");

        //打印更新数据库的数据
        System.out.println(temp.toString());
        System.out.println(aTemp.toString());

        blogAbstractService.saveOrUpdate(aTemp);
        blogDetailsService.saveOrUpdate(temp);
        return Result.success("操作成功");
    }

    //搜索博客
    @GetMapping("/blog/search")
    public Result search(String searchBlog,Integer currentPage) {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 5);
        IPage pageData = allBlogService.page(page, new QueryWrapper<AllBlog>().orderByDesc("blog_id").like(searchBlog!=null,"blog_title",searchBlog));
        return Result.success(pageData);
    }

}
