package by.epam.elective.navigation.commands;

import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.CourseService;

import javax.servlet.http.HttpServletRequest;

public class AddCourseCommand implements Command {
    public static final String REQUEST_PARAMETER_COURSE_NAME = "courseName";
    public static final String TEACHER_ID = "teacherId";
    public static final int COURSE_OPEN_STATUS_ID = 0;

    @Override
    public String execute(HttpServletRequest request) {
        String courseName = request.getParameter(REQUEST_PARAMETER_COURSE_NAME);
        int teacherId = Integer.parseInt(request.getParameter(TEACHER_ID));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        CourseService courseService = new CourseService();
        boolean isDone = courseService.addCourse(courseName, COURSE_OPEN_STATUS_ID, startDate, endDate, teacherId);
        String nextPage = ConfigurationManager.getProperty("path.page.course.error");
        if (isDone) {
            nextPage = ConfigurationManager.getProperty("path.page.course.ok");
        }
        return nextPage;
    }
}