package by.epam.elective.navigation.commands;

import by.epam.elective.entity.Archive;
import by.epam.elective.entity.Course;
import by.epam.elective.entity.User;
import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.ArchiveService;
import by.epam.elective.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ListCoursesCommand implements Command {
    public static final String USER = "user";
    public static final String COURSES_LIST = "coursesList";
    public static final String COURSE_STATUS = "status";
    public static final String ATTRIBUTE_COURSE_STATUS = "courseStatus";
    public static final String MARKS_LIST = "marksList";

    @Override
    public String execute(HttpServletRequest request) {
        CourseService courseService = new CourseService();
        ArchiveService archiveService = new ArchiveService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        int status = Integer.parseInt(request.getParameter(COURSE_STATUS));
        ArrayList<Course> coursesList = courseService.findCoursesByStatus(status, user.getRole(), user.getId());
        ArrayList<Archive> archives = new ArrayList<>();
        if (coursesList != null && coursesList.size() != 0) {
            for (Course course : coursesList) {
                Archive archive = archiveService.findArchiveByCourseIdUserId(course.getId(), user.getId());
                archives.add(archive);
            }
        }
        session.setAttribute(MARKS_LIST, archives);
        session.setAttribute(COURSES_LIST, coursesList);
        session.setAttribute(ATTRIBUTE_COURSE_STATUS, status);
        return ConfigurationManager.getProperty("path.page.list.courses");
    }
}
