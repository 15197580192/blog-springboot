package com.hj.service.impl;

import com.hj.entity.BlogDetails;
import com.hj.mapper.BlogDetailsMapper;
import com.hj.service.BlogDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客详情表 服务实现类
 * </p>
 *
 * @author hzy
 * @since 2021-11-21
 */
@Service
public class BlogDetailsServiceImpl extends ServiceImpl<BlogDetailsMapper, BlogDetails> implements BlogDetailsService {

}
