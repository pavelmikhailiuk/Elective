package by.epam.elective.pool;

import by.epam.elective.exception.TechnicalException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {
    private static ResourceBundle dbConfig = ResourceBundle.getBundle("resources.db");
    private static ResourceBundle errorMessage = ResourceBundle.getBundle("resources.errorMessage");

    public static Connection getConnection() throws TechnicalException {
        Connection connection = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(dbConfig.getString("url"), dbConfig.getString("user"), dbConfig.getString("password"));
        } catch (SQLException e) {
            throw new TechnicalException(errorMessage.getString("cant.connect.to.database"), e);
        }
        return connection;
    }
}
