package by.epam.elective.dao;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;


public abstract class AbstractDAO {
    private final static Logger LOGGER = Logger.getLogger(AbstractDAO.class);

    public void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Error close prepared statement", e);
            }
        }
    }
}
