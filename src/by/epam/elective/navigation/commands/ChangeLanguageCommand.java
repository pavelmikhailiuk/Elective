package by.epam.elective.navigation.commands;

import by.epam.elective.navigation.Command;
import by.epam.elective.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ChangeLanguageCommand implements Command {
    public static final String EN = "en";
    public static final String RU = "ru";
    public static final String LANGUAGE = "language";

    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter(LANGUAGE);
        switch (language) {
            case "EN":
                request.getSession().setAttribute(LANGUAGE, EN);
                break;
            case "RU":
                request.getSession().setAttribute(LANGUAGE, RU);
                break;
        }
        return ConfigurationManager.getProperty("path.page.login");
    }
}
