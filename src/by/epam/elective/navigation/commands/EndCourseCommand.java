package by.epam.elective.navigation.commands;

import by.epam.elective.entity.Course;
import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.CourseService;

import javax.servlet.http.HttpServletRequest;

public class EndCourseCommand implements Command {
    public static final String COURSE_ID = "courseId";
    public static final int COURSE_CLOSED_STATUS_ID = 1;

    @Override
    public String execute(HttpServletRequest request) {
        int courseId = Integer.parseInt(request.getParameter(COURSE_ID));
        CourseService courseService = new CourseService();
        Course course = courseService.findCourseById(courseId);
        course.setStatusId(COURSE_CLOSED_STATUS_ID);
        courseService.changeCourse(course);
        return ConfigurationManager.getProperty("path.page.user");
    }
}
