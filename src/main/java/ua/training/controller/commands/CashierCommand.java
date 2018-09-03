package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class CashierCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/cashierbase.jsp";
    }
}
