package com.citygrid.training.spring.advanced.jdbc;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.citygrid.training.spring.advanced.jdbc.dao.FeedDao;
import com.citygrid.training.spring.advanced.jdbc.model.Feed;

public class JDBCExample {
    private final static Logger LOGGER = Logger.getLogger(JDBCExample.class.getName());

    public static void main(final String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:application-context.xml");
        FeedDao feedDao = (FeedDao) ctx.getBean("feedDao");

        LOGGER.info("Database Table Size = " + feedDao.getCount());

        Feed feed1 = new Feed(0, "ypc", "Yellowpages", 5, true);
        feed1 = feedDao.insert(feed1);
        Feed feed2 = new Feed(0, "dex", "Dex", 1, true);
        feed2 = feedDao.insert(feed2);
        Feed feed3 = new Feed(0, "reachlocal", "Reach Local", 5, true);
        feed3 = feedDao.insert(feed3);

        LOGGER.info("Database Table Size = " + feedDao.getCount());

        Collection<Feed> feeds = feedDao.findAll();
        for (Feed feed : feeds) {
            LOGGER.info(feed);
        }

        feed2.setPriority(3);
        feedDao.update(feed2);
        LOGGER.info(feed2);

        feedDao.delete(1);
        feedDao.delete(2);
        feedDao.delete(3);

        LOGGER.info("Database Table Size = " + feedDao.getCount());

        try {
            feedDao.findByFeedId(1);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

}
