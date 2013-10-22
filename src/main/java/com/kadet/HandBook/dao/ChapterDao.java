package com.kadet.HandBook.dao;

import com.kadet.HandBook.entity.Chapter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 22.10.13
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
public interface ChapterDao {

    public List<Chapter> findAll ();
    public Chapter findById (int chapterId);
    public int save (Chapter chapter);
    public int update (Chapter chapter);
    public int delete (Chapter chapter);

}
