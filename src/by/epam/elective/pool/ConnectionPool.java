package by.epam.elective.pool;

import by.epam.elective.exception.TechnicalException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private final static Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    public static final int WAITING_TIMEOUT = 10;
    private static Lock lock = new ReentrantLock();
    private static ConnectionPool instance;
    private ArrayBlockingQueue<Connection> pool;
    private static ResourceBundle errorMessage = ResourceBundle.getBundle("resources.errorMessage");
    private static ResourceBundle dbConfig = ResourceBundle.getBundle("resources.db");
    private static AtomicBoolean isNull = new AtomicBoolean(true);
    private AtomicBoolean giveConnection = new AtomicBoolean(true);

    private ConnectionPool() {
        int poolSize = Integer.parseInt(dbConfig.getString("pool.size"));
        pool = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Connection connection = null;
            try {
                connection = DBConnector.getConnection();
            } catch (TechnicalException e) {
                LOGGER.error(e);
            }
            if (connection != null) {
                pool.offer(connection);
            }
        }
        if (pool.size() != poolSize) {
            throw new RuntimeException("Pool initialization error");
        }
    }

    public static ConnectionPool getInstance() {
        if (isNull.get()) {
            lock.lock();
            if (instance == null) {
                instance = new ConnectionPool();
                isNull.set(false);
            }
            lock.unlock();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        if (giveConnection.get()) {
            try {
                connection = pool.poll(WAITING_TIMEOUT, TimeUnit.SECONDS);
                if (connection == null) {
                    LOGGER.error(errorMessage.getString("cant.get.connection.to.database"));
                }
            } catch (InterruptedException e) {
                LOGGER.error(errorMessage.getString("cant.get.connection.to.database"), e);
            }
        }
        return connection;
    }

    public void putConnection(Connection connection) {
        try {
            pool.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error(errorMessage.getString("cant.put.connection.to.database"), e);
        }
    }

    public void cleanUp() {
        Iterator<Connection> connectionIterator = pool.iterator();
        giveConnection.set(false);
        while (connectionIterator.hasNext()) {
            Connection connection = connectionIterator.next();
            try {
                connection.close();
                connectionIterator.remove();
            } catch (SQLException e) {
                LOGGER.error(errorMessage.getString("pool.cleanup.error"), e);
            }
        }
    }
}
