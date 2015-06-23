package by.epam.elective.dao;

import by.epam.elective.exception.TechnicalException;
import by.epam.elective.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public abstract class AbstractDAO {
    private static ResourceBundle errorMessage = ResourceBundle.getBundle("resources.errorMessage");
    private final static Logger LOGGER =Logger.getLogger(AbstractDAO.class);

    public void put(Connection connection, ConnectionPool connectionPool) {
        connectionPool.putConnection(connection);
    }

    public void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error(errorMessage.getString("error.close.prepared.statement"), e);
            }
        }
    }
}
