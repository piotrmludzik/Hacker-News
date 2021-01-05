package com.codecool.hackernews.dao;

import com.codecool.hackernews.models.NewsModel;
import com.codecool.hackernews.service.NewsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * The news data access object.
 */
public class NewsDao {

    private final String dataType;    // The news data type, stored as a type name.
    private final String pageNumber;  // The page number of collected news.

    /**
     * The constructor of news data access object.
     */
    public NewsDao(String dataType, String pageNumber) {
        this.dataType = dataType;
        this.pageNumber = pageNumber;
    }

    /**
     * Returns a list of news.
     */
    public List<NewsModel> getNews() throws IOException {
        String newsJson = getNewsFromSource();
        return convertJsonToObjects(newsJson);
    }

    /* Gets news from external API. */
    private String getNewsFromSource() throws IOException {
        return new NewsService(dataType, pageNumber).getData();
    }

    /* Converts news form JSON to objects */
    private List<NewsModel> convertJsonToObjects(String newsJson) {
        Gson gson = new Gson();
        Type newsListType = new TypeToken<ArrayList<NewsModel>>(){ }.getType();
        return gson.fromJson(newsJson, newsListType);
    }
}
