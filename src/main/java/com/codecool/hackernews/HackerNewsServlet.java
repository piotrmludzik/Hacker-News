package com.codecool.hackernews;

import com.codecool.hackernews.common.Const;
import com.codecool.hackernews.common.DataType;
import com.codecool.hackernews.dao.NewsDao;
import com.codecool.hackernews.models.NewsModel;
import com.codecool.hackernews.templates.NewsTemplate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Supports news pages.
 */
@WebServlet(name = "hackerNewsServlet", urlPatterns = {"", "/top", "/newest"}, loadOnStartup = 1)
public class HackerNewsServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String dataType = getDataType(request.getServletPath());
        String pageNumber = request.getParameter("page");

        List<NewsModel> news = new NewsDao(dataType, pageNumber).getNews();
        String pageTemplate = new NewsTemplate("", pageNumber, news).getTemplate();

        PrintWriter out = response.getWriter();
        out.println(pageTemplate);
    }

    /* Returns the news type based on the servlet path. */
    private String getDataType(String servletPath) {
        String dataType = DataType.getType(servletPath);
        if (dataType == null)  // default page
            dataType = Const.DataType.TOP;

        return dataType;
    }
}
