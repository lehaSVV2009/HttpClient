package com.kadet.HandBook.servlet;

import com.kadet.HandBook.controller.AuthorizationController;
import com.kadet.HandBook.controller.impl.AuthorizationControllerImpl;
import com.kadet.HandBook.entity.User;
import com.kadet.HandBook.util.JsonManager;
import com.kadet.HandBook.util.UrlConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
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
 * Time: 16:09
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "oauthclient", urlPatterns = {"/facebook", "/callback"})
public class OAuthClientServlet extends HttpServlet {

    private AuthorizationController controller = new AuthorizationControllerImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("callback")) {
            controller.callback(request, response);
        } else {
            controller.initial(request, response);
        }
    }


}