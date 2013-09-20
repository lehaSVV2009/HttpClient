package com.kadet.http.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 20.09.13
 * Time: 17:56
 * To change this template use File | Settings | File Templates.
 */
public class HttpPost extends HttpRequest{

    private final String POST_METHOD = "POST";

    public HttpPost(URL url) {
        super(url);
    }

    public boolean addParameter (String name, String value) {
        try {
            name = URLEncoder.encode(name, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");
            params.put(name, value);
        } catch (UnsupportedEncodingException e) {
            return false;
        }
        return true;
    }


    @Override
    public void initMethod() {
        this.method = POST_METHOD;
    }

    @Override
    public String getQueryString() {
        return "";
    }

}
