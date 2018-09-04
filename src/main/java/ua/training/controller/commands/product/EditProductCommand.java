package ua.training.controller.commands.product;

import ua.training.controller.commands.Command;
import ua.training.model.entity.Product;
import ua.training.model.entity.User;
import ua.training.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditProductCommand implements Command {
    private ProductService productService;

    public EditProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int code = Integer.parseInt(request.getParameter("code"));
        //String name = request.getParameter("name");
        boolean isSoldByWeight = Boolean.parseBoolean(request.getParameter("soldByWeight"));
        long price = Long.parseLong(request.getParameter("price"));
        User user = (User) ((HttpServletRequest) request).getSession().getAttribute("user");
        Product product = new Product.Builder(code)
        //        .productName(name)
                .isSoldByWeight(isSoldByWeight)
                .price(price)
                .byManager(user)
                .build();
        if (isSoldByWeight) {
            long weight = Long.parseLong(request.getParameter("weight"));
            product.setWeight(weight);
        } else {
            int number = Integer.parseInt(request.getParameter("number"));
            product.setNumber(number);
        }
        productService.update(product);
        response.sendRedirect(request.getContextPath() + "/api/manager/products");
    }
}
