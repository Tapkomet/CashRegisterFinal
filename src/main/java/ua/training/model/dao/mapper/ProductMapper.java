package ua.training.model.dao.mapper;

import ua.training.model.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ProductMapper implements ObjectMapper<Product> {


    @Override
    public Product extractFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setCode(rs.getInt("code"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getLong("price"));
        return product;
    }

    @Override
    public Product makeUnique(Map<Integer, Product> cache,
                              Product product) {
        cache.putIfAbsent(product.getCode(), product);
        return cache.get(product.getCode());
    }
}
