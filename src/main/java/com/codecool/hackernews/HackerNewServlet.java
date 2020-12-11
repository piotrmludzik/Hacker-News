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
                        "<link rel=\"stylesheet\" type=\"text/css\" href='/static/css/site.css'>" +
                        "<title>" + title + "</title>" +
                    "</head>" +
                    "<body>" +
                        "<header>" +
                            "<span>" + title + "</span>" +
                            "<span>Top news</span>" +
                            "<span>Newest</span>" +
                            "<span>Jobs</span>" +
                        "</header>" +
                        "<main>" +
                            "BODY" +
                        "</main>" +
                        "<footer>" +
                            "<span>" + title + " by Piotr Mludzik (link to <a href=\"https://github.com/piotrmludzik\">GitHub</a>)<span>" +
                        "</footer>" +
                    "</body>" +
                "</html>"
        );
    }
}
