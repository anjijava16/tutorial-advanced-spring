package com.citygrid.training.spring.advanced.mybatis;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MyBatisExample {
    private final static Logger LOGGER = Logger.getLogger(MyBatisExample.class.getName());

    public static void main(final String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:application-context.xml");
        FeedDao feedDao = (FeedDao) ctx.getBean("feedDao");
        
        LOGGER.info("Getting all the feeds in the database");
        Collection<Feed> feeds = feedDao.findAll();
        for (Feed feed : feeds) {
            LOGGER.info(feed);
        }
        
        LOGGER.info("Seeking for feed with id = 1");
        Feed feed = feedDao.findFeedById(1);
        LOGGER.info(feed);
        
        LOGGER.info("Setting the feed inactive");
        feed.setActive(false);
        feedDao.update(feed);
        LOGGER.info(feedDao.findFeedById(feed.getFeedId()));
        
        Feed newFeed = new Feed(0, "yodle", "yodle", 4, true);
        LOGGER.info("Adding a new feed: " + newFeed.toString());
        feedDao.insert(newFeed);
        
        LOGGER.info("Getting all the feeds in the database again");
        feeds = feedDao.findAll();
        for (Feed f : feeds) {
            LOGGER.info(f);
        }        
    }

}
