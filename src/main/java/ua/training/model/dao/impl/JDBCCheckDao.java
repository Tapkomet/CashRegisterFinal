package ua.training.model.dao.impl;

import ua.training.model.dao.CheckDao;
import ua.training.model.dao.mapper.CheckMapper;
import ua.training.model.entity.Check;
import ua.training.model.entity.Product;
import ua.training.model.service.ProductService;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCCheckDao implements CheckDao {
    private Connection connection;


    public JDBCCheckDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Check check) throws SQLException {
        int id = check.getId();
        long totalPrice = check.getTotalPrice();
        int cashierId = check.getCashier().getId();
        int shiftId = check.getShift().getId();
        connection.setAutoCommit(false);
        PreparedStatement stmt = connection.prepareStatement(
                "insert into cheque (check_id, total_price, cashier_id, shift_id)" +
                " values (?, ?, ?, ?)");
        stmt.setInt(1, id);
        stmt.setLong(2, totalPrice);
        stmt.setInt(3, cashierId);
        stmt.setInt(4, shiftId);
        stmt.addBatch();
        stmt = connection.prepareStatement(
                "insert into product_in_check (code, name, is_sold_by_weight, number_sold," +
                        " weight_sold, price, check_id)" +
                        " values (?, ?, ?, ?, ?, ?, ?)");
        for(Product product : check.getProducts()){
            stmt.setInt(1, product.getCode());
            stmt.setString(2, product.getName());
            stmt.setBoolean(3, product.isSoldByWeight());
            stmt.setInt(4, product.getNumber());
            stmt.setLong(5, product.getWeight());
            stmt.setLong(6, product.getPrice());
            stmt.setInt(7, product.getCheck().getId());
            stmt.addBatch();
        }
        stmt.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);
        stmt.close();
        connection.close();
    }

    @Override
    public Check findById(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "select * from check where code = (?)");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        CheckMapper checkMapper = new CheckMapper();

        rs.next();
        Check check = checkMapper.extractFromResultSet(rs);

        stmt.close();
        connection.close();
        return check;
    }

    @Override
    public List<Check> findAll() throws SQLException {
        Map<Integer, Check> checks = new HashMap<>();

        final String query = "" +
                " select * from cheque";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        CheckMapper checkMapper = new CheckMapper();

        while (rs.next()) {
            Check check = checkMapper
                    .extractFromResultSet(rs);
            check = checkMapper
                    .makeUnique(checks, check);
        }
        return new ArrayList<>(checks.values());
    }



    @Override
    public void update(Check check) {

    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "delete from check where code = (?)");
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
}
