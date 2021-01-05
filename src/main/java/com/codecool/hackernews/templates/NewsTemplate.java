package com.codecool.hackernews.templates;

import com.codecool.hackernews.common.Const;
import com.codecool.hackernews.models.NewsModel;

import java.util.List;

public class NewsTemplate {

    private final String pageTitle;
    private final List<NewsModel> news;

    /**
     * The constructor of news template.
     */
    public NewsTemplate(String pageTitle, List<NewsModel> news) {
        this.pageTitle = pageTitle;
        this.news = news;
    }

    /**
     * Generates and returns the page template.
     */
    public String getTemplate() {
        String title = getTitle();
        String articles = getArticles();

        return generateHtmlBase(title, articles);
    }

    /* Gets the title of the site (displayed in the browser tab). */
    private String getTitle() {
        String title = Const.SITE_NAME;
        if (noPageTitle())
            title = Const.SITE_NAME + " | " + pageTitle;

        return title;
    }

    /* Returns true if there is no page title, false otherwise. */
    private boolean noPageTitle() {
        return !pageTitle.equals("");
    }

    /* Returns a html code with all news articles. */
    private String getArticles() {
        StringBuilder articlesCode = new StringBuilder();
        for (NewsModel oneNews : news) {
            articlesCode.append(generateHtmlArticle(oneNews));
        }

        return articlesCode.toString();
    }

    /* Returns html code with news aritcle. */
    private String generateHtmlArticle(NewsModel oneNews) {
        return "<article>" +
                    "<header>" + oneNews.getTitle() + "</header>" +
                    "<section>" +
                        oneNews.getTimeAgo() + "<br>" +
                        oneNews.getUser() +
                    "</section>" +
                    "<footer>" +
                        "domain: <span>" + oneNews.getDomain() + "</span> | " +
                        "points: <span>" + oneNews.getPoints() + "</span> | " +
                        "ccomments: <span>" + oneNews.getCommentsCount() + "</span>" +
                    "</footer>" +
                "</article>";
    }

    /* Returns html template base as string. */
    private String generateHtmlBase(String title, String articles) {
        return "<!DOCTYPE HTML>" +
                "<html lang=\"en\">" +
                "<head>" +
                    "<meta charset=\"UTF-8\">" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href='/static/css/colors.css'>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href='/static/css/site.css'>" +
                    "<title>" + title + "</title>" +
                "</head>" +
                "<body id=\"main-container\">" +
                    "<header>" +
                        "<span><a href=\"\\\">" + Const.SITE_NAME + "</a></span>" +
                        "<span><a href=\"\\top-news\">Top news</a></span>" +
                        "<span><a href=\"\\newest\">Newest</a></span>" +
                        "<span><a href=\"\\jobs\">Jobs</a></span>" +
                    "</header>" +
                    "<main>" +
                        articles +
                    "</main>" +
                    "<footer>" +
                        "<span>" +
                            Const.SITE_NAME + " by Piotr Mludzik (link to: " +
                            "<a href=\"https://github.com/piotrmludzik\">GitHub</a>, " +
                            "<a href=\"https://www.linkedin.com/in/piotrmludzik\">LinkedIn</a>)" +
                        "<span>" +
                    "</footer>" +
                "</body>" +
                "</html>";
    }
}
