package com.kadet.HandBook.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 03.11.13
 * Time: 16:14
 * To change this template use File | Settings | File Templates.
 */
public class UrlConverter {


    public static String readStringFromUrl(String url) throws IOException {
        InputStream accessTokenIS = new URL(url).openStream();
        String str
                = new Scanner(accessTokenIS).useDelimiter("\\A").next().toString();
        return str;
    }

}