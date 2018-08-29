package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ProductDao;
import ua.training.model.entity.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Product> getAllProducts(){
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.findAll();
        }
    }

    public void addProduct(int code, String name, boolean isSoldByWeight, int number, long weight) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            productDao.addProduct(code, name, isSoldByWeight, number, weight);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
