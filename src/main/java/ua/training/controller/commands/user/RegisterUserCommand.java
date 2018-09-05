package ua.training.controller.commands.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.controller.util.Regex;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


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
        if (surname == null || surname.equals("")) {
            request.setAttribute("surname_error_message", "Put in the surname");
            forward(request, response, "/registration.jsp");
            return;
        }
        if (email == null || email.equals("")) {
            request.setAttribute("email_error_message", "Put in the email");
            forward(request, response, "/registration.jsp");
            return;
        }
        if(pass == null || pass.equals("")){
            request.setAttribute("password_error_message", "Put in the password");
            forward(request, response, "/registration.jsp");
            return;
        }


        if (!Regex.isEmailCorrect(email)) {
            request.setAttribute("email_error_message", "Invalid email");
            forward(request, response, "/registration.jsp");
            return;
        }
        if (!Regex.isSurnameCorrect(surname)) {
            request.setAttribute("surname_error_message", "Invalid name");
            forward(request, response, "/registration.jsp");
            return;
        }
        if (!Regex.isPasswordCorrect(pass)) {
            request.setAttribute("password_error_message", "Invalid password");
            forward(request, response, "/registration.jsp");
            return;
        }

        try {
            userService.register(surname, email, pass);
        } catch (SQLException e) {
            logger.debug("Database error when registering user {}", email);
            request.setAttribute("sql_error_message", "Database problem: " + e.getMessage());
        }
        logger.info("Registration success for user {}", email);
        request.setAttribute("index_message", "Registration successful");
        forward(request, response, "/index.jsp");
    }
}
