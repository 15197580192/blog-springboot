package com.hj.service;

import com.hj.entity.AllBlog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author hzy
 * @since 2021-11-24
 */
public interface AllBlogService extends IService<AllBlog> {

    /**
     * 获取MaxBlogId Map
     * @return
     */
    Map<String, AllBlog> getMaxBlogIdMap();
}
