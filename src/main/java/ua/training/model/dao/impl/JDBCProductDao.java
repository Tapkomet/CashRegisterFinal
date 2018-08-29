package ua.training.model.dao.impl;

import ua.training.model.dao.ProductDao;
import ua.training.model.dao.mapper.ProductMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.Product;
import ua.training.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCProductDao implements ProductDao {
    private Connection connection;


    public JDBCProductDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Product entity) {

    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        Map<Integer, Product> products = new HashMap<>();
        Map<Integer, User> users = new HashMap<>();

        final String query = "" +
                " select * from product";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            ProductMapper productMapper = new ProductMapper();

            while (rs.next()) {
                Product product = productMapper
                        .extractFromResultSet(rs);
                product = productMapper
                        .makeUnique(products, product);
            }
            return new ArrayList<>(products.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public void update(Product entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addProduct(int code, String name, boolean isSoldByWeight, int number, long weight) {

    }
}
