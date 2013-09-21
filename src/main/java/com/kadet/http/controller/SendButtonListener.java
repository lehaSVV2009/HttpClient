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
public class SendButtonListener implements ActionListener{

    private JTextArea loggerArea;
    private JEditorPane htmlArea;
    private JComboBox methodsComboBox;
    private JTextField postParameterTextField;

    public SendButtonListener(JComboBox methodsComboBox, MainPanel mainPanel) {
        this.methodsComboBox = methodsComboBox;
        this.loggerArea = mainPanel.getLoggerArea();
        this.htmlArea = mainPanel.getHtmlArea();
        this.postParameterTextField = mainPanel.getPostParameterTextField();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String method = (String)methodsComboBox.getSelectedItem();
        if ("GET".equals(method)) {
            testGet();
        } else if ("HEAD".equals(method)) {
            testHead();
        } else if ("POST".equals(method)) {
            testPost(postParameterTextField.getText());
        }
    }

    public void testGet () {
        HttpClient client = new HttpClient();
        try {
            URL url = new URL("http://www.spaces.ru/");
            HttpRequest request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            loggerArea.append("Request:\n");
            loggerArea.append(request.getQueryString());
            loggerArea.append("Response:");
            loggerArea.append("\nstatus: " + response.getStatus());
            loggerArea.append("\ndesc: " + response.getDescription());
            loggerArea.append("\nheaders:\n");
            Map<String, String> headers = response.getHeaders();
            for (String key : headers.keySet()) {
                loggerArea.append(key);
                loggerArea.append(": ");
                loggerArea.append(response.getHeaders().get(key) + "\n");
            }
            loggerArea.append("\n\n");
            htmlArea.setText(response.getBody());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void testPost (String param) {
        HttpClient client = new HttpClient();
        try {
            URL url = new URL("http://spaces.ru/search/?sid=2201168027516682");
            Map<String, String> params = new LinkedHashMap<String, String>();
            params.put("sid", "");
            params.put("CK","440498432078011");
            params.put("link_id", "1210763");
            params.put("from", "");
            params.put("q", param);
            params.put("cfms", "Найти");
            HttpRequest request = new HttpPost(url, params);
            HttpResponse response = client.execute(request);
            loggerArea.append("Request:\n");
            loggerArea.append(request.getQueryString());
            loggerArea.append("\n\nResponse:");
            loggerArea.append("\nstatus: " + response.getStatus());
            loggerArea.append("\ndesc: " + response.getDescription());
            loggerArea.append("\nheaders:\n");
            Map<String, String> headers = response.getHeaders();
            for (String key : headers.keySet()) {
                loggerArea.append(key);
                loggerArea.append(": ");
                loggerArea.append(response.getHeaders().get(key) + "\n");
            }
            loggerArea.append("\n\n");
            htmlArea.setText(response.getBody());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void testHead () {

        HttpClient client = new HttpClient();
        try {
            URL url = new URL("http://www.spaces.ru/");
            HttpRequest request = new HttpHead(url);
            HttpResponse response = client.execute(request);
            loggerArea.append("Request:\n");
            loggerArea.append(request.getQueryString());
            loggerArea.append("Response:");
            loggerArea.append("\nstatus: " + response.getStatus());
            loggerArea.append("\ndesc: " + response.getDescription());
            loggerArea.append("\nheaders:\n");
            Map<String, String> headers = response.getHeaders();
            for (String key : headers.keySet()) {
                loggerArea.append(key);
                loggerArea.append(": ");
                loggerArea.append(response.getHeaders().get(key) + "\n");
            }
            loggerArea.append("\n\n");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
