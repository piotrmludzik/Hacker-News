package com.codecool.hackernews.common;

/**
 * The static class is responsible for determining the type of news.
 */
public class NewsType {

    /**
     *  Returns the news type based on the path of the URL.
     */
    public static String getType(String path) {
        switch (path) {
            case "/top" :
                return Const.DataType.TOP;
            case "/newest" :
                return Const.DataType.NEWEST;
            default :
                return null;
        }
    }

    /* Prevents from creating an instance. */
    private NewsType() {
        throw new AssertionError("The DataType class cannot be an object.");
    }
}
