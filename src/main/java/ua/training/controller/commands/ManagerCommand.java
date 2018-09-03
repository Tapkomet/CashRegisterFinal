package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class ManagerCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/managerbase.jsp";
    }
}
