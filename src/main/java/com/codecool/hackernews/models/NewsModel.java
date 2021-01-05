package com.codecool.hackernews.models;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a one news data.
 */
public class NewsModel {

    private final String title;
    private final int points;
    private final String user;
    @SerializedName("time_ago")
    private final String timeAgo;
    @SerializedName("comments_count")
    private final int commentsCount;
    private final String url;
    private final String domain;

    public NewsModel(String title, int points, String user, String timeAgo, int commentsCount, String url, String domain) {
        this.title = title;
        this.points = points;
        this.user = user;
        this.timeAgo = timeAgo;
        this.commentsCount = commentsCount;
        this.url = url;
        this.domain = domain;
    }

    public String getTitle() {
        return title;
    }

    public int getPoints() {
        return points;
    }

    public String getUser() {
        return user;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public String getUrl() {
        return url;
    }

    public String getDomain() {
        return domain;
    }
}
