package ua.training.controller.commands;

import ua.training.model.entity.Product;
import ua.training.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddProductCommand implements ua.training.controller.commands.Command {
    private ProductService productService;

    public AddProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int code = Integer.parseInt(request.getParameter("code"));
        String name = request.getParameter("name");
        boolean isSoldByWeight = Boolean.parseBoolean(request.getParameter("soldByWeight"));
        int number = Integer.parseInt(request.getParameter("number"));
        long weight = Long.parseLong(request.getParameter("weight"));
        long price = Long.parseLong(request.getParameter("price"));
        productService.addProduct(code, name, isSoldByWeight, number, weight, price);
        List<Product> products = productService.getAllProducts();
        request.setAttribute("products" , products);
        return "/WEB-INF/productlist.jsp";
    }
}
