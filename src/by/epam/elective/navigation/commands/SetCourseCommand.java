package by.epam.elective.navigation.commands;

import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.ArchiveService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetCourseCommand implements Command {
    public static final int TEACHER_ROLE = 2;
    public static final String COURSE_ID = "courseId";
    public static final String COURSE = "course";
    public static final String TEACHERS_LIST = "teachersList";
    public static final String OLD_TEACHER = "oldTeacher";

    @Override
    public String execute(HttpServletRequest request) {
        int oldTeacherId = Integer.parseInt(request.getParameter("oldTeacherId"));
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        ArchiveService archiveService = new ArchiveService();
        boolean isDone = archiveService.removeArchive(oldTeacherId, courseId);
        String nextPage = ConfigurationManager.getProperty("path.page.course.error");
        HttpSession session = request.getSession();
        if (isDone) {
            isDone = archiveService.addArchive(teacherId, courseId);
        }
        if (isDone) {
            nextPage = ConfigurationManager.getProperty("path.page.course.ok");
        }
        session.setAttribute("courseChange", isDone);
        return nextPage;
    }
}