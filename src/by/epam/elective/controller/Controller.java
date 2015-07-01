package by.epam.elective.controller;

import by.epam.elective.exception.TechnicalException;
import by.epam.elective.navigation.Command;
import by.epam.elective.navigation.CommandFactory;
import by.epam.elective.pool.ConnectionPool;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    public static final String REQUEST_PARAMETER_PAGE = "page";
    public static final long serialVersionUID = 1L;
    private final static Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String log4jLocation = config.getInitParameter("log4j-location");
        String path = getServletContext().getRealPath(log4jLocation);
        PropertyConfigurator.configure(path);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        performAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        performAction(request, response);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().cleanUp();
        super.destroy();
    }

    private void performAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(REQUEST_PARAMETER_PAGE);
        if (commandName != null) {
            Command command = null;
            try {
                command = CommandFactory.getCommand(commandName);
            } catch (TechnicalException e) {
                response.sendError(500);
                LOGGER.error(e);
            }
            String nextPage = null;
            if (command != null) {
                nextPage = command.execute(request);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
            requestDispatcher.forward(request, response);
        } else {
            response.sendError(500);
            LOGGER.error("Page parameter is null");
        }
    }
}
