package com.codecool.hackernews.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Provides news from an external API (hnpwa).
 */
public class NewsService {

    private final String dataType;

    /**
     * Prepares an instance with the data type setting.
     */
    public NewsService(String dataType) {
        this.dataType = dataType;
    }

    /**
     * Sends request to the external API and returns the JSON response as a string.
     */
    public String getData() throws IOException {
        HttpURLConnection con = setupConnection();
        Reader streamReader = checkAndGetResponse(con);
        String content = readResponse(streamReader);
        con.disconnect();

        return content;
    }

    /* Creates and sets up connection to the external API. */
    private HttpURLConnection setupConnection() throws IOException {
        URL url = new URL("https://api.hnpwa.com/v0/" + dataType + "/" + 1 + ".json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);

        return con;
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
}
