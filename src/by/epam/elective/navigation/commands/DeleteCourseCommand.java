package by.epam.elective.navigation.commands;

import by.epam.elective.entity.Archive;
import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.ArchiveService;
import by.epam.elective.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class DeleteCourseCommand implements Command {
    public static final String MARKS_LIST = "marksList";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<Archive> archives = (ArrayList<Archive>) session.getAttribute(MARKS_LIST);
        Archive archive = archives.get(0);
        ArchiveService archiveService = new ArchiveService();
        archiveService.removeArchive(archive.getUserId(), archive.getCourseId());
        CourseService courseService = new CourseService();
        courseService.deleteCourse(archive.getCourseId());
        return ConfigurationManager.getProperty("path.page.user");
    }
}
