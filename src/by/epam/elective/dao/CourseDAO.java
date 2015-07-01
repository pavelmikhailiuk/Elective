package by.epam.elective.dao;

import by.epam.elective.entity.Course;
import by.epam.elective.exception.TechnicalException;
import by.epam.elective.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseDAO extends AbstractCourseDAO {
    public static final String ADD_NEW_COURSE = "INSERT INTO courses (course_name, status_id, start_date, end_date) VALUES (?,?,?,?)";
    public static final String SELECT_COURSES_BY_STATUS_USER_ID = "SELECT DISTINCT courses.course_id, courses.course_name, courses.status_id, " +
            "courses.start_date, courses.end_date FROM archives, courses WHERE courses.course_id=archives.course_id AND archives.user_id=? AND courses.status_id=?";
    public static final String SELECT_COURSES_BY_STATUS_NOT_USER_ID = "SELECT DISTINCT courses.course_id, courses.course_name, courses.status_id, courses.start_date," +
            " courses.end_date FROM courses, archives  WHERE courses.course_id=archives.course_id AND courses.status_id=? AND archives.course_id NOT IN (SELECT course_id FROM archives WHERE user_id=?)";
    public static final String SELECT_COURSES_BY_STATUS = "SELECT * FROM courses WHERE status_id=?";
    public static final String UPDATE_COURSE_BY_COURSE_ID = "UPDATE courses SET course_name=?, status_id=?, start_date=?, end_date=? WHERE course_id=?";
    private static final String SELECT_COURSES_BY_COURSE_ID = "SELECT * FROM courses WHERE course_id=?";
    private static final String DELETE_COURSE_BY_COURSE_ID = "DELETE FROM courses WHERE course_id=?;";

    @Override
    public boolean addCourse(Course course) throws TechnicalException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        boolean isDone = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(ADD_NEW_COURSE);
            preparedStatement.setString(1, course.getName());
            preparedStatement.setInt(2, course.getStatusId());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            preparedStatement.setString(3, simpleDateFormat.format(course.getStartDate()));
            preparedStatement.setString(4, simpleDateFormat.format(course.getEndDate()));
            preparedStatement.executeUpdate();
            isDone = true;
        } catch (SQLException e) {
            throw new TechnicalException("Error add new course", e);
        } finally {
            close(preparedStatement);
            connectionPool.putConnection(connection);
        }
        return isDone;
    }

    @Override
    public ArrayList<Course> findCoursesByStatusAndUserId(int status, int userId, boolean joined) throws TechnicalException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Course> courses = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            if (joined) {
                preparedStatement = connection.prepareStatement(SELECT_COURSES_BY_STATUS_USER_ID);
                preparedStatement.setInt(2, status);
                preparedStatement.setInt(1, userId);
            } else {
                preparedStatement = connection.prepareStatement(SELECT_COURSES_BY_STATUS_NOT_USER_ID);
                preparedStatement.setInt(1, status);
                preparedStatement.setInt(2, userId);
            }

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(initCourse(resultSet));
            }
        } catch (SQLException e) {
            throw new TechnicalException("Error get course by status user id", e);
        } finally {
            close(preparedStatement);
            connectionPool.putConnection(connection);
        }
        return courses;
    }

    @Override
    public ArrayList<Course> findCoursesByStatus(int status) throws TechnicalException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Course> courses = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_COURSES_BY_STATUS);
            preparedStatement.setInt(1, status);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(initCourse(resultSet));
            }
        } catch (SQLException e) {
            throw new TechnicalException("Error get course by status", e);
        } finally {
            close(preparedStatement);
            connectionPool.putConnection(connection);
        }
        return courses;
    }

    @Override
    public Course findCourseById(int courseId) throws TechnicalException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Course course = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_COURSES_BY_COURSE_ID);
            preparedStatement.setInt(1, courseId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                course = initCourse(resultSet);
            }
        } catch (SQLException e) {
            throw new TechnicalException("Error get course by id", e);
        } finally {
            close(preparedStatement);
            connectionPool.putConnection(connection);
        }
        return course;
    }

    @Override
    public boolean changeCourse(Course course) throws TechnicalException {
        boolean isDone = false;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COURSE_BY_COURSE_ID);
            preparedStatement.setInt(5, course.getId());
            preparedStatement.setInt(2, course.getStatusId());
            preparedStatement.setString(1, course.getName());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            preparedStatement.setString(3, simpleDateFormat.format(course.getStartDate()));
            preparedStatement.setString(4, simpleDateFormat.format(course.getEndDate()));
            preparedStatement.executeUpdate();
            isDone = true;
        } catch (SQLException e) {
            throw new TechnicalException("Error change course", e);
        } finally {
            close(preparedStatement);
            connectionPool.putConnection(connection);
        }
        return isDone;
    }

    @Override
    public boolean deleteCourse(int courseId) throws TechnicalException {
        boolean isDone = false;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_COURSE_BY_COURSE_ID);
            preparedStatement.setInt(1, courseId);
            preparedStatement.executeUpdate();
            isDone = true;
        } catch (SQLException e) {
            throw new TechnicalException("Error delete from courses", e);
        } finally {
            close(preparedStatement);
            connectionPool.putConnection(connection);
        }
        return isDone;
    }

    private Course initCourse(ResultSet resultSet) throws TechnicalException {
        Course course = new Course();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            course.setId(resultSet.getInt(1));
            course.setName(resultSet.getString(2));
            course.setStatusId(resultSet.getInt(3));
            course.setStartDate(simpleDateFormat.parse(resultSet.getString(4)));
            course.setEndDate(simpleDateFormat.parse(resultSet.getString(5)));
        } catch (SQLException | ParseException e) {
            throw new TechnicalException("Error course initialization", e);
        }
        return course;
    }
}
