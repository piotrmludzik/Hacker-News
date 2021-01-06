package com.codecool.hackernews.common;

public class Const {
    /** The site name. */
    public static final String SITE_NAME = "Hacker news";

    /** The news type. The names represent queries to the external API. */
    public static class NewsType {
        public static final String TOP = "news";
        public static final String NEWEST = "newest";
        public static final String JOBS = "jobs";

        private NewsType() {}
    }

    /* Prevents from creating an instance. */
    private Const() {
        throw new AssertionError("The Const class cannot be an object.");
    }
}
