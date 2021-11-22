package com.hj.mapper;

import com.hj.entity.BlogDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 博客详情表 Mapper 接口
 * </p>
 *
 * @author hzy
 * @since 2021-11-21
 */

public interface BlogDetailsMapper extends BaseMapper<BlogDetails> {
//    @Insert("INSERT INTO blog_destails(blog_id,blog_title,blog_content) VALUES(#{blogId}, #{blogTitle}, #{blogContent})")
//    int addBlog(BlogDetails blogDetails);
}
