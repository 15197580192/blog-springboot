package com.hj.service.impl;

import com.hj.entity.BlogAbstract;
import com.hj.mapper.BlogAbstractMapper;
import com.hj.service.BlogAbstractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客摘要表 服务实现类
 * </p>
 *
 * @author hzy
 * @since 2021-11-21
 */
@Service
public class BlogAbstractServiceImpl extends ServiceImpl<BlogAbstractMapper, BlogAbstract> implements BlogAbstractService {

}
