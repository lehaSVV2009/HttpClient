package com.kadet.http.controller;

import com.kadet.http.model.*;
import com.kadet.http.view.MainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 21.09.13
 * Time: 13:30
 * To change this template use File | Settings | File Templates.
 */
public class SendButtonListener implements ActionListener {

    private JComboBox urlsComboBox;
    private JTextArea loggerArea;
    private JEditorPane htmlArea;
    private JComboBox methodsComboBox;
    private JTextField postParameterTextField;

    public SendButtonListener(JComboBox methodsComboBox, MainPanel mainPanel) {
        this.methodsComboBox = methodsComboBox;
        this.loggerArea = mainPanel.getLoggerArea();
        this.htmlArea = mainPanel.getHtmlArea();
        this.postParameterTextField = mainPanel.getPostParameterTextField();
        this.urlsComboBox = mainPanel.getUrlsComboBox();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String urlText = (String) urlsComboBox.getSelectedItem();
        String method = (String) methodsComboBox.getSelectedItem();
        sendRequest(urlText, method);
    }

    private void sendRequest (String urlText, String method) {
        HttpClient client = new HttpClient();
        try {
            URL url = new URL(urlText);
            HttpRequest request = makeRequest(method, url);
            HttpResponse response = client.execute(request);
            fillLoggerArea(request.getQueryString(), response.getStatus(), response.getDescription(), response.getHeaders(), response.getBody());
            fillHtmlArea(response.getBody());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HttpRequest makeRequest (String method, URL url) throws IOException {
        HttpRequest request;
        if ("GET".equals(method)) {
            request = new HttpGet(url);
        } else if ("HEAD".equals(method)) {
            request = new HttpHead(url);
        } else if ("POST".equals(method)) {
            Map<String, String> params = new LinkedHashMap<String, String>();
            params.put("sid", "");
            params.put("CK", "359086541741124");
            params.put("link_id", "1776446");
            params.put("from", "");
            params.put("q", postParameterTextField.getText());
            params.put("cfms", "Найти");
            request = new HttpPost(url, params);
        } else {
            throw new IOException("Make request error!");
        }
        return request;
    }

    private void fillLoggerArea(String requestText, int responseStatus, String responseDescription, Map<String, String> responseHeaders, String responseBody) {
        loggerArea.append("\n\nRequest:\n");
        loggerArea.append(requestText);
        loggerArea.append("\nResponse:");
        loggerArea.append("\nstatus: " + responseStatus);
        loggerArea.append("\ndesc: " + responseDescription);
        loggerArea.append("\nheaders:\n");
        for (String key : responseHeaders.keySet()) {
            loggerArea.append(key);
            loggerArea.append(": ");
            loggerArea.append(responseHeaders.get(key) + "\n");
        }
        loggerArea.append("\n\n");
    }

    private void fillHtmlArea(String htmlText) {
        htmlArea.setText(htmlText);
    }

}
