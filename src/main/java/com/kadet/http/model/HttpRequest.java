package com.kadet.http.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 20.09.13
 * Time: 1:22
 * To change this template use File | Settings | File Templates.
 */
public abstract class HttpRequest {

    protected URL url;
    protected String method;

    public HttpRequest (URL url) {
        this.initMethod();
        this.url = url;
    }

    public String getHost () {
        return url.getHost();
    }

    public int getPort () {
        return url.getPort();
    }

    public abstract void initMethod ();

    public abstract String getQueryString ();
//    public abstract void sendData (OutputStream outputStream);

    @Override
    public String toString() {
        return "HttpRequest{" +
                "url=" + url +
                ", method='" + method + '\'' +
                '}';
    }

}