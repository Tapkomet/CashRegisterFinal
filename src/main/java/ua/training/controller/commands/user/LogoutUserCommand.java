package ua.training.controller.commands.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.controller.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LogoutUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LogoutUserCommand.class);

    /**
     * @param request
     * logs out user
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandUtility.setUser(request, null);
        logger.info("Logged out.");
        forward(request, response, Path.INDEX);
    }
}
