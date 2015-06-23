package by.epam.elective.dao;

import by.epam.elective.entity.User;
import by.epam.elective.exception.TechnicalException;
import by.epam.elective.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserDAO extends AbstractUserDAO {
    public static final String ADD_NEW_USER = "INSERT INTO users (user_name, user_surname, login, password, role) VALUES (?,?,?,?,?)";
    public static final String CHECK_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users where login=? and password=?";
    public static final String SELECT_USERS_BY_ROLE = "SELECT * FROM users WHERE role=?";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE user_id=?";
    private static ResourceBundle errorMessage = ResourceBundle.getBundle("resources.errorMessage");

    @Override
    public boolean addUser(User user) throws TechnicalException {
        boolean isDone = false;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(ADD_NEW_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getRole());
            preparedStatement.executeUpdate();
            isDone = true;
        } catch (SQLException e) {
            throw new TechnicalException(errorMessage.getString("error.add.new.user"), e);
        } finally {
            close(preparedStatement);
            put(connection, connectionPool);
        }
        return isDone;
    }

    @Override
    public User checkUser(String login, String password) throws TechnicalException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(CHECK_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = initUser(resultSet);
            }
        } catch (SQLException e) {
            throw new TechnicalException(errorMessage.getString("error.check.existing.user"), e);
        } finally {
            close(preparedStatement);
            put(connection, connectionPool);
        }
        return user;
    }

    @Override
    public ArrayList<User> findUsersByRole(int role) throws TechnicalException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USERS_BY_ROLE);
            preparedStatement.setInt(1, role);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(initUser(resultSet));
            }
        } catch (SQLException e) {
            throw new TechnicalException(errorMessage.getString("error.get.users.by.role"), e);
        } finally {
            close(preparedStatement);
            put(connection, connectionPool);
        }
        return users;
    }

    @Override
    public User findUserById(int id) throws TechnicalException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = initUser(resultSet);
            }
        } catch (SQLException e) {
            throw new TechnicalException(errorMessage.getString("error.get.users.by.id"), e);
        } finally {
            close(preparedStatement);
            put(connection, connectionPool);
        }
        return user;
    }

    private User initUser(ResultSet resultSet) throws TechnicalException {
        User user = new User();
        try {
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setLogin(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            user.setRole(resultSet.getInt(6));
        } catch (SQLException e) {
            throw new TechnicalException(errorMessage.getString("error.user.initialization"), e);
        }
        return user;
    }
}
