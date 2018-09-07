package ua.training.controller.commands.product;

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

public class ProductListCommand implements ua.training.controller.commands.Command {
    private ProductService productService;

    public ProductListCommand(ProductService productService) {
        this.productService = productService;
    }

    private static final Logger logger = LogManager.getLogger(ProductListCommand.class);
    private final int ROWS_ON_PAGE = 5;

    /**
     * Lists all products available, possibly sorted
     *
     * @param request  Servlet request
     * @param response Servlet response
     * @throws ServletException if forwarding or redirecting fails
     * @throws IOException      if forwarding or redirecting
     * @author Roman Kobzar
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sortBy = request.getParameter("tosort");
        if (sortBy == null || sortBy.equals("")) {
            sortBy = "code";
        }

        String pageString = request.getParameter("page");
        int page;
        if (pageString == null || pageString.isEmpty()) {
            logger.debug(":: page = {}", pageString);
            page = 1;
        } else {
            page = Integer.parseInt(pageString);
        }
        int nextPage;
        String nextPageString = request.getParameter("nextPage");
        if ("previous".equals(nextPageString)) {
            nextPage = page - 1;
        } else if ("next".equals(nextPageString)) {
            nextPage = page + 1;
        } else {
            nextPage = page;
        }
        page = nextPage;
        int productAmount;
        try {
            productAmount = productService.getProductCount();
        } catch (SQLException e) {
            logger.debug("Database error when requesting product count");
            request.setAttribute("sql_error_message", "Database problem: " + e.getMessage());
            forward(request, response, Path.PRODUCT_LIST);
            return;
        }

        int lastPage = productAmount / ROWS_ON_PAGE
                + ((productAmount % ROWS_ON_PAGE) == 0 ? 0 : 1);

        List<Product> products;
        try {
            if (lastPage > page) {
                Integer offset = (page - 1) * ROWS_ON_PAGE;
                products = productService.getProductsSorted(sortBy, ROWS_ON_PAGE, offset);
            } else {
                Integer offset = (lastPage - 1) * ROWS_ON_PAGE;
                products = productService.getProductsSorted(sortBy, ROWS_ON_PAGE, offset);
                page = lastPage;
            }
            request.setAttribute("tosort", sortBy);
            request.setAttribute("products", products);
            request.setAttribute("page", page);
            request.setAttribute("lastPage", lastPage);
        }
        catch (SQLException e){
            logger.debug("Database error when requesting products");
            request.setAttribute("sql_error_message", "Database problem: " + e.getMessage());
        }

        forward(request, response, Path.PRODUCT_LIST);
    }

    void getAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> products = productService.getAllProducts();
            request.setAttribute("products", products);
        } catch (SQLException e) {
            logger.debug("Database error when requesting products");
            request.setAttribute("sql_error_message", "Database problem: " + e.getMessage());
        }
        forward(request, response, Path.PRODUCT_LIST);
    }
}
