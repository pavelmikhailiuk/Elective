package by.epam.elective.validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidator {
    public static final String NAME_SURNAME_REGEX = "(^[A-Z][a-z]+)$|^([А-Я][а-я]+)$";
    public static final String LOGIN_REGEX = "[\\w]{3,8}";
    public static final String PASSWORD_REGEX = "[\\w]{3,8}";
    public static final String MARK_REGEX = "^([\\d])$|^(10)$";
    public static final String DATE_REGEX = "201[4-7]-(0[1-9]|1[012])-([0-2][1-9]|3[0-1])";

    public boolean validate(String parameter, String value) {
        String regex = null;
        switch (parameter) {
            case "username":
                regex = NAME_SURNAME_REGEX;
                break;
            case "surname":
                regex = NAME_SURNAME_REGEX;
                break;
            case "login":
                regex = LOGIN_REGEX;
                break;
            case "password":
                regex = PASSWORD_REGEX;
                break;
            case "mark":
                regex = MARK_REGEX;
                break;
            case "startDate":
                regex = DATE_REGEX;
                break;
            case "endDate":
                regex = DATE_REGEX;
                break;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        boolean isValidated = matcher.find();
        return isValidated;
    }
}
