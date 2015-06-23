package by.epam.elective.navigation.commands;

import by.epam.elective.entity.Archive;
import by.epam.elective.entity.User;
import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ListStudentsCommand implements Command {
    public static final String USER = "user";
    public static final String STUDENTS_LIST = "studentsList";
    public static final String COURSE_ID = "courseId";
    public static final String MARKS_LIST = "marksList";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User teacher = (User) session.getAttribute(USER);
        int courseId = Integer.parseInt(request.getParameter(COURSE_ID));
        UserService userService = new UserService();
        ArrayList<User> studentsList = userService.listStudents(courseId, teacher.getId());
        ArrayList<Archive> marksList = userService.listMarks(courseId, teacher.getId());
        session.setAttribute(STUDENTS_LIST, studentsList);
        session.setAttribute(COURSE_ID, courseId);
        session.setAttribute(MARKS_LIST, marksList);
        return ConfigurationManager.getProperty("path.page.list.students");
    }
}
