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
            try {
                return productDao.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void addProduct(int code, String name, boolean isSoldByWeight, int number, long weight, long price) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            productDao.addProduct(code, name, isSoldByWeight, number, weight, price);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Product product){
        try (ProductDao productDao = daoFactory.createProductDao()) {
            productDao.create(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
