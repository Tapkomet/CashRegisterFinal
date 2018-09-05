package ua.training.controller.commands.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.controller.util.Regex;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.exception.LoginException;
import ua.training.model.service.exception.WrongEmailException;
import ua.training.model.service.exception.WrongPasswordException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class LoginUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LoginUserCommand.class);
    private UserService userService;

    public LoginUserCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param request
     * checks user data and logs them in
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        if (email == null || email.equals("")) {
            request.setAttribute("email_error_message", "Put in the email");
            forward(request, response, "/login.jsp");
            return;
        }
        if(pass == null || pass.equals("")){
            request.setAttribute("password_error_message", "Put in the password");
            forward(request, response, "/login.jsp");
            return;
        }
        if (!Regex.isPasswordCorrect(pass)) {
            request.setAttribute("password_error_message", "Invalid password");
            forward(request, response, "/login.jsp");
            return;
        }
        if (!Regex.isEmailCorrect(email)) {
            request.setAttribute("email_error_message", "Invalid email");
            forward(request, response, "/login.jsp");
            return;
        }

        if (CommandUtility.checkUserIsLogged(request, email)) {
            logger.debug("User {} already logged in", email);
            forward(request, response, "/api/exception");
            return;
        }
        try {
            Optional<User> user = userService.login(email, pass);
            CommandUtility.setUser(request, user.get());
            logger.info("User {} of role {} logged succesfully", email, user.get().getRole());
            if (user.get().getRole() == User.ROLE.SENIOR_CASHIER) {
                redirect(request, response, "/api/admin");
                return;

            }
            if (user.get().getRole() == User.ROLE.PRODUCT_MANAGER) {
                redirect(request, response, "/api/manager");
                return;
            }
            if (user.get().getRole() == User.ROLE.CASHIER) {
                redirect(request, response, "/api/cashier");
                return;
            }
            forward(request, response, "/api/exception");
            return;
        } catch (WrongEmailException e) {
            request.setAttribute("email_error_message", "Wrong email");
        } catch (WrongPasswordException e) {
            request.setAttribute("password_error_message", "Wrong password");
        } catch (LoginException e) {
            request.setAttribute("login_error_message", "Login failed");
        }
        logger.info("Invalid attempt of login user: {}", email );
        forward(request, response, "/login.jsp");
        return;
    }
}
