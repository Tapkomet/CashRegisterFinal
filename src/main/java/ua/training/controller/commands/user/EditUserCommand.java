package ua.training.controller.commands.user;

import ua.training.controller.commands.Command;
import ua.training.controller.commands.user.UserListCommand;
import ua.training.controller.util.Path;
import ua.training.controller.util.Regex;
import ua.training.model.entity.User;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditUserCommand implements Command {
    private UserService userService;

    public EditUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codeStr = request.getParameter("code");
        String role = request.getParameter("role");
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        User user = new User();
        user.setId(id);
        user.setRoleFromString(role);
        User loggedUser = (User) ((HttpServletRequest) request).getSession().getAttribute("user");
        if(id==loggedUser.getId()){
            request.setAttribute("self_error_message", "You cannot change your own role");
            UserListCommand listCommand = new UserListCommand(userService);
            listCommand.execute(request, response);
            return;
        }
        try {
            userService.update(user);
        } catch (SQLException e) {
            request.setAttribute("sql_error_message", "Database problem: " + e.getMessage());
            UserListCommand listCommand = new UserListCommand(userService);
            listCommand.execute(request, response);
            return;
        }
        redirect(request, response, Path.ADMIN_USERS);
    }

}
