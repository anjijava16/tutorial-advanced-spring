package com.citygrid.training.spring.advanced.jdbc.dao.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.citygrid.training.spring.advanced.jdbc.dao.FeedDao;
import com.citygrid.training.spring.advanced.jdbc.model.Feed;
import com.google.common.collect.ImmutableList;

@Repository("feedDao")
public class JDBCTemplateFeedDao extends JdbcDaoSupport implements FeedDao, InitializingBean {
    private final static Logger LOGGER = Logger.getLogger(JDBCTemplateFeedDao.class.getName());

    public final static String COL_FEED_ID = "feed_id";
    public final static String COL_FEED_CODE = "feed_code";
    public final static String COL_FEED_NAME = "feed_name";
    public final static String COL_PRIORITY = "priority";
    public final static String COL_ACTIVE = "active";

    private static final class FeedMapper implements RowMapper<Feed> {
        public Feed mapRow(ResultSet rs, int rowNum) throws SQLException {
            Feed feed = new Feed();

            feed.setFeedId(rs.getInt(COL_FEED_ID));
            feed.setFeedCode(rs.getString(COL_FEED_CODE));
            feed.setFeedName(rs.getString(COL_FEED_NAME));
            feed.setPriority(rs.getInt(COL_PRIORITY));
            feed.setActive(rs.getBoolean(COL_ACTIVE));

            return feed;
        }
    }

    private static final class FeedUpdater extends SqlUpdate {
        private static final String SQL_UPDATE_FEED = "update feed set feed_code=:feed_code, feed_name=:feed_name, priority=:priority, active=:active where feed_id=:feed_id";

        public FeedUpdater(final DataSource dataSource) {
            super(dataSource, SQL_UPDATE_FEED);
            
            super.declareParameter(new SqlParameter("feed_id", Types.INTEGER));
            super.declareParameter(new SqlParameter("feed_code", Types.VARCHAR));
            super.declareParameter(new SqlParameter("feed_name", Types.VARCHAR));
            super.declareParameter(new SqlParameter("priority", Types.INTEGER));
            super.declareParameter(new SqlParameter("active", Types.BOOLEAN));
        }
    }

    private static final class FeedInserter extends SqlUpdate {
        private static final String SQL_INSERT_FEED = "insert into feed (feed_code, feed_name, priority, active) values (:feed_code, :feed_name, :priority, :active)";

        public FeedInserter(final DataSource dataSource) {
            super(dataSource, SQL_INSERT_FEED);
            
            super.setGeneratedKeysColumnNames(new String[]{"feed_id"});
            super.declareParameter(new SqlParameter("feed_code", Types.VARCHAR));
            super.declareParameter(new SqlParameter("feed_name", Types.VARCHAR));
            super.declareParameter(new SqlParameter("priority", Types.INTEGER));
            super.declareParameter(new SqlParameter("active", Types.BOOLEAN));
            
            super.setReturnGeneratedKeys(true);
        }
    }

    private FeedUpdater feedUpdater;
    private FeedInserter feedInserter;

    @Autowired
    public JDBCTemplateFeedDao(final DataSource dataSource) {
        this.setDataSource(dataSource);

        feedUpdater = new FeedUpdater(dataSource);
        feedInserter = new FeedInserter(dataSource);
    }

    public int getCount() {
        final String sql = "select count(1) from feed";

        return this.getJdbcTemplate().queryForInt(sql);

    }

    public Collection<Feed> findAll() {
        final String sql = "select feed_id, feed_code, feed_name, priority, active from feed";

        return ImmutableList.<Feed> copyOf(this.getJdbcTemplate().query(sql, new FeedMapper()));
    }

    public Feed findByFeedId(int feedId) {
        final String sql = "select feed_id, feed_code, feed_name, priority, active from feed where feed_id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{feedId}, Feed.class);
    }

    public Feed insert(final Feed feed) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("feed_code", feed.getFeedCode());
        paramMap.put("feed_name", feed.getFeedName());
        paramMap.put("priority", feed.getPriority());
        paramMap.put("active", feed.isActive());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        feedInserter.updateByNamedParam(paramMap, keyHolder);

        Feed insertedFeed = new Feed(keyHolder.getKey().intValue(), feed.getFeedCode(),
                feed.getFeedName(), feed.getPriority(), feed.isActive());

        LOGGER.info(insertedFeed);
        
        return insertedFeed;
    }

    public void update(final Feed feed) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("feed_id", feed.getFeedId());
        paramMap.put("feed_code", feed.getFeedCode());
        paramMap.put("feed_name", feed.getFeedName());
        paramMap.put("priority", feed.getPriority());
        paramMap.put("active", feed.isActive());

        LOGGER.info(feedUpdater.updateByNamedParam(paramMap) + " row(s) is/are updated.");
    }

    public void delete(int feedId) {
        final String sql = "delete from feed where feed_Id = ?";
        
        LOGGER.info(this.getJdbcTemplate().update(sql, new Object[] { feedId }) + " row(s) is/are deleted");
    }
}
