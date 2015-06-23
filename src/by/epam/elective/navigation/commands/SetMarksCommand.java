package by.epam.elective.navigation.commands;

import by.epam.elective.entity.Course;
import by.epam.elective.entity.User;
import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.ArchiveService;
import by.epam.elective.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class SetMarksCommand implements Command {
    public static final String COURSE_ID = "courseId";
    public static final String STUDENTS_LIST = "studentsList";
    public static final String USER = "user";
    public static final int COURSE_END_STATUS_ID = 2;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User teacher = (User) session.getAttribute(USER);
        ArrayList<User> studentsList = (ArrayList<User>) session.getAttribute(STUDENTS_LIST);
        int courseId = (Integer) session.getAttribute(COURSE_ID);
        ArchiveService archiveService = new ArchiveService();
        CourseService courseService = new CourseService();
        for (User student : studentsList) {
            int studentId = student.getId();
            int mark = Integer.parseInt(request.getParameter(String.valueOf(studentId)));
            archiveService.setMarkByUserId(mark, studentId, courseId);
        }
        Course course = courseService.findCourseById(courseId);
        course.setStatusId(COURSE_END_STATUS_ID);
        courseService.changeCourse(course);
        return ConfigurationManager.getProperty("path.page.user");
    }
}
