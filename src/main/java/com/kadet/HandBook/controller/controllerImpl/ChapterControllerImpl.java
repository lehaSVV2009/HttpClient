package com.kadet.HandBook.controller.controllerImpl;

import com.kadet.HandBook.controller.ChapterController;
import com.kadet.HandBook.entity.Chapter;
import com.kadet.HandBook.service.ChapterService;
import com.kadet.HandBook.service.impl.ChapterServiceImpl;
import com.kadet.HandBook.util.DataStrings;
import com.kadet.HandBook.util.Messages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 22.10.13
 * Time: 12:32
 * To change this template use File | Settings | File Templates.
 */
public class ChapterControllerImpl implements ChapterController {

    private ChapterService chapterService = new ChapterServiceImpl();

    private Chapter createStandardChapter() {
        Chapter chapter = new Chapter();
        chapter.setTitle(Messages.STANDARD_TITLE);
        chapter.setText(Messages.STANDARD_TEXT);
        return chapter;
    }

    @Override
    public void openChapter(HttpServletRequest request, HttpServletResponse response) {
        try {

            request.setCharacterEncoding("UTF-8");
            Chapter chapter;
            try {

                String chapterIdString = request.getParameter("chapterId");
                if (chapterIdString == null) {
                    chapter = createStandardChapter();
                } else {
                    Integer chapterId = Integer.parseInt(chapterIdString);
                    chapter = chapterService.findById(chapterId);
                    if (chapter == null) {
                        chapter = createStandardChapter();
                    }
                }
            } catch (NumberFormatException nfe) {
                chapter = createStandardChapter();
            }
            request.setAttribute("title", chapter.getTitle());
            request.setAttribute("text", chapter.getText());
            request.setAttribute("chapters", chapterService.findAll());
            redirectToJsp(request, response, DataStrings.MAIN_JSP);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void redirectToJsp(HttpServletRequest request, HttpServletResponse response, String jspUrl) throws ServletException, IOException {
        request.getRequestDispatcher(jspUrl).forward(request, response);
    }
}
