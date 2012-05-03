package com.citygrid.training.spring.advanced.jdbc.dao;

import java.util.Collection;

import com.citygrid.training.spring.advanced.jdbc.model.Feed;

public interface FeedDao {
    public int getCount();
    
    public Collection<Feed> findAll();
    
    public Feed findByFeedId(int feedId);
    
    public Feed insert(final Feed feed);
    
    public void update(final Feed feed);
    
    public void delete(int feedId);
}
