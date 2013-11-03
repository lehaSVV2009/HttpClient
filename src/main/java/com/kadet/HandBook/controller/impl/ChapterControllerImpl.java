package com.kadet.HandBook.controller.impl;

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
import java.util.Random;

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

    @Override
    public void addChapter(HttpServletRequest request, HttpServletResponse response) {
        try {

            request.setCharacterEncoding("UTF-8");
            String title = request.getParameter("addChapter.title");
            String text = request.getParameter("addChapter.text");
            if (!badText(title)
                    && !badText(text)) {
                Chapter chapter = new Chapter(
                        new Random().nextInt(10000),
                        title,
                        text
                );
                int result = chapterService.save(chapter);
                if (result != -1) {
                    request.setAttribute("addSuccess", new Boolean(true));
                    redirectToJsp(request, response, DataStrings.ADD_CHAPTER_JSP);
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ServletException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private boolean badText(String text) {
        return text == null
                || "".equals(text.trim());
    }

    @Override
    public void removeChapter(HttpServletRequest request, HttpServletResponse response) {
        try {

            request.setCharacterEncoding("UTF-8");
            String idString = request.getParameter("removeChapter.id");
            if (idString == null) {
                request.setAttribute("chapters", chapterService.findAll());
                redirectToJsp(request, response, DataStrings.REMOVE_CHAPTER_JSP);
                return;
            }
            Integer id = Integer.parseInt(idString);
            Chapter chapter = new Chapter();
            chapter.setId(id);
            int result = chapterService.delete(chapter);
            if (result != -1) {
                request.setAttribute("removeSuccess", new Boolean(true));
                request.setAttribute("chapters", chapterService.findAll());
                redirectToJsp(request, response, DataStrings.REMOVE_CHAPTER_JSP);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ServletException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void editChapter(HttpServletRequest request, HttpServletResponse response) {
        try {

            request.setCharacterEncoding("UTF-8");
            String idString = request.getParameter("editChapter.id");
            if (idString == null) {
                request.setAttribute("chapters", chapterService.findAll());
                redirectToJsp(request, response, DataStrings.EDIT_CHAPTER_JSP);
                return;
            }
            String title = request.getParameter("editChapter.title");
            String text = request.getParameter("editChapter.text");
            Integer id = Integer.parseInt(idString);
            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setTitle(title);
            chapter.setText(text);
            int result = chapterService.update(chapter);
            if (result != -1) {
                request.setAttribute("editSuccess", new Boolean(true));
                request.setAttribute("chapters", chapterService.findAll());
                redirectToJsp(request, response, DataStrings.EDIT_CHAPTER_JSP);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ServletException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void goToEditChapter(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("chapters", chapterService.findAll());
            redirectToJsp(request, response, DataStrings.EDIT_CHAPTER_JSP);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ServletException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void goToRemoveChapter(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("chapters", chapterService.findAll());
            redirectToJsp(request, response, DataStrings.REMOVE_CHAPTER_JSP);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ServletException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    protected void redirectToJsp(HttpServletRequest request, HttpServletResponse response, String jspUrl) throws ServletException, IOException {
        request.getRequestDispatcher(jspUrl).forward(request, response);
    }

}