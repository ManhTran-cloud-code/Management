package manh.com.project.SaleManagement.repositories;

import manh.com.project.SaleManagement.models.DiscountDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DiscountDetailRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DiscountDetailRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public DiscountDetail mapRowToDiscountDetail(ResultSet rs) throws SQLException {
        DiscountDetail discountDetail = new DiscountDetail();
        discountDetail.setDiscountId(rs.getInt("discount_detail_id"));
        discountDetail.setDiscountId(rs.getInt("discount_id"));
        discountDetail.setProductId(rs.getInt("product_id"));
        discountDetail.setCategoryId(rs.getInt("category_id"));
        return discountDetail;
    }

    public int saveDiscountDetail(DiscountDetail discountDetail){
        String sql = "INSERT INTO discount_detail(discount_id,product_id) VALUES(?,?)";
        return jdbcTemplate.update(sql, discountDetail.getDiscountId(), discountDetail.getProductId());
    }
}
