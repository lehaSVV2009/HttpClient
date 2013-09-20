package com.kadet.http.model;

import com.kadet.http.util.HttpDefaults;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 20.09.13
 * Time: 17:56
 * To change this template use File | Settings | File Templates.
 */
public class HttpGet extends HttpRequest {

    private final String GET_METHOD = "GET";

    public HttpGet(URL url) {
        super(url);
    }

    @Override
    public void initMethod() {
        this.method = GET_METHOD;
    }

    @Override
    public String getQueryString() {
        StringBuilder query = new StringBuilder();
        query.append(GET_METHOD).append(" ").append(url.toExternalForm()).append(" ").append(HttpDefaults.HTTP).append("\r\n");
        query.append("HOST: ").append(getHost()).append("\r\n");
        query.append("Connection: ").append("keep-alive").append("\r\n");
        query.append("Cache-Control: ").append("max-age=0").append("\r\n");
        query.append("User-Agent: ").append("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36").append("\r\n");
        query.append("Referer: ").append(url.toExternalForm()).append("\r\n");
        query.append("Accept-Encoding: ").append("gzip,deflate,sdch").append("\r\n");
        query.append("Accept-Language:").append("ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4").append("\r\n");
        query.append("Cookie: ").append("sid=0433465259137125; __utma=168332643.1558675713.1379615216.1379615216.1379615216.1; __utmb=168332643.3.10.1379615216; __utmc=168332643; __utmz=168332643.1379615216.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)").append("\r\n");
        query.append("\r\n");
        return query.toString();
    }

    /*@Override
    public void sendData(OutputStream outputStream) {
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println(GET_METHOD + " " + url.toExternalForm() + " ");
        writer.println("HOST " + getHost());
        writer.println("Connection: " + "keep-alive");
        writer.println("Cache-Control: " + "max-age=0");
        writer.println("User-Agent: " + "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36");
        writer.println("Referer: " + url.toExternalForm());
        writer.println("Accept-Encoding: " + "gzip,deflate,sdch");
        writer.println("Accept-Language:" + "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
        writer.println("Cookie: " + "sid=0433465259137125; __utma=168332643.1558675713.1379615216.1379615216.1379615216.1; __utmb=168332643.3.10.1379615216; __utmc=168332643; __utmz=168332643.1379615216.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)");
        writer.println();
        writer.flush();
    }    */


}
