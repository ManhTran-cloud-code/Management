package manh.com.project.SaleManagement.repositories;

import manh.com.project.SaleManagement.models.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorityRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AuthorityRepository(JdbcTemplate jdbcTemplate){
       this.jdbcTemplate =jdbcTemplate;
    }
    public Authority mapRowtoAuthority(ResultSet rs) throws SQLException {
        Authority authority = new Authority();
        authority.setEmail(rs.getString("email"));
        authority.setAuthorityName(rs.getString("authority_name"));
        return authority;
    }
    public List<Authority> findAuthorityByUserEmail(String email){
        String sql = "SELECT * FROM Authority WHERE email=?";
        return  jdbcTemplate.query(sql,(rs, rowNum)->mapRowtoAuthority(rs),email);
    }

    public int saveAuthority(Authority authority){
        String sql = "INSERT INTO Authority(email,authority_name) VALUES(?,?)";
        return jdbcTemplate.update(sql,authority.getEmail(),authority.getAuthorityName());
    }

}
