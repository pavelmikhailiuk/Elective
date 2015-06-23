package by.epam.elective.navigation.commands;

import by.epam.elective.entity.User;
import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class NewCourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        UserService userService=new UserService();
        HttpSession session=request.getSession();
        int teacherId=Integer.parseInt(request.getParameter("teacherId"));
        User teacher =userService.findUserById(teacherId);
        session.setAttribute("teacher",teacher);
        return ConfigurationManager.getProperty("path.page.add.course");
    }
}
