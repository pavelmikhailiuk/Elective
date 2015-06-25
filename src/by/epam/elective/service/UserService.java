package by.epam.elective.service;

import by.epam.elective.dao.AbstractUserDAO;
import by.epam.elective.dao.UserDAO;
import by.epam.elective.encoder.PasswordEncoder;
import by.epam.elective.entity.Archive;
import by.epam.elective.entity.User;
import by.epam.elective.exception.LogicalException;
import by.epam.elective.exception.TechnicalException;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.validator.FormValidator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;

public class UserService {
    private final static Logger LOGGER = Logger.getLogger(UserService.class);
    private AbstractUserDAO userDAO = new UserDAO();
    PasswordEncoder encoder = new PasswordEncoder();
    FormValidator validator = new FormValidator();
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String USERNAME = "username";
    public static final String SURNAME = "surname";

    public boolean addUser(String username, String surname, String login, String password, int role) throws LogicalException {
        boolean isDone = false;
        try {
            if (validator.validate(USERNAME, username) && validator.validate(SURNAME, surname)
                    && validator.validate(LOGIN, login) && validator.validate(PASSWORD, password)) {
                login = encoder.encode(login);
                password = encoder.encode(password);
                User user = initUser(username, surname, login, password, role);
                isDone = userDAO.addUser(user);
            } else {
                throw new LogicalException(ConfigurationManager.getProperty("validation.error"));
            }
        } catch (TechnicalException e) {
            LOGGER.error(e);
        }
        return isDone;
    }

    public User checkUser(String login, String password) throws LogicalException {
        User user = null;
        try {
            if (validator.validate(LOGIN, login) && (validator.validate(PASSWORD, password))) {
                user = userDAO.checkUser(encoder.encode(login), encoder.encode(password));
            } else {
                throw new LogicalException(ConfigurationManager.getProperty("validation.error"));
            }
        } catch (TechnicalException e) {
            LOGGER.error(e);
        }
        return user;
    }

    public ArrayList<User> findUsersByRole(int role) {
        ArrayList<User> users = null;
        try {
            users = userDAO.findUsersByRole(role);
        } catch (TechnicalException e) {
            LOGGER.error(e);
        }
        return users;
    }

    public User findUserById(int id) {
        User user = null;
        try {
            user = userDAO.findUserById(id);
        } catch (TechnicalException e) {
            LOGGER.error(e);
        }
        return user;
    }

    private User initUser(String username, String surname, String login, String password, int role) {
        User user = new User();
        user.setName(username);
        user.setSurname(surname);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }

    public ArrayList<User> listStudents(int courseId) {
        ArchiveService archiveService = new ArchiveService();
        ArrayList<Archive> archiveByCourseId = archiveService.findArchiveByCourseId(courseId);
        Iterator<Archive> archiveIterator = archiveByCourseId.iterator();
        ArrayList<User> studentsList = new ArrayList<>();
        while (archiveIterator.hasNext()) {
            Archive archive = archiveIterator.next();
            int archiveUserId = archive.getUserId();
            User student = findUserById(archiveUserId);
            studentsList.add(student);
        }
        return studentsList;
    }
}
