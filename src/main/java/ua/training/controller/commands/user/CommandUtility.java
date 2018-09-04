package ua.training.controller.commands.user;


import ua.training.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

class CommandUtility {
    static void setUserRole(HttpServletRequest request,
                            User.ROLE role, String email) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("email", email);
        session.setAttribute("user", role);
    }

    static void setUser(HttpServletRequest request,
                            User user) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        session.setAttribute("user", user);
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String email){
        /*HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        if(loggedUsers.stream().anyMatch(email::equals)){
            return true;
        }
        loggedUsers.add(email);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;*/
        if((User) ((HttpServletRequest) request).getSession().getAttribute("user")!=null){
            return true;
        }
        return false;
    }
}
