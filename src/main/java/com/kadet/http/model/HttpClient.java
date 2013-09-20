package com.kadet.http.model;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 20.09.13
 * Time: 1:22
 * To change this template use File | Settings | File Templates.
 */
public class HttpClient {

    public HttpResponse execute (HttpRequest request) throws IOException {
        Socket socket = new Socket(request.getHost(), receivePort(request));
        sendRequest(request, socket.getOutputStream());
        return receiveResponse(socket.getInputStream());
    }

    public int receivePort (HttpRequest request) {
        int port = request.getPort();
        if (port == -1) {
            port = 80;
        }
        return port;
    }

    private void sendRequest (HttpRequest request, OutputStream outputStream) {
        String query = request.getQueryString();
        PrintWriter writer = new PrintWriter(outputStream);
        writer.print(query);
        writer.flush();
    }

    private HttpResponse receiveResponse (InputStream inputStream) throws IOException {
        HttpResponse response = new HttpResponse();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String statusLine = reader.readLine();
        response.setStatus(receiveStatusFromStatusLine(statusLine));
        response.setDescription(receiveDescriptionFromStatusLine(statusLine));
        response.setHeaders(receiveHeaders(reader));
        response.setBody(receiveBody(reader));
        reader.close();
        return response;
    }


    private int receiveStatusFromStatusLine(String statusLine) {
        try {
            int status = Integer.parseInt(statusLine.substring(9, 12));
            return status;
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    private String receiveDescriptionFromStatusLine(String statusLine) {
        return statusLine.substring(13);
    }

    private Map<String, String> receiveHeaders (BufferedReader reader) throws IOException {
        Map<String, String> headers = new HashMap<String, String>();
        String line;
        do {
            line = reader.readLine();
            if(line != null && line.length() != 0){
                String separator = ": ";
                String name = line.substring(0, line.indexOf(separator));
                String value = line.substring(line.indexOf(separator) + separator.length());
                headers.put(name, value);
            }
        } while (line != null && line.length() != 0);
        return headers;
    }

    private String receiveBody (BufferedReader reader) throws IOException {
        StringBuilder answer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            answer.append(line).append("\n");
        }
        return answer.toString();
    }

}