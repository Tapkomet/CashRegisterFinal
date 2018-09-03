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
    public void create(Product product) throws SQLException {
        int code = product.getCode();
        String name = product.getName();
        boolean isSoldByWeight = product.isSoldByWeight();
        int number = product.getNumber();
        long weight = product.getWeight();
        long price = product.getPrice();
        int managerId = product.getManager().getId();
        PreparedStatement stmt = connection.prepareStatement(
                "insert into product (code, name, is_sold_by_weight, number_in_stock," +
                        " weight_in_stock, price, product_manager_id)" +
                        " values (?, ?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, code);
        stmt.setString(2, name);
        stmt.setBoolean(3, isSoldByWeight);
        stmt.setInt(4, number);
        stmt.setLong(5, weight);
        stmt.setLong(6, price);
        stmt.setInt(7, managerId);
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    @Override
    public Product findById(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "select * from product where code = (?)");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        ProductMapper productMapper = new ProductMapper();

        rs.next();
        Product product = productMapper.extractFromResultSet(rs);

        stmt.close();
        connection.close();
        return product;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        Map<Integer, Product> products = new HashMap<>();

        final String query = "" +
                " select * from product";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        ProductMapper productMapper = new ProductMapper();

        while (rs.next()) {
            Product product = productMapper
                    .extractFromResultSet(rs);
            product = productMapper
                    .makeUnique(products, product);
        }
        return new ArrayList<>(products.values());
    }



    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "delete from product where code = (?)");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        stmt.close();
        connection.close();
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
    public void addProduct(int code, String name, boolean isSoldByWeight, int number, long weight, long price) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "insert into product (code, name, is_sold_by_weight, number_in_stock, weight_in_stock, price)" +
                        " values (?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, code);
        stmt.setString(2, name);
        stmt.setBoolean(3, isSoldByWeight);
        stmt.setInt(4, number);
        stmt.setLong(5, weight);
        stmt.setLong(6, price);
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }
}
