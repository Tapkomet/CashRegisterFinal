package ua.training.model.dao.mapper;

import ua.training.model.entity.Product;
import ua.training.model.entity.User;

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
        product.setSoldByWeight(rs.getBoolean("is_sold_by_weight"));
        product.setNumber(rs.getInt("number_in_stock"));
        product.setWeight(rs.getLong("weight_in_stock"));
        User manager = new User();
        manager.setId(rs.getInt("product_manager_id"));
        product.setManager(manager);
        return product;
    }

    @Override
    public Product makeUnique(Map<Integer, Product> cache,
                              Product product) {
        cache.putIfAbsent(product.getCode(), product);
        return cache.get(product.getCode());
    }
}
