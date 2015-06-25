package by.epam.elective.navigation.commands;

import by.epam.elective.entity.Course;
import by.epam.elective.entity.User;
import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ListCoursesCommand implements Command {
    public static final String USER = "user";
    public static final String COURSES_LIST = "coursesList";
    public static final String COURSE_STATUS = "status";

    @Override
    public String execute(HttpServletRequest request) {
        CourseService courseService = new CourseService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        int status = Integer.parseInt(request.getParameter(COURSE_STATUS));
        ArrayList<Course> coursesList = courseService.findCoursesByStatus(status, user.getRole(), user.getId());
        request.setAttribute(COURSES_LIST, coursesList);
        request.setAttribute("courseStatus", status);
        return ConfigurationManager.getProperty("path.page.list.courses");
    }
}
