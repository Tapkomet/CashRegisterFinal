package ua.training.controller.commands.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.util.Path;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserListCommand implements ua.training.controller.commands.Command {
    private UserService userService;

    public UserListCommand(UserService userService) {
        this.userService = userService;
    }
    private static final Logger logger = LogManager.getLogger(UserListCommand.class);

    /**
     * Lists all users
     * @param request Servlet request
     * @param response Servlet response
     * @throws ServletException if forwarding or redirecting fails
     * @throws IOException if forwarding or redirecting
     * @author Roman Kobzar
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> users = userService.getAllUsers();
            request.setAttribute("users", users);
        } catch (SQLException e) {
            logger.debug("Database error when requesting users");
            request.setAttribute("sql_error_message", "Database problem: " + e.getMessage());
        }
        forward(request, response, Path.USER_LIST);
    }

}
