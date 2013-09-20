package com.kadet.http.model;

import java.io.*;
import java.net.Socket;

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

    private void sendRequest (HttpRequest request, OutputStream outputStream) {
        String query = request.getQueryString();
        PrintWriter writer = new PrintWriter(outputStream);
        writer.print(query);
        writer.flush();
    }

    private HttpResponse receiveResponse (InputStream inputStream) throws IOException {
        HttpResponse response = new HttpResponse();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        String inputLine;
        StringBuilder answer = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            answer.append(inputLine).append("\n");
        }

        in.close();
        response.setBody(answer.toString());
        return response;
    }

    public int receivePort (HttpRequest request) {
        int port = request.getPort();
        if (port == -1) {
            port = 80;
        }
        return port;
    }

}