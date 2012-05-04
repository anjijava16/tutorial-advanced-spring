package com.citygrid.training.spring.advanced.mybatis;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Objects;

public class Feed implements Serializable {
    private static final long serialVersionUID = 4709564932345166022L;
    
    private int feedId;
    private String feedCode;
    private String feedName;
    private int priority;
    private boolean active;

    public Feed() {
    }

    public Feed(int feedId, String feedCode, String feedName, int priority, boolean active) {
        this.feedId = feedId;
        this.feedCode = feedCode;
        this.feedName = feedName;
        this.priority = priority;
        this.active = active;
    }
    
    public int getFeedId() {
        return feedId;
    }

    @Required
    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }

    public String getFeedCode() {
        return feedCode;
    }

    @Required
    public void setFeedCode(String feedCode) {
        this.feedCode = feedCode;
    }

    public String getFeedName() {
        return feedName;
    }

    @Required
    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    public int getPriority() {
        return priority;
    }

    @Required
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isActive() {
        return active;
    }

    @Required
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("feedId", feedId)
                .add("feedCode", feedCode)
                .add("feedName", feedName)
                .add("priority", priority)
                .add("isActive", active)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (obj != null && obj instanceof Feed) {
            Feed other = (Feed) obj;

            result = feedId == other.feedId && Objects.equal(feedCode, other.feedCode)
                    && Objects.equal(feedName, other.feedName) && priority == other.priority
                    && active == other.active;
        }

        return result;
    }
    
    @Override
    public int hashCode() {
        return feedId;
    }
}
