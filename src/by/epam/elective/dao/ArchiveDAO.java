package by.epam.elective.dao;

import by.epam.elective.entity.Archive;
import by.epam.elective.exception.TechnicalException;
import by.epam.elective.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArchiveDAO extends AbstractArchiveDAO {
    public static final String SELECT_FROM_ARCHIVES_BY_USER_ID = "SELECT * FROM archives WHERE user_id=?";
    public static final String SELECT_FROM_ARCHIVES_BY_COURSE_ID = "SELECT * FROM archives WHERE course_id=?";
    public static final String INSERT_INTO_ARCHIVES_USER_ID_COURSE_ID = "INSERT INTO archives (user_id, course_id) VALUES (?,?)";
    public static final String SET_MARK_BY_USER_ID_AND_COURSE_ID = "UPDATE archives SET mark=? WHERE (user_id=? AND course_id=?)";
    public static final String REMOVE_FROM_ARCHIVES_BY_USER_ID = "DELETE FROM archives WHERE (user_id=? AND course_id=?)";

    @Override
    public ArrayList<Archive> findArchiveByUserId(int userId) throws TechnicalException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Archive> archive = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_FROM_ARCHIVES_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                archive.add(initArchive(resultSet));
            }
        } catch (SQLException e) {
            throw new TechnicalException("Error get archive by user id", e);
        } finally {
            close(preparedStatement);
            connectionPool.putConnection(connection);
        }
        return archive;
    }

    @Override
    public ArrayList<Archive> findArchiveByCourseId(int courseId) throws TechnicalException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Archive> archive = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_FROM_ARCHIVES_BY_COURSE_ID);
            preparedStatement.setInt(1, courseId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                archive.add(initArchive(resultSet));
            }
        } catch (SQLException e) {
            throw new TechnicalException("Error get archive by course id", e);
        } finally {
            close(preparedStatement);
            connectionPool.putConnection(connection);
        }
        return archive;
    }

    @Override
    public boolean addArchive(int userId, int courseId) throws TechnicalException {
        boolean isDone = false;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_INTO_ARCHIVES_USER_ID_COURSE_ID);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.executeUpdate();
            isDone = true;
        } catch (SQLException e) {
            throw new TechnicalException("Error archive insert", e);
        } finally {
            close(preparedStatement);
            connectionPool.putConnection(connection);
        }
        return isDone;
    }

    @Override
    public boolean removeArchive(int userId, int courseId) throws TechnicalException {
        boolean isDone = false;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(REMOVE_FROM_ARCHIVES_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.executeUpdate();
            isDone = true;
        } catch (SQLException e) {
            throw new TechnicalException("Error remove from archive", e);
        } finally {
            close(preparedStatement);
            connectionPool.putConnection(connection);
        }
        return isDone;
    }

    @Override
    public boolean setMarkByUserId(int mark, int userId, int courseId) throws TechnicalException {
        boolean isDone = false;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SET_MARK_BY_USER_ID_AND_COURSE_ID);
            preparedStatement.setInt(1, mark);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, courseId);
            preparedStatement.executeUpdate();
            isDone = true;
        } catch (SQLException e) {
            throw new TechnicalException("Error update marks", e);
        } finally {
            close(preparedStatement);
            connectionPool.putConnection(connection);
        }
        return isDone;
    }

    private Archive initArchive(ResultSet resultSet) throws TechnicalException {
        Archive archive = new Archive();
        try {
            archive.setId(resultSet.getInt(1));
            archive.setUserId(resultSet.getInt(2));
            archive.setCourseId(resultSet.getInt(3));
            archive.setMark(resultSet.getInt(4));
        } catch (SQLException e) {
            throw new TechnicalException("Error archive initialization", e);
        }
        return archive;
    }
}
