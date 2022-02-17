package thymeleafsandbox.stsm.business.entities.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import thymeleafsandbox.stsm.business.entities.Row;
import thymeleafsandbox.stsm.business.entities.Variety;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class RowDataRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String ROW_VALUES = "INSERT INTO rows_details (variety, number, seeds) VALUES (?,?,?)";
    private static final String ROWS_DETAILS_WHERE_NUMBER = "SELECT * FROM rows_details WHERE number = ?";

    public void saveRow(List<Row> rowData, Integer seedId) {
        for (Row row : rowData) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            PreparedStatementCreator preparedStatementCreator = con -> {
                PreparedStatement preparedStatement = con.prepareStatement(ROW_VALUES, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, row.getVariety().getName());
                preparedStatement.setInt(2, seedId);
                preparedStatement.setInt(3, row.getSeedsPerCell());
                return preparedStatement;
            };
            jdbcTemplate.update(preparedStatementCreator, keyHolder);
        }
    }

    public List<Row> getAllRows(Integer seedId) {
        RowMapper<Row> rowMapper = (resultSet, i) -> {
            Row row = new Row();
            row.setVariety(new Variety(1, resultSet.getString("variety")));
            row.setSeedsPerCell(resultSet.getInt("seeds"));
            return row;
        };
        return jdbcTemplate.query(ROWS_DETAILS_WHERE_NUMBER, rowMapper, seedId);
    }
}