package by.epam.elective.navigation.commands;

import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class NewUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.add.user");
    }
}
