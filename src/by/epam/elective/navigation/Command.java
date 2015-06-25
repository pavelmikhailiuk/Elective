package by.epam.elective.navigation;

import by.epam.elective.exception.LogicalException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request) throws LogicalException;
}
