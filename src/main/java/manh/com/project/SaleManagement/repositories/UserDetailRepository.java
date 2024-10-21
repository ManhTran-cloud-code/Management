package manh.com.project.SaleManagement.repositories;

import manh.com.project.SaleManagement.models.UserDetail;
import manh.com.project.SaleManagement.utis.ConvertLocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDetailRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public UserDetailRepository( JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserDetail mapRowToUserDetail(ResultSet rs) throws SQLException {
        UserDetail userDetail = new UserDetail();
        userDetail.setUserDetailId(rs.getInt("user_detail_id"));
        userDetail.setPhoneNumber(rs.getString("phone"));
        userDetail.setAddress(rs.getString("address"));
        userDetail.setDateOfBirth(new ConvertLocalDate().convertStringToLocalDate(rs.getString("birthday")));
        return userDetail;
    }

    public UserDetail findUserDetailByUserId(int userId) {
        String sql = "select * from UserDetail where user_id=?";
        return jdbcTemplate.queryForObject(sql,(rs, rowNum) -> mapRowToUserDetail(rs),userId);
    }

    public String getPhoneByUserId(int userId) {
        String sql = "select phone from UserDetail where user_id=?";
        return jdbcTemplate.query(sql,(rs, rowNum) -> (rs.getString("phone")),userId).toString();
    }
    public int saveUserDetail(UserDetail userDetail) {
        String sql = "insert into UserDetail(user_id,phone,address,birthday) values(?,?,?,?)";
        return jdbcTemplate.update(sql,userDetail.getUserId(),userDetail.getPhoneNumber(),userDetail.getAddress(),userDetail.getDateOfBirth());
    }
}
