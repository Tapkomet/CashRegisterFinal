package ua.training.controller.commands;

import ua.training.model.entity.Product;
import ua.training.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProductListCommand implements ua.training.controller.commands.Command {
    private ProductService productService;

    public ProductListCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Product> products = productService.getAllProducts();
        request.setAttribute("products" , products);
        return "/WEB-INF/productlist.jsp";
    }
}
