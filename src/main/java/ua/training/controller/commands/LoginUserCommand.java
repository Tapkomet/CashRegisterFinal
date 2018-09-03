package ua.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;



public class LoginUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LoginUserCommand.class);
    private UserService userService ;

    public LoginUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        if( email == null || email.equals("") || pass == null || pass.equals("")  ){
            forward(request, response, "/login.jsp");
            return;
        }
        if(CommandUtility.checkUserIsLogged(request, email)){
            forward(request, response, "/WEB-INF/error.jsp");
            return;
        }
        Optional<User> user = userService.login(email);
        if( user.isPresent() && user.get().getPassword().equals(pass)){
            CommandUtility.setUser(request, user.get());
            logger.info("User "+ email+" logged successfully.");
            if(user.get().getRole()==User.ROLE.SENIOR_CASHIER){
                response.sendRedirect(request.getContextPath() + "/api/admin");
                return;

            }
            if(user.get().getRole()==User.ROLE.PRODUCT_MANAGER){
                response.sendRedirect(request.getContextPath() + "/api/manager");
                return;
            }
            if(user.get().getRole()==User.ROLE.CASHIER){
                response.sendRedirect(request.getContextPath() + "/api/cashier");
                return;
            }
            forward(request, response, "/WEB-INF/error.jsp");
            return;
        }
        logger.info("Invalid attempt of login user:'"+ email+"'");
        forward(request, response, "/login.jsp");
        return;
    }
}
