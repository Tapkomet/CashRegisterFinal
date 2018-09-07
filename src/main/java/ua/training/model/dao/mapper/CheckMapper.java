package ua.training.model.dao.mapper;

import ua.training.model.entity.Check;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class CheckMapper implements ObjectMapper<Check> {


    @Override
    public Check extractFromResultSet(ResultSet rs) throws SQLException {
        Check check = new Check();
        check.setId(rs.getInt("check_id"));
        check.setTotalPrice(rs.getLong("total_price"));
        check.setCreateTime(rs.getTimestamp("create_time"));
        return check;
    }

    @Override
    public Check makeUnique(Map<Integer, Check> cache,
                              Check check) {
        cache.putIfAbsent(check.getId(), check);
        return cache.get(check.getId());
    }

    public Check mergeChecks(Check check, Check tempCheck) {
        if (check == null) {
            return tempCheck;
        }
        final ArrayList<ua.training.model.entity.Product> temp = new ArrayList<>(check.getProducts());
        temp.addAll(check.getProducts());
        temp.addAll(tempCheck.getProducts());
        check.setProducts(temp);
        return check;
    }
}
