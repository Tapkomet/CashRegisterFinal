package ua.training.controller.commands.check;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.util.Path;
import ua.training.model.entity.Product;
import ua.training.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CheckAddPageCommand implements ua.training.controller.commands.Command {
    private ProductService productService;

    public CheckAddPageCommand(ProductService productService) {
        this.productService = productService;
    }
    private static final Logger logger = LogManager.getLogger(CheckAddPageCommand.class);

    /**
     * Lists all products available, possibly sorted, for adding to the check
     * @param request Servlet request
     * @param response Servlet response
     * @throws ServletException if forwarding or redirecting fails
     * @throws IOException if forwarding or redirecting
     * @author Roman Kobzar
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sortBy = request.getParameter("tosort");
        if(sortBy==null) {
            getAllProducts(request, response);
        }
        else{
            try {
                List<Product> products = productService.getProductsSortedBy(sortBy);
                request.setAttribute("products", products);
            } catch (SQLException e) {
                logger.debug("Database error when requesting products");
                request.setAttribute("sql_error_message", "Database problem: " + e.getMessage());
            }
            forward(request, response, Path.CASHIER_PRODUCT_LIST);
        }
    }

    void getAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> products = productService.getAllProducts();
            request.setAttribute("products", products);
        } catch (SQLException e) {
            logger.debug("Database error when requesting products");
            request.setAttribute("sql_error_message", "Database problem: " + e.getMessage());
        }
        forward(request, response, Path.CASHIER_PRODUCT_LIST);
    }
}
