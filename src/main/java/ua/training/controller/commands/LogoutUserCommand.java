package ua.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


public class LogoutUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LogoutUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.setUser(request, null);
        logger.info("Logged out.");
        return "/index.jsp";
    }
}
