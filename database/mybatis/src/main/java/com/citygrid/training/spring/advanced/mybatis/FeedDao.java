package com.citygrid.training.spring.advanced.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface FeedDao {
    @Select("select * from feed")
    @Results(value = {
            @Result(property = "feedId", column = "feed_id"),
            @Result(property = "feedCode", column = "feed_code"),
            @Result(property = "feedName", column = "feed_name"),
            @Result(property = "priority", column = "priority"),
            @Result(property = "active", column = "active")
            })
    public List<Feed> findAll();

    @Select("select * from feed where feed_id = #{feedId}")
    @Results(value = {
            @Result(property = "feedId", column = "feed_id"),
            @Result(property = "feedCode", column = "feed_code"),
            @Result(property = "feedName", column = "feed_name"),
            @Result(property = "priority", column = "priority"),
            @Result(property = "active", column = "active")
            })
    public Feed findFeedById(int feedId);
    
    @Update("update feed set feed_code = #{feedCode}, feed_name = #{feedName}, priority = #{priority}, active = #{active} where feed_id = #{feedId}")    
    public void update(final Feed feed);
    
    @Insert("insert into feed (feed_code, feed_name, priority, active) values (#{feedCode}, #{feedName}, #{priority}, #{active})")
    public void insert(final Feed feed);
}
