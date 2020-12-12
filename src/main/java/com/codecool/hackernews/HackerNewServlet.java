package com.codecool.hackernews;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "hackerNewsServlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class HackerNewServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String title = "Hacker news";

        out.println(
                "<!DOCTYPE HTML>" +
                "<html lang=\"en\">" +
                    "<head>" +
                        "<meta charset=\"UTF-8\">" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href='/static/css/colors.css'>" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href='/static/css/site.css'>" +
                        "<title>" + title + "</title>" +
                    "</head>" +
                    "<body id=\"main-container\">" +
                        "<header>" +
                            "<span><a href=\"\\\">" + title + "</a></span>" +
                            "<span><a href=\"\\top-news\">Top news</a></span>" +
                            "<span><a href=\"\\newest\">Newest</a></span>" +
                            "<span><a href=\"\\jobs\">Jobs</a></span>" +
                        "</header>" +
                        "<main>" +
                            "<article>" +
                                "<header>" +
                                    "Title" +
                                "</header>" +
                                "<section>" +
                                    "TimeAgo" +
                                "</section>" +
                                "<footer>" +
                                    "Author" +
                                "</footer>" +
                            "</article>" +
                        "</main>" +
                        "<footer>" +
                            "<span>" +
                                title +
                                " by Piotr Mludzik (link to: " +
                                "<a href=\"https://github.com/piotrmludzik\">GitHub</a>, " +
                                "<a href=\"https://www.linkedin.com/in/piotrmludzik\">LinkedIn</a>)" +
                            "<span>" +
                        "</footer>" +
                    "</body>" +
                    "</html>"
        );
    }
}
