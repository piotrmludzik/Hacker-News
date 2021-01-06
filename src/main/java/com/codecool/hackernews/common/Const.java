package com.codecool.hackernews.common;

/**
 * Contains all constants for the program's internal constants.
 */
public class Const {
    /** The site name. */
    public static final String SITE_NAME = "Hacker news";

    /* Prevents from creating an instance. */
    private Const() {
        throw new AssertionError("The Const class cannot be an object.");
    }
}
