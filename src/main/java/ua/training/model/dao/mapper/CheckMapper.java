package ua.training.model.dao.mapper;

import ua.training.model.entity.Check;

import java.sql.ResultSet;
import java.sql.SQLException;
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
}
