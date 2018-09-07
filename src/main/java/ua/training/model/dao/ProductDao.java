package ua.training.model.dao;

import ua.training.model.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends GenericDao<Product> {
    void addProduct(int code, String name, boolean isSoldByWeight, int number, long weight, long price) throws SQLException;

    List<Product> findAllSorted(String sortBy) throws SQLException;

    List<Product> findNumberSorted(String sortBy, int integer, int offse) throws SQLException;
}
