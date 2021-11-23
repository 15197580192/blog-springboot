package com.hj.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hj.common.dto.BlogEditDto;
import com.hj.common.lang.Result;
import com.hj.entity.BlogAbstract;
import com.hj.entity.BlogDetails;
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

    //博客详情
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        BlogDetails blog = blogDetailsService.getById(id);
        BlogAbstract ablog = blogAbstractService.getById(id);
        Assert.notNull(blog, "该博客已删除！");
        //return Result.success(blog);
        return Result.success(MapUtil.builder()
                .put("userId",ablog.getUserId())
                .put("blogdetails",blog)
                .map()
        );
    }

    //登录权限
    @RequiresAuthentication
    @PostMapping("/blog/edit")
    @CrossOrigin
    public Result edit(@Validated @RequestBody BlogEditDto blog) {
        //打印前端传来的数据
        System.out.println(blog.toString());
        Long longtemp ;
        BlogDetails temp = null;
        BlogAbstract atemp = null;
        if(blog.getBlogId() != null) {
            // 编辑状态
            temp = blogDetailsService.getOne(new QueryWrapper<BlogDetails>().eq("blog_id", blog.getBlogId()));
            atemp = blogAbstractService.getOne(new QueryWrapper<BlogAbstract>().eq("blog_id", blog.getBlogId()));
            //只能编辑自己的文章
            Assert.isTrue(atemp.getUserId() == ShiroUtil.getProfile().getUserId(), "没有权限编辑");
        }
        else { // 发布状态
            temp = new BlogDetails();
            atemp = new BlogAbstract();
            //读取数据库中博客的数目
            Long count = Long.valueOf(blogDetailsService.count(new QueryWrapper<BlogDetails>().isNotNull("blog_id")));
            System.out.println(count);
            //数据库博客数加1为新的博客的编号
            count++;
            temp.setBlogId(count);
            atemp.setBlogId(count);
            //同步博主的id
            atemp.setUserId(ShiroUtil.getProfile().getUserId());
            atemp.setBlogPublishTime(LocalDateTime.now());
            // temp.setStatus(0);
        }
        //忽略博客id赋值前端传来的博客信息
        BeanUtil.copyProperties(blog, temp, "blogId");

        //打印更新数据库的数据
        System.out.println(temp.toString());
        System.out.println(atemp.toString());

        blogAbstractService.saveOrUpdate(atemp);
        blogDetailsService.saveOrUpdate(temp);
        return Result.success("操作成功");
    }

}
