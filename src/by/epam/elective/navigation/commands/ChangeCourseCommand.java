package by.epam.elective.navigation.commands;

import by.epam.elective.entity.Archive;
import by.epam.elective.entity.Course;
import by.epam.elective.entity.User;
import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.ArchiveService;
import by.epam.elective.service.CourseService;
import by.epam.elective.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ChangeCourseCommand implements Command {
    public static final int TEACHER_ROLE = 2;
    public static final String COURSE_ID = "courseId";
    public static final String COURSE = "course";
    public static final String TEACHERS_LIST = "teachersList";
    public static final String OLD_TEACHER = "oldTeacher";

    @Override
    public String execute(HttpServletRequest request) {
        int courseId = Integer.parseInt(request.getParameter(COURSE_ID));
        CourseService courseService = new CourseService();
        Course course = courseService.findCourseById(courseId);
        UserService userService = new UserService();
        ArrayList<User> teachersList = userService.findUsersByRole(TEACHER_ROLE);
        ArchiveService archiveService = new ArchiveService();
        ArrayList<Archive> archives = archiveService.findArchiveByCourseId(courseId);
        User teacher = null;
        for (Archive archive : archives) {
            for (User user : teachersList) {
                if (archive.getUserId() == user.getId()) {
                    teacher = user;
                }
            }
        }
        teachersList.remove(teacher);
        HttpSession session = request.getSession();
        session.setAttribute(COURSE, course);
        session.setAttribute(TEACHERS_LIST, teachersList);
        session.setAttribute(OLD_TEACHER, teacher);
        String nextPage = ConfigurationManager.getProperty("path.page.change.course");
        return nextPage;
    }
}