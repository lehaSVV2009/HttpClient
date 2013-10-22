package com.kadet.HandBook.servlet;

import com.kadet.HandBook.controller.ChapterController;
import com.kadet.HandBook.controller.controllerImpl.ChapterControllerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 22.10.13
 * Time: 12:29
 * To change this template use File | Settings | File Templates.
 */
public class OpenChapterServlet extends HttpServlet {

    private ChapterController chapterController = new ChapterControllerImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chapterController.openChapter(request, response);
    }
}
