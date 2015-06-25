package by.epam.elective.navigation.commands;

import by.epam.elective.entity.User;
import by.epam.elective.exception.LogicalException;
import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddUserCommand implements Command {
    public static final int TEACHER_ROLE = 2;
    public static final int STUDENT_ROLE = 3;
    public static final String USER = "user";
    public static final String USERNAME = "username";
    public static final String SURNAME = "surname";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) throws LogicalException {
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        User user = userService.checkUser(login, password);
        String nextPage = ConfigurationManager.getProperty("path.page.registration.error");
        if (user == null) {
            boolean isDone = false;
            User admin = (User) session.getAttribute(USER);
            if (admin == null) {
                isDone = userService.addUser(request.getParameter(USERNAME), request.getParameter(SURNAME), login, password, STUDENT_ROLE);
                if (isDone) {
                    user = userService.checkUser(login, password);
                    session.setAttribute(USER, user);
                    nextPage = ConfigurationManager.getProperty("path.page.registration.ok");
                }
            } else {
                isDone = userService.addUser(request.getParameter(USERNAME), request.getParameter(SURNAME), login, password, TEACHER_ROLE);
                if (isDone) {
                    nextPage = ConfigurationManager.getProperty("path.page.registration.ok");
                }
            }
        } else {
            nextPage = ConfigurationManager.getProperty("path.page.user.error");
        }
        return nextPage;
    }
}

