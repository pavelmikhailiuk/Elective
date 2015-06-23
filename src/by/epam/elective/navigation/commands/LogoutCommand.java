package by.epam.elective.navigation.commands;

import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return ConfigurationManager.getProperty("path.page.index");
    }
}
