package test.by.epam.elective;


import by.epam.elective.validator.FormValidator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FormValidatorTest {
    private static FormValidator formValidator;

    @BeforeClass
    public static void initFormValidator() {
        formValidator = new FormValidator();
    }
    @Test
    public void validateTest(){
        Assert.assertTrue(formValidator.validate("username","Иван"));
        Assert.assertTrue(formValidator.validate("username","John"));
        Assert.assertTrue(formValidator.validate("surname","Иванов"));
        Assert.assertTrue(formValidator.validate("surname","Johnson"));
        Assert.assertFalse(formValidator.validate("username", "ИвYн"));
        Assert.assertFalse(formValidator.validate("username", "Joh0n"));
        Assert.assertFalse(formValidator.validate("surname", "ИВанов"));
        Assert.assertFalse(formValidator.validate("surname", "johnson"));
        Assert.assertFalse(formValidator.validate("login", "Иван"));
        Assert.assertTrue(formValidator.validate("login","JohN"));
        Assert.assertFalse(formValidator.validate("password", "Иванов"));
        Assert.assertTrue(formValidator.validate("password","John4son"));
        Assert.assertFalse(formValidator.validate("mark","11"));
        Assert.assertTrue(formValidator.validate("mark", "10"));
        Assert.assertTrue(formValidator.validate("startDate", "2014-01-30"));
        Assert.assertTrue(formValidator.validate("endDate", "2016-12-31"));
        Assert.assertFalse(formValidator.validate("startDate", "2115-01-22"));
        Assert.assertFalse(formValidator.validate("startDate","2015-13-22"));
        Assert.assertFalse(formValidator.validate("startDate","2015-00-22"));
        Assert.assertFalse(formValidator.validate("startDate","2015-01-00"));
        Assert.assertFalse(formValidator.validate("startDate","2015-01-32"));
    }

    @AfterClass
    public static void clearFormValidator(){
        formValidator=null;
    }
}
