package manh.com.project.SaleManagement.repositories;

import manh.com.project.SaleManagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;


    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User mapRowToUser(ResultSet rs) throws SQLException {
       User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setName(rs.getString("user_name"));
        user.setEmail(rs.getString("email"));
        user.setEncryptPassword(rs.getString("encryptPassword"));
        return user;


    }
    public User findByEmail(String email) {
        String sql = "SELECT * FROM [USER] WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, (rs,rowNum) -> mapRowToUser(rs), email);
    }

    public int saveUser(User user){
        String sql = "Insert Into [User](user_name,email,encryptPassword) values(?,?,?)";
        return jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getEncryptPassword());
    }

}
