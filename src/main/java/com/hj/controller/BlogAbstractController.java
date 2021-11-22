package com.hj.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hj.common.dto.BlogEditDto;
import com.hj.common.lang.Result;
import com.hj.entity.BlogAbstract;
import com.hj.entity.BlogDetails;
import com.hj.entity.User;
import com.hj.service.BlogAbstractService;
import com.hj.service.BlogDetailsService;
import com.hj.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    //登录权限
    @RequiresAuthentication
    @PostMapping("/blog/edit")
    @CrossOrigin
    public Result edit(@Validated @RequestBody BlogEditDto blog) {
        //打印前端传来的数据
        System.out.println(blog.toString());

        BlogDetails temp = null;
        BlogAbstract atemp = null;
        if(blog.getBlogId() != null) {
            // 编辑状态
            temp = blogDetailsService.getOne(new QueryWrapper<BlogDetails>().eq("blog_id", blog.getBlogId()));
            atemp = blogAbstractService.getOne(new QueryWrapper<BlogAbstract>().eq("blog_id", blog.getBlogId()));
            //只能编辑自己的文章
            Assert.isTrue(atemp.getUseUserId() == ShiroUtil.getProfile().getUserId(), "没有权限编辑");
        }
        else { // 添加状态
            temp = new BlogDetails();
            atemp = new BlogAbstract();
            temp.setBlogId(1L);
            atemp.setBlogId(1L);
            atemp.setUseUserId(ShiroUtil.getProfile().getUserId());
            atemp.setBlogPublishTime(LocalDateTime.now());
            // temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog, temp, "blogId");

        //打印更新数据库的数据
        System.out.println(temp.toString());
        System.out.println(atemp.toString());

        blogAbstractService.saveOrUpdate(atemp);
        blogDetailsService.saveOrUpdate(temp);
        return Result.success(null);
    }

}
