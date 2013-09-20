package com.kadet.http.model;

import com.kadet.http.util.HttpDefaults;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 20.09.13
 * Time: 17:56
 * To change this template use File | Settings | File Templates.
 */
public class HttpHead extends HttpRequest{

    private final String HEAD_METHOD = "HEAD";

    public HttpHead(URL url) {
        super(url);
    }

    @Override
    public void initMethod() {
        this.method = HEAD_METHOD;
    }

    @Override
    public String getQueryString() {
        StringBuilder query = new StringBuilder();
        query.append(HEAD_METHOD).append(" ").append(url.toExternalForm()).append(" ").append(HttpDefaults.HTTP).append("\r\n");
        query.append("HOST: ").append(getHost()).append("\r\n");
        query.append("Connection: ").append("keep-alive").append("\r\n");
        query.append("\r\n");
        return query.toString();
    }


}
