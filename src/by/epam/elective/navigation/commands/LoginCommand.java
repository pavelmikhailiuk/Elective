package by.epam.elective.navigation.commands;

import by.epam.elective.entity.User;
import by.epam.elective.exception.LogicalException;
import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String USER = "user";

    @Override
    public String execute(HttpServletRequest request) throws LogicalException {
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        User user = userService.checkUser(request.getParameter(LOGIN), request.getParameter(PASSWORD));
        String newPage = ConfigurationManager.getProperty("path.page.user.error");
        if (user != null) {
            session.setAttribute(USER, user);
            newPage = ConfigurationManager.getProperty("path.page.user");
        }
        return newPage;
    }
}
