package by.epam.elective.navigation.commands;

import by.epam.elective.entity.Archive;
import by.epam.elective.entity.User;
import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.ArchiveService;
import by.epam.elective.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ListStudentsCommand implements Command {
    public static final String STUDENTS_LIST = "studentsList";
    public static final String COURSE_ID = "courseId";
    public static final String MARKS_LIST = "marksList";
    public static final String STUDENTS_LIST_SIZE = "studentsListSize";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int courseId = Integer.parseInt(request.getParameter(COURSE_ID));
        UserService userService = new UserService();
        ArchiveService archiveService = new ArchiveService();
        ArrayList<User> studentsList = userService.listStudents(courseId);
        ArrayList<Archive> marksList = archiveService.findArchiveByCourseId(courseId);
        session.setAttribute(STUDENTS_LIST, studentsList);
        session.setAttribute(COURSE_ID, courseId);
        session.setAttribute(MARKS_LIST, marksList);
        session.setAttribute(STUDENTS_LIST_SIZE, studentsList.size());
        return ConfigurationManager.getProperty("path.page.list.students");
    }
}
