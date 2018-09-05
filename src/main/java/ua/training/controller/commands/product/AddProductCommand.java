package ua.training.controller.commands.product;

import ua.training.controller.util.Path;
import ua.training.controller.util.Regex;
import ua.training.model.entity.Product;
import ua.training.model.entity.User;
import ua.training.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddProductCommand implements ua.training.controller.commands.Command {
    private ProductService productService;

    public AddProductCommand(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Add product to database as per Product Manager's responsibility.
     * In case of an SQL exception displays info on the page
     * @param request Servlet Request that contains info for the product being added
     * @param response Servlet Response
     * @throws ServletException if forwarding or redirecting fails
     * @throws IOException if forwarding or redirecting
     * @author Roman Kobzar
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codeStr = request.getParameter("code");
        String priceStr = request.getParameter("price");
        if (!Regex.isNumberCorrect(codeStr)) {
            request.setAttribute("code_error_message", "Invalid code");
            showError(request, response);
            return;
        }
        if (!Regex.isNumberCorrect(priceStr)) {
            request.setAttribute("price_error_message", "Invalid price");
            showError(request, response);
            return;
        }
        int code = Integer.parseInt(codeStr);
        String name = request.getParameter("name");
        if(name==null||name.equals("")){
            request.setAttribute("name_error_message", "Put in the name");
            showError(request, response);
            return;
        }
        boolean isSoldByWeight = !(request.getParameter("soldByWeight") == null);
        long price = Long.parseLong(priceStr);
        User user = (User) ((HttpServletRequest) request).getSession().getAttribute("user");
        Product product = new Product.Builder(code)
                .productName(name)
                .isSoldByWeight(isSoldByWeight)
                .price(price)
                .byManager(user)
                .build();
        if (isSoldByWeight) {
            String weightStr = request.getParameter("weight");
            if (!Regex.isNumberCorrect(weightStr)) {
                request.setAttribute("weight_error_message", "Invalid price");
                showError(request, response);
                return;
            }
            long weight = Long.parseLong(weightStr);
            product.setWeight(weight);
        } else {
            String numberStr = request.getParameter("number");
            if (!Regex.isNumberCorrect(numberStr)) {
                request.setAttribute("number_error_message", "Invalid number");
                showError(request, response);
                return;
            }
            int number = Integer.parseInt(numberStr);
            product.setNumber(number);
        }
        try {
            productService.create(product);
        } catch (SQLException e) {
            request.setAttribute("sql_error_message", "Database problem: " + e.getMessage());
            showError(request, response);
            return;
        }
        redirect(request, response, Path.MANAGER_PRODUCTS);
    }

    private void showError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductListCommand listCommand = new ProductListCommand(productService);
        listCommand.getAllProducts(request, response);
    }
}
