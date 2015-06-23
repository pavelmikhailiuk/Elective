package by.epam.elective.navigation.commands;

import by.epam.elective.entity.User;
import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ListTeachersCommand implements Command {

    public static final int TEACHER_ROLE = 2;
    public static final String TEACHERS_LIST = "teachersList";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        ArrayList<User> teachersList = userService.findUsersByRole(TEACHER_ROLE);
        session.setAttribute(TEACHERS_LIST, teachersList);
        return ConfigurationManager.getProperty("path.page.list.teachers");
    }
}
