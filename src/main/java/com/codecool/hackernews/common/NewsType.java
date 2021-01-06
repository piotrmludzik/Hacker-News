package com.codecool.hackernews.common;

/**
 * The static class is responsible for determining the type of news.
 */
public class NewsType {

    /**
     *  Returns the news type based on the path of the URL.
     */
    //TODO: check if it can be written better.
    public static String getType(String path) {
        switch (path) {
            case "/top" :
                return NewsConst.TOP.getType();
            case "/newest" :
                return NewsConst.NEWEST.getType();
            case "/jobs" :
                return NewsConst.JOBS.getType();
            default :
                return null;
        }
    }

    /* Prevents from creating an instance. */
    private NewsType() {
        throw new AssertionError("The DataType class cannot be an object.");
    }
}
