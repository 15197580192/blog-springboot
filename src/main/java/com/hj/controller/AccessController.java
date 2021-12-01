package com.hj.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hj.common.lang.Result;
import com.hj.entity.AllBlog;
import com.hj.entity.BlogAbstract;
import com.hj.entity.User;
import com.hj.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hzy
 * @since 2021-12-01
 */
@RestController
public class AccessController {

    @Autowired
    AccessService accessService;


}
