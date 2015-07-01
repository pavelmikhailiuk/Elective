package by.epam.elective.navigation;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request);
}
