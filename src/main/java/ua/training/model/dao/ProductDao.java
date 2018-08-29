package ua.training.model.dao;

import ua.training.model.entity.Product;

public interface ProductDao extends GenericDao<Product> {
    void addProduct(int code, String name, boolean isSoldByWeight, int number, long weight);
}
