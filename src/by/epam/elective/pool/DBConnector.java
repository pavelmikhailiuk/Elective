package by.epam.elective.pool;

import by.epam.elective.exception.TechnicalException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

class DBConnector {
    private static ResourceBundle dbConfig = ResourceBundle.getBundle("resources.db");

    static Connection getConnection() throws TechnicalException {
        Connection connection = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(dbConfig.getString("url"), dbConfig.getString("user"), dbConfig.getString("password"));
        } catch (SQLException e) {
            throw new TechnicalException("Can't connect to database", e);
        }
        return connection;
    }
}
