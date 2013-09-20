import com.kadet.http.model.HttpClient;
import com.kadet.http.model.HttpGet;
import com.kadet.http.model.HttpRequest;
import com.kadet.http.model.HttpResponse;
import com.kadet.http.view.MainFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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


        HttpClient client = new HttpClient();
        try {
            URL url = new URL("http://www.spaces.ru/");
//            System.out.println(
//                    new StringBuilder()
//                    .append("\nhost = ").append(url.getHost())
//                    .append("\nport = ").append(url.getPort())
//                    .append("\nquery = ").append(url.getQuery())
//                    .append("\nref = ").append(url.getRef())
//                    .append("\nauthority = ").append(url.getAuthority())
//                    .append("\ncontent = ").append(url.getContent())
//                    .append("\n").append(url.toExternalForm())
//            );

            HttpRequest request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            System.out.println(response.getBody());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
