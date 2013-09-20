import com.kadet.http.model.*;
import com.kadet.http.view.MainFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 20.09.13
 * Time: 1:18
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main (String []args) {

/*
        JFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
*/


        testHead();
        testGet();
        testPost();
    }

    public static void testHead () {

        HttpClient client = new HttpClient();
        try {
            URL url = new URL("http://www.spaces.ru/");
            HttpRequest request = new HttpHead(url);
            HttpResponse response = client.execute(request);
            System.out.println("status: " + response.getStatus());
            System.out.println("desc: " + response.getDescription());
            System.out.println("headers: " + response.getHeaders());
            System.out.println("body: " + response.getBody());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testGet () {
        HttpClient client = new HttpClient();
        try {
            URL url = new URL("http://www.spaces.ru/");
            System.out.println(
                    new StringBuilder()
                            .append("\nhost = ").append(url.getHost())
                            .append("\nport = ").append(url.getPort())
                            .append("\nquery = ").append(url.getQuery())
                            .append("\nref = ").append(url.getRef())
                            .append("\nauthority = ").append(url.getAuthority())
                            .append("\ncontent = ").append(url.getContent())
                            .append("\n").append(url.toExternalForm())
            );

            HttpRequest request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            System.out.println("status: " + response.getStatus());
            System.out.println("desc: " + response.getDescription());
            System.out.println("headers: " + response.getHeaders());
            System.out.println("body: " + response.getBody());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void testPost () {
        HttpClient client = new HttpClient();
        try {
            URL url = new URL("http://www.spaces.ru/search/?sid=2623137414319723");
            Map<String, String> params = new LinkedHashMap<String, String>();
            params.put("sid", "");
            params.put("CK", "421060658161060");
            params.put("link_id", "1050390");
            params.put("from", "");
            params.put("q", "lopez");
            params.put("cfms", "Найти");
            HttpRequest request = new HttpPost(url, params);
            HttpResponse response = client.execute(request);
            System.out.println("status: " + response.getStatus());
            System.out.println("desc: " + response.getDescription());
            System.out.println("headers: " + response.getHeaders());
            System.out.println("body: " + response.getBody());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
