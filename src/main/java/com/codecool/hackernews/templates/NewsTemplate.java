package com.codecool.hackernews.templates;

import com.codecool.hackernews.common.Const;
import com.codecool.hackernews.common.NewsConst;
import com.codecool.hackernews.common.NewsTitle;
import com.codecool.hackernews.models.NewsModel;

import java.util.List;

public class NewsTemplate {

    private final List<NewsModel> news;
    private final String newsType;
    private final String pageNumber;

    /**
     * The constructor of news template.
     */
    public NewsTemplate(List<NewsModel> news, String newsType, String pageNumber) {
        this.news = news;
        this.newsType = newsType;
        this.pageNumber = (pageNumber == null ? "1" : pageNumber);
    }

    /**
     * Generates and returns the page template.
     */
    public String getTemplate() {
        String title = getTitle();
        String articles = getArticles();
        String navbar = getNavBar();

        return generateHtmlBase(title, navbar, articles);
    }

    /* Gets the title of the site (displayed in the browser tab). */
    private String getTitle() {
        String pageName = NewsTitle.getNewsTitle(newsType);
        if (pageName == null)
            return Const.SITE_NAME;

        return Const.SITE_NAME + " | " + pageName;
    }

    /* Returns a navigation bar html code. */
    private String getNavBar() {
        StringBuilder navbarCode = new StringBuilder();
        navbarCode.append("<span id=\"navbar_title\"><a href=\"\\\">" + Const.SITE_NAME + "</a></span>");
        for (NewsConst newsConst : NewsConst.values()) {
            String pageType = newsConst.getType();
            String pageTitle = newsConst.getTitle();

            navbarCode.append("<span");
            if (newsType.equals(pageType))
                navbarCode.append(" id=\"navbar_active\"");
            navbarCode.append("><a href=\"\\").append(pageType).append("\">").append(pageTitle).append("</a></span>");
        }

        return navbarCode.toString();
    }

    /* Returns a html code with all news articles. */
    private String getArticles() {
        StringBuilder articlesCode = new StringBuilder();
        for (NewsModel oneNews : news) {
            articlesCode.append(generateHtmlArticle(oneNews));
        }

        return articlesCode.toString();
    }

    /* Returns html code with news article. */
    private String generateHtmlArticle(NewsModel oneNews) {
        return "<article>" +
                    "<header>" +
                        "<a href=\"" + oneNews.getUrl() + "\" target=\"_blank\">" + oneNews.getTitle() + "</a>" +
                    "</header>" +
                    "<section>" +
                        oneNews.getTimeAgo() + "<br>" +
                        oneNews.getUser() +
                    "</section>" +
                    "<footer>" +
                        "domain: <span>" + oneNews.getDomain() + "</span> | " +
                        "points: <span>" + oneNews.getPoints() + "</span> | " +
                        "comments: <span>" + oneNews.getCommentsCount() + "</span>" +
                    "</footer>" +
                "</article>";
    }

    /* Returns html template base as string. */
    private String generateHtmlBase(String title, String navbar, String articles) {
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
                        navbar +
                    "</header>" +
                    "<main>" +
                        "<div id=\"page_pagination\">" +
                            "<span><a href=\"\\" + newsType + "?page=" + getPagePaginationNumber(-1) + "\"><<<</a></span>" +
                            "<span><a href=\"\\" + newsType + "?page=" + getPagePaginationNumber(1) + "\">>>></a></span>" +
                        "</div>" +
                        "<div id=\"articles\">" +
                            articles +
                        "</div>" +
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

    /*
     * Returns the page number for pagination. The direction determines
     * whether to subtract or add a number from the current side.
     */
    private String getPagePaginationNumber(int direction) {
        int pageNumberForPagination = Integer.parseInt(this.pageNumber) + direction;
        if (pageNumberForPagination <= 0)
            pageNumberForPagination = 1;

        return String.valueOf(pageNumberForPagination);
    }
}
