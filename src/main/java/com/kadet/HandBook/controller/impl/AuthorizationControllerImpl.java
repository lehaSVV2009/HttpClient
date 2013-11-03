package com.kadet.HandBook.controller.impl;

import com.kadet.HandBook.controller.AuthorizationController;
import com.kadet.HandBook.entity.User;
import com.kadet.HandBook.util.DataStrings;
import com.kadet.HandBook.util.JsonManager;
import com.kadet.HandBook.util.UrlConverter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 03.11.13
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
public class AuthorizationControllerImpl implements AuthorizationController {

    private final static int maxCookieAge = 100 * 60;

    private static final String AUTHORIZATION_URL = "https://www.facebook.com/dialog/oauth?" +
            "client_id=%s" +
            "&redirect_uri=%s" +
            "&state=%s" +
            "&scope=user_birthday";

    private static final String ACCESS_TOKEN_URL = "https://graph.facebook.com/oauth/access_token" +
            "?client_id=%s" +
            "&redirect_uri=%s" +
            "&client_secret=%s" +
            "&code=%s";

    private static final String CLIENT_ID = "402853429844292";
    private static final String CLIENT_SECRET = "1d0dda9b31c65111f7f086635ed84723";
    private static final String REDIRECT_URI = "http://localhost:8080/callback";


    public void initial(HttpServletRequest request, HttpServletResponse response) {

        try {
            String state = UUID.randomUUID().toString();
            request.getSession().setAttribute("nonce", state);
            String authorizationUrl = String.format(AUTHORIZATION_URL, CLIENT_ID, REDIRECT_URI, state);
            response.sendRedirect(authorizationUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void callback(HttpServletRequest request, HttpServletResponse response) {

        try {
            if (!request.getParameter("state").equals(request.getSession().getAttribute("nonce"))) {
                throw new IllegalStateException("CSRF!");
            }
            String code = request.getParameter("code");
            String url = String.format(ACCESS_TOKEN_URL, CLIENT_ID, REDIRECT_URI, CLIENT_SECRET, code);
            String facebookResponse = UrlConverter.readStringFromUrl(url);
            // Response looks like: "access_token=12398172398&expires=123123"
            User user = getUserByAccessToken(facebookResponse.split("[&=]")[1]);
            if (validateUser(user)) {
                addUserAsCookie(user, response, maxCookieAge);
                response.sendRedirect("/openChapter");
            } else {
                redirectToJsp(request, response, DataStrings.INDEX_JSP);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private User getUserByAccessToken(String accessToken) throws IOException {
        User user;
        String basicInfoUrl = String.format("https://graph.facebook.com/me?access_token=%s", accessToken);
        String basicInfo = UrlConverter.readStringFromUrl(basicInfoUrl);
        System.out.println(basicInfo);
        user = JsonManager.userFromUrl(basicInfoUrl);
        return user;
    }

    private boolean validateUser(User user) {
        return user != null && user.getName() != null && user.getSurname() != null;
    }

    private Cookie createCookie(String name, String value, int maxCookieAge) throws UnsupportedEncodingException {
        Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
        cookie.setMaxAge(maxCookieAge);
        return cookie;
    }

    private void addUserAsCookie (User user, HttpServletResponse response, int maxCookieAge) throws UnsupportedEncodingException {
        response.addCookie(
                createCookie("userName", user.getName(), maxCookieAge)
        );
        response.addCookie(
                createCookie("userSurname", user.getSurname(), maxCookieAge)
        );
    }

    protected void redirectToJsp(HttpServletRequest request, HttpServletResponse response, String jspUrl) throws ServletException, IOException {
        request.getRequestDispatcher(jspUrl).forward(request, response);
    }

}
