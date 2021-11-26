package com.hj.service.impl;

import com.hj.entity.AllBlog;
import com.hj.mapper.AllBlogMapper;
import com.hj.service.AllBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author hzy
 * @since 2021-11-24
 */
@Service
public class AllBlogServiceImpl extends ServiceImpl<AllBlogMapper, AllBlog> implements AllBlogService {
    @Resource
    private AllBlogMapper allBlogMapper;

    @Override
    public Map<String, AllBlog> getMaxBlogIdMap() {
        return allBlogMapper.getMaxBlogIdMap();
    }
}
