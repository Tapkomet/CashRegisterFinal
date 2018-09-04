package ua.training.controller.commands.product;

import ua.training.model.entity.Product;
import ua.training.model.entity.User;
import ua.training.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductCommand implements ua.training.controller.commands.Command {
    private ProductService productService;

    public DeleteProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("id");
        int code = Integer.parseInt(sid);
        productService.delete(code);
        response.sendRedirect(request.getContextPath() + "/api/manager/products");
    }
}
