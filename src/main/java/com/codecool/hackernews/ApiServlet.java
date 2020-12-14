package com.codecool.hackernews;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Handles any API queries.
 */
@WebServlet(name = "ApiServlet", urlPatterns = {"/api/*"}, loadOnStartup = 0)
public class ApiServlet extends HttpServlet {

    /**
     * Handles all API queries sent to the server by GET method.
     *
     * @param request            received request
     * @param response           reply response
     * @throws ServletException  exception for unexpected serlet behavior
     * @throws IOException       IO unexpected error exception
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        makeResponse(response, getData(request.getPathInfo(), request.getParameter("page")));
    }

    /*
     * Checks the boundary conditions and returns data from external API.
     * If anything goes wrong, return the information or the error in JSON format.
     */
    private String getData(String pathInfo, String pageNumber) throws IOException {
        if (pathInfo == null)  // entered "{url address}/api"
            return "{\"information\": \"This is the correct way to get an answer. However, please make a better request!\"}";

        String dataType = getDataType(pathInfo);
        if (dataType == null)  // bad API request
            return "{\"error\": \"Bad API request.\"}";

        if (pageNumber == null)  // no page number
            pageNumber = "1";

        return  getDataFromExternalApi(dataType, pageNumber);
    }

    /* Returns the data type based on the path of the API request. */
    private String getDataType(String pathInfo) {
        switch (pathInfo) {
            case "/top" :
                return "news";
            default :
                return null;
        }
    }

    /* Sends reqest to the external API and returns the JSON response as a string. */
    private String getDataFromExternalApi(String dataType, String pageNumber) throws IOException {
        HttpURLConnection con = setupConnection(dataType, pageNumber);
        Reader streamReader = checkAndGetResponse(con);
        String content = readResponse(streamReader);
        con.disconnect();

        return content;
    }

    /* Creates and sets up connection to the external API. */
    private HttpURLConnection setupConnection(String dataType, String pageNumber) throws IOException {
        HttpURLConnection con = (HttpURLConnection) prepareUrl(dataType, pageNumber).openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);

        return con;
    }

    /* Creates URL address to the external API. */
    private URL prepareUrl(String dataType, String pageNumber) throws MalformedURLException {
        return new URL("https://api.hnpwa.com/v0/" + dataType + "/" + pageNumber + ".json");
    }

    /* Checks if the connection is correct and returns a response. */
    private Reader checkAndGetResponse(HttpURLConnection con) throws IOException {
        int status = con.getResponseCode();

        Reader streamReader;
        if (status > 299)  // response error
            streamReader = new InputStreamReader(con.getErrorStream());
        else  // response ok
            streamReader = new InputStreamReader(con.getInputStream());
        return streamReader;
    }

    /* Returns a string response. */
    private String readResponse(Reader streamReader) throws IOException {
        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null)
            content.append(inputLine);
        in.close();

        return content.toString();
    }

    /* Sets the response and returns the data as JSON. */
    private void makeResponse(HttpServletResponse response, String data) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println(data);
    }
}
