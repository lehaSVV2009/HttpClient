package com.kadet.HandBook.dao.impl;

import com.kadet.HandBook.dao.ChapterDao;
import com.kadet.HandBook.entity.Chapter;
import com.kadet.HandBook.util.MysqlManager;

import java.sql.*;
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


    private String createSaveQuery (String tableName, String []columnNames) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ")
                .append(tableName)
                .append("(")
                .append(columnNames[0]);
        for (int columnNum = 1; columnNum < columnNames.length; ++columnNum) {
            query.append(", ")
                    .append(columnNames[columnNum]);
        }
        query.append(") VALUES(?");
        for (int columnNum = 1; columnNum < columnNames.length; ++columnNum) {
            query.append(",?");
        }
        query.append(")");
        return query.toString();
    }

    @Override
    public int save(Chapter chapter) {
        int result = -1;
        String query = createSaveQuery(
                TABLE_NAME,
                COLUMN_NAMES
        );
        try {
            Connection connection = MysqlManager.getInstance().getConnection();
            PreparedStatement statement
                    = connection.prepareStatement(query);
            statement.setInt(1, chapter.getId());
            statement.setString(2, chapter.getTitle());
            statement.setString(3, chapter.getText());
            result = statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            result = -1;
            MysqlManager.log(e);
        }
        return result;
    }

    private String createUpdateQuery (String tableName, String []columnNames, String idColumnName) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(columnNames[0])
                .append(" = ? ");
        for (int columnNum = 1; columnNum < columnNames.length; ++columnNum) {
            query.append(", ")
                    .append(columnNames[columnNum])
                    .append(" = ? ");
        }
        query.append(" WHERE ")
                .append(idColumnName)
                .append(" = ?");
        return query.toString();
    }


    @Override
    public int update(Chapter chapter) {
        int result = -1;
        String query = createUpdateQuery(
                TABLE_NAME,
                COLUMN_NAMES,
                COLUMN_NAMES[0]
        );
        try {
            Connection connection = MysqlManager.getInstance().getConnection();
            PreparedStatement statement
                    = connection.prepareStatement(query);
            statement.setInt(1, chapter.getId());
            statement.setString(2, chapter.getTitle());
            statement.setString(3, chapter.getText());
            statement.setInt(4, chapter.getId());
            result = statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            result = -1;
            MysqlManager.log(e);
        }
        return result;
    }

    private String createDeleteQuery (String tableName, String idColumnName) {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ")
                .append(tableName)
                .append(" WHERE ")
                .append(idColumnName)
                .append(" = ? ");
        return query.toString();
    }

    public int delete (Chapter chapter) {
        int result = -1;
        String query = createDeleteQuery(TABLE_NAME, COLUMN_NAMES[0]);
        try {
            Connection connection = MysqlManager.getInstance().getConnection();
            PreparedStatement statement
                    = connection.prepareStatement(query);
            statement.setInt(1, chapter.getId());
            result = statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            result = -1;
            MysqlManager.log(e);
        }
        return result;
    }




}
