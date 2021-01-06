package com.codecool.hackernews.common;

/**
 * Handles the news data.
 */
public class NewsHandler {

    /**
     *  Returns the news type based on the path of the URL.
     */
    public static String getNewsType(String path) {
        for (NewsConst oneNewsConst : NewsConst.values()) {
            String oneNewsContType = oneNewsConst.getType();
            if (("/" + oneNewsContType).equals(path))
                return oneNewsContType;
        }

        return null;
    }

    /**
     * Returns a news title based on a type.
     */
    public static String getNewsTitle(String newsType) {
        for (NewsConst oneNewsConst : NewsConst.values()) {
            if (oneNewsConst.getType().equals(newsType))
                return oneNewsConst.getTitle();
        }

        return null;
    }

    /* Prevents from creating an instance. */
    private NewsHandler() {
        throw new AssertionError("The NewsHandler class cannot be an object.");
    }
}
