package ua.training.controller.commands;

import ua.training.model.entity.Product;
import ua.training.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductListCommand implements ua.training.controller.commands.Command {
    private ProductService productService;

    public ProductListCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.getAllProducts();
        request.setAttribute("products" , products);
        forward(request, response, "/WEB-INF/productlist.jsp");
    }
}
