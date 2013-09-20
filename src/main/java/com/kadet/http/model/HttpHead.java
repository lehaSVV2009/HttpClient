package com.kadet.http.model;

import java.io.InputStream;
import java.io.OutputStream;
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
        return "";
    }


}
