package com.kadet.HandBook.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 22.10.13
 * Time: 12:28
 * To change this template use File | Settings | File Templates.
 */
public interface ChapterController {

    public void openChapter (HttpServletRequest request, HttpServletResponse response);
    public void addChapter (HttpServletRequest request, HttpServletResponse response);
    public void removeChapter (HttpServletRequest request, HttpServletResponse response);
    public void editChapter (HttpServletRequest request, HttpServletResponse response);
    public void goToEditChapter (HttpServletRequest request, HttpServletResponse response);
    public void goToRemoveChapter (HttpServletRequest request, HttpServletResponse response);

}
