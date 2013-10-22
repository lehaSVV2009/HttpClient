package com.kadet.HandBook.service.impl;

import com.kadet.HandBook.dao.ChapterDao;
import com.kadet.HandBook.dao.impl.ChapterDaoImpl;
import com.kadet.HandBook.entity.Chapter;
import com.kadet.HandBook.service.ChapterService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 22.10.13
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public class ChapterServiceImpl implements ChapterService {

    private ChapterDao chapterDao = new ChapterDaoImpl();

    @Override
    public List<Chapter> findAll() {
        return chapterDao.findAll();
    }

    @Override
    public Chapter findById(int chapterId) {
        return chapterDao.findById(chapterId);
    }

    @Override
    public int save(Chapter chapter) {
        return chapterDao.save(chapter);
    }

    @Override
    public int update(Chapter chapter) {
        return chapterDao.update(chapter);
    }

    @Override
    public int delete(Chapter chapter) {
        return chapterDao.delete(chapter);
    }
}
