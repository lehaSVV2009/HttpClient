package com.kadet.http.model;

import com.kadet.http.util.HttpDefaults;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 20.09.13
 * Time: 17:56
 * To change this template use File | Settings | File Templates.
 */
public class HttpPost extends HttpRequest {

    private Map<String, String> params;

    private final String POST_METHOD = "POST";

    public HttpPost(URL url, Map<String, String> params) {
        super(url);
        this.params = params;
    }

    public HttpPost(URL url) {
        this(url, new LinkedHashMap<String, String>());
    }

    public boolean addParameter(String name, String value) {
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

    private String getParametersString() {
        StringBuilder paramsString = new StringBuilder();
        try {
            for (String name : params.keySet()) {
                paramsString.append(URLEncoder.encode(name, "UTF-8")).append('=').append(URLEncoder.encode(((String)params.get(name)), "UTF-8")).append('&');
            }
            System.out.println(paramsString.toString().length());
            if (paramsString.length() > 1) {
                paramsString.delete(paramsString.length() - 1, paramsString.length());
            }
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        return paramsString.toString();
    }

    @Override
    public String getQueryString() {

        String paramsString = getParametersString();
        StringBuilder query = new StringBuilder();
        query.append(POST_METHOD).append(' ').append(url.toExternalForm()).append(' ').append(HttpDefaults.HTTP).append("\r\n");
        query.append("HOST: ").append(getHost()).append("\r\n");
        query.append("Connection: ").append("keep-alive").append("\r\n");
        query.append("Content-Length: ").append(paramsString.length()).append("\r\n");
        query.append("Cache-Control: ").append("max-age=0").append("\r\n");
        query.append("Accept: ").append("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8").append("\r\n");
        query.append("Origin: ").append(url.getHost()).append("\r\n");
        query.append("User-Agent: ").append("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36").append("\r\n");
        query.append("Referer: ").append(url.toExternalForm()).append("\r\n");
        query.append("Accept-Encoding: ").append("gzip,deflate,sdch").append("\r\n");
        query.append("Accept-Language:").append("ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4").append("\r\n");
        query.append("Cookie: ").append("sid=2623137414319723; __utma=168332643.1558675713.1379615216.1379699127.1379717250.3; __utmb=168332643.5.10.1379717250; __utmc=168332643; __utmz=168332643.1379615216.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)").append("\r\n");
        query.append("\r\n");
        query.append(paramsString);

        System.out.println("query:\n" + query.toString());
        return query.toString();
    }

    public Map<String, String> getParams() {
        return params;
    }
}
