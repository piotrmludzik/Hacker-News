package com.codecool.hackernews.common;

public class Const {
    /** The site name. */
    public static final String SITE_NAME = "Hacker news";

    /** The news data type */
    public static class DataType {
        public static final String NEWS = "news";

        private DataType() {}
    }

    /* Prevents from creating an instance. */
    private Const() {
        throw new AssertionError("The Const class cannot be an object.");
    }
}
