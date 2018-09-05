package ua.training.controller.commands.product;

import ua.training.controller.util.Path;
import ua.training.model.entity.Product;
import ua.training.model.entity.User;
import ua.training.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DeleteProductCommand implements ua.training.controller.commands.Command {
    private ProductService productService;

    public DeleteProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("id");
        int code = Integer.parseInt(sid);
        try {
            productService.delete(code);
        } catch (SQLException e) {
            request.setAttribute("sql_error_message", "Database problem: " + e.getMessage());
            ProductListCommand listCommand = new ProductListCommand(productService);
            listCommand.getAllProducts(request, response);
            return;
        }
        redirect(request, response, Path.MANAGER_PRODUCTS);
    }
}
