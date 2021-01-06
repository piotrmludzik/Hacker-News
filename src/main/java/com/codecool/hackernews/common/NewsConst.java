package com.codecool.hackernews.common;

/**
 * Contains news constants.
 */
public enum NewsConst {
    TOP("top", "Top news"),
    NEWEST("newest", "Newest news"),
    JOBS("jobs", "Jobs news");

    private final String type;
    private final String title;

    NewsConst(String newsType, String newsTitle) {
        this.type = newsType;
        this.title = newsTitle;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }
}
