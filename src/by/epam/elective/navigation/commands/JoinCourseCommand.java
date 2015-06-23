package by.epam.elective.navigation.commands;

import by.epam.elective.entity.User;
import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.ArchiveService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class JoinCourseCommand implements Command {
    public static final String USER = "user";
    public static final String COURSE_ID = "courseId";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        int userId = user.getId();
        int courseId = Integer.parseInt(request.getParameter(COURSE_ID));
        ArchiveService archiveService = new ArchiveService();
        archiveService.addArchive(userId, courseId);
        return ConfigurationManager.getProperty("path.page.user");
    }
}
