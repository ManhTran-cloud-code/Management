package manh.com.project.SaleManagement.repositories;

import manh.com.project.SaleManagement.models.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DiscountRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public DiscountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Discount mapRowToDiscount(ResultSet rs) throws SQLException {
        Discount discount = new Discount();
        discount.setDiscountId(rs.getInt("discount_id"));
        discount.setDiscountName(rs.getString("discount_name"));
        discount.setDiscountType(rs.getInt("discount_type"));
        discount.setStartDate(rs.getDate("start_date"));
        discount.setEndDate(rs.getDate("end_date"));
        return discount;
    }

    public int saveDiscount(Discount discount) {
        String sql = "INSERT INTO discount (discount_name, discount_type,start_date,end_date) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql,discount.getDiscountName(), discount.getDiscountType(), discount.getStartDate(), discount.getEndDate());
    }
}
