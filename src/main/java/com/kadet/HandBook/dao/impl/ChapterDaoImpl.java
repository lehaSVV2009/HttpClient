package com.kadet.HandBook.dao.impl;

import com.kadet.HandBook.dao.ChapterDao;
import com.kadet.HandBook.entity.Chapter;
import com.kadet.HandBook.util.MysqlManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 22.10.13
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public class ChapterDaoImpl implements ChapterDao {

    private final static String TABLE_NAME = "Chapter";

    private final static String[] COLUMN_NAMES = {
            "id",
            "title",
            "text"
    };

    private String createSelectAllQuery (String tableName) {
        return new StringBuilder()
                .append("select * from ")
                .append(tableName)
                .toString();
    }

    private List<Chapter> getAllFromResultSet (ResultSet resultSet) throws SQLException {
        List<Chapter> chapters = new ArrayList<Chapter>();
        while (resultSet.next()) {
            Chapter chapter = new Chapter();
            chapter.setId(resultSet.getInt(COLUMN_NAMES[0]));
            chapter.setTitle(resultSet.getString(COLUMN_NAMES[1]));
            chapter.setText(resultSet.getString(COLUMN_NAMES[2]));
            chapters.add(chapter);
        }
        return chapters;
    }

    @Override
    public List<Chapter> findAll() {
        List<Chapter> result = new ArrayList<Chapter>();
        String query = createSelectAllQuery(TABLE_NAME);
        try {
            Connection connection = MysqlManager.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            result
                    = getAllFromResultSet(resultSet);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            result.clear();
            MysqlManager.log(e);
        }
        return result;
    }

    @Override
    public Chapter findById(int chapterId) {
        List<Chapter> chapters = findAll();
        for (Chapter chapter : chapters) {
            if (chapterId == chapter.getId()) {
                return chapter;
            }
        }
        return null;
    }

    @Override
    public int save(Chapter chapter) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int update(Chapter chapter) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int delete(Chapter chapter) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
