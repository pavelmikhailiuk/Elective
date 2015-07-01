package test.by.epam.elective;


import by.epam.elective.exception.TechnicalException;
import by.epam.elective.pool.ConnectionPool;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConnectionPoolTest {
    private static ConnectionPool connectionPool;

    @BeforeClass
    public static void initConnectionPool() {
        connectionPool = ConnectionPool.getInstance();
    }

    @Test
    public void getConnectionTest() throws TechnicalException {
        for (int i = 0; i < 20; i++) {
            Assert.assertNotNull(connectionPool.getConnection());
        }
    }

    @AfterClass
    public static void cleanUpConnectionPool() {
        connectionPool.cleanUp();
    }
}
