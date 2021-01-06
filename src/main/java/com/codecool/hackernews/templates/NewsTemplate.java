package com.codecool.hackernews.templates;

import com.codecool.hackernews.common.Const;
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

        return generateHtmlBase(title, articles);
    }

    /* Gets the title of the site (displayed in the browser tab). */
    private String getTitle() {
        String pageName = " | ";
        switch (newsType) {
            case Const.NewsType.TOP :
                pageName += "Top news";
                break;
            case Const.NewsType.NEWEST :
                pageName += "Newest news";
                break;
            case Const.NewsType.JOBS :
                pageName += "Jobs news";
                break;
            default :
                pageName = "";
        }

        return Const.SITE_NAME + pageName;
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
                    "<header>" + oneNews.getTitle() + "</header>" +
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
                        "<span><a href=\"\\top\">Top news</a></span>" +
                        "<span><a href=\"\\newest\">Newest</a></span>" +
                        "<span><a href=\"\\jobs\">Jobs</a></span>" +
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
