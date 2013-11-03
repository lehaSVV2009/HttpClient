package com.kadet.HandBook.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 03.11.13
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
public interface AuthorizationController {

    public void initial (HttpServletRequest request, HttpServletResponse response);
    public void callback (HttpServletRequest request, HttpServletResponse response);


}
