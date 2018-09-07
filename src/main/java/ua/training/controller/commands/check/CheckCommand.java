package ua.training.controller.commands.check;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.controller.util.Path;
import ua.training.model.entity.Check;
import ua.training.model.service.CheckService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CheckCommand implements Command {
    private CheckService checkService;

    public CheckCommand(CheckService checkService) {
        this.checkService = checkService;
    }
    private static final Logger logger = LogManager.getLogger(CheckCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        try {
            Check check = checkService.getCheckById(id);
            request.setAttribute("check", check);
            request.setAttribute("products", check.getProducts());
            System.out.println(check.getProducts());
        } catch (SQLException e) {
            logger.debug("Database error when requesting check {}"+id);
            request.setAttribute("sql_error_message", "Database problem: " + e.getMessage());
        }
        forward(request, response, Path.CHECK);
    }
}
