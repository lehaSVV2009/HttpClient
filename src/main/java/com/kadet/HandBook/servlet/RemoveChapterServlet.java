package com.kadet.HandBook.servlet;

import com.kadet.HandBook.controller.ChapterController;
import com.kadet.HandBook.controller.impl.ChapterControllerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 26.10.13
 * Time: 1:57
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(urlPatterns = "/removeChapter")
public class RemoveChapterServlet extends HttpServlet{

    private ChapterController controller = new ChapterControllerImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controller.removeChapter(req, resp);
    }
}
