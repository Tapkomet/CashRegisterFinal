package ua.training.controller;

import ua.training.controller.commands.*;
import ua.training.controller.commands.check.AddCheckCommand;
import ua.training.controller.commands.check.CheckAddPageCommand;
import ua.training.controller.commands.check.CheckCommand;
import ua.training.controller.commands.check.CheckListCommand;
import ua.training.controller.commands.product.*;
import ua.training.controller.commands.user.*;
import ua.training.controller.util.Path;
import ua.training.model.service.CheckService;
import ua.training.model.service.ProductService;
import ua.training.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {

    private ProductService productService = new ProductService();
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        commands.put("manager/product",
                new ProductCommand(new ProductService()));
        commands.put("manager/products",
                new ProductListCommand(new ProductService()));
        commands.put("manager/addProduct",
                new AddProductCommand(new ProductService()));
        commands.put("manager/editProduct",
                new EditProductCommand(new ProductService()));
        commands.put("manager/deleteProduct",
                new DeleteProductCommand(new ProductService()));
        commands.put("user-login",
                new LoginUserCommand(new UserService()));
        commands.put("logout",
                new LogoutUserCommand());
        commands.put("user-register",
                new RegisterUserCommand(new UserService()));
        commands.put("exception" , new ExceptionCommand());
        commands.put("cashier/check",
                new CheckCommand(new CheckService()));
        commands.put("cashier/checks",
                new CheckListCommand(new CheckService()));
        commands.put("cashier/checks/addPage",
                new CheckAddPageCommand(new ProductService()));
        commands.put("cashier/checks/add",
                new AddCheckCommand(new CheckService()));
        commands.put("manager" , new ManagerCommand());
        commands.put("admin/users", new UserListCommand(new UserService()));
        commands.put("admin/users/edit", new EditUserCommand(new UserService()));
        commands.put("admin" , new AdminCommand());
        commands.put("cashier" , new CashierCommand());
    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getRequestURI();
        path = path.replaceAll(".*/api/" , "");
        Command command = commands.containsKey(path) ? commands.get(path) : commands.get(Path.INDEX);
        command.execute(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
