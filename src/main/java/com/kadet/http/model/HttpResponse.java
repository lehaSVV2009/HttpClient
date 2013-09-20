package com.kadet.http.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 20.09.13
 * Time: 1:23
 * To change this template use File | Settings | File Templates.
 */
public class HttpResponse {

    private int status;
    private String description;
    private Map<String, String> headers;
    private String body;
    private String fullResponse;

    public HttpResponse() {
        this.headers = new HashMap<String, String>();
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "status=" + status +
                ", description='" + description + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }

    public String getFullResponse() {
        return fullResponse;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setFullResponse(String fullResponse) {
        this.fullResponse = fullResponse;
    }
}
