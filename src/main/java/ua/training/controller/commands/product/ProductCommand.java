package ua.training.controller.commands.product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.controller.util.Path;
import ua.training.model.entity.Product;
import ua.training.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductCommand implements Command {
    private ProductService productService;

    public ProductCommand(ProductService productService) {
        this.productService = productService;
    }
    private static final Logger logger = LogManager.getLogger(ProductCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("id");
        int code = Integer.parseInt(sid);
        try {
            Product product = productService.getProductById(code);
            request.setAttribute("product", product);
        } catch (SQLException e) {
            logger.debug("Database error when requesting product {}"+code);
            request.setAttribute("sql_error_message", "Database problem: " + e.getMessage());
        }
        forward(request, response, Path.PRODUCT);
    }
}
