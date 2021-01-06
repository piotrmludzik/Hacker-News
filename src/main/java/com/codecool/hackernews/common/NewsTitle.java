package com.codecool.hackernews.common;

public class NewsTitle {

    /**
     * Returns a proper news title.
     */
    public static String getNewsTitle(String newsType) {
        for (NewsConst oneNewsConst : NewsConst.values()) {
            if (oneNewsConst.getType().equals(newsType))
                return oneNewsConst.getTitle();
        }

        return null;
    }

    /* Prevents from creating an instance. */
    private NewsTitle() {
        throw new AssertionError("The NewsTitle class cannot be an object.");
    }
}
