package com.codecool.hackernews.common;

public class Const {
    /** The site name. */
    public static final String SITE_NAME = "Hacker news";

    /** The news data type. The names represent queries to the external API. */
    public static class DataType {
        public static final String TOP = "news";

        private DataType() {}
    }

    /* Prevents from creating an instance. */
    private Const() {
        throw new AssertionError("The Const class cannot be an object.");
    }
}
