package ua.training.controller.commands.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegisterUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RegisterUserCommand.class);
    private UserService userService ;

    public RegisterUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        if( email == null || email.equals("") || pass == null || pass.equals("")  ){
            forward(request, response, "/index.jsp");
            return;
        }
        userService.register(surname, email, pass);
        logger.info("Registration attempt");
        forward(request, response, "/index.jsp");
    }
}