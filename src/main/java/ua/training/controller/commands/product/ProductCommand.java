package ua.training.controller.commands.product;

import ua.training.controller.commands.Command;
import ua.training.model.entity.Product;
import ua.training.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductCommand implements Command {
    private ProductService productService;

    public ProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("id");
        int code = Integer.parseInt(sid);
        Product product = productService.getProductById(code);
        request.setAttribute("product" , product);
        forward(request, response, "/WEB-INF/product.jsp");
    }
}
