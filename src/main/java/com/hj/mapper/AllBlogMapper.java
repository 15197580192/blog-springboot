package com.hj.mapper;

import com.hj.entity.AllBlog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author hzy
 * @since 2021-11-24
 */
public interface AllBlogMapper extends BaseMapper<AllBlog> {

    @MapKey("blog_id")
    @Select("select max(blog_id) as max from all_blog")
    Map<String, AllBlog> getMaxBlogIdMap();
}
