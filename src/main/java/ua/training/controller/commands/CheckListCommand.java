package ua.training.controller.commands;

import ua.training.model.entity.Check;
import ua.training.model.service.CheckService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CheckListCommand implements Command {
    private CheckService checkService;

    public CheckListCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Check> checks = checkService.getAllChecks();
        request.setAttribute("checks" , checks);
        return "/WEB-INF/checklist.jsp";
    }
}
