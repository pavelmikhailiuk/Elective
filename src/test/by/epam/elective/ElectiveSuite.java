package test.by.epam.elective;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@org.junit.runners.Suite.SuiteClasses({ConnectionPoolTest.class, ResourcesTest.class, FormValidatorTest.class})
@RunWith(Suite.class)
public class ElectiveSuite {
}
