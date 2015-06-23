package test.by.epam.elective;


import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ResourceBundle;

public class ResourcesTest {
    private ResourceBundle testBundle;

    @After
    public void clearResources() {
        testBundle = null;
    }

    @Test
    public void configPropertiesTest() {
        testBundle = ResourceBundle.getBundle("resources.db");
        Assert.assertNotNull(testBundle);
    }

    @Test
    public void dbPropertiesTest() {
        testBundle = ResourceBundle.getBundle("resources.db");
        Assert.assertNotNull(testBundle);
    }

    @Test
    public void errorMessagesPropertiesTest() {
        testBundle = ResourceBundle.getBundle("resources.errorMessage");
        Assert.assertNotNull(testBundle);
    }

    @Test
    public void webResourcesPropertiesTest() {
        testBundle = ResourceBundle.getBundle("resources.WebResources");
        Assert.assertNotNull(testBundle);
    }
}
