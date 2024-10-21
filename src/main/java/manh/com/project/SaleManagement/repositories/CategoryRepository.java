package manh.com.project.SaleManagement.repositories;


import jakarta.validation.constraints.NotNull;
import manh.com.project.SaleManagement.models.Category;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryRepository {
     private final JdbcTemplate jdbcTemplate;

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @NotNull
    private Category mapRowToCategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        return category;
    }
    public Category save(Category category) {
        String sql = "INSERT INTO Category (id, name) VALUES (?, ?)";
        jdbcTemplate.update(sql, category.getId(), category.getName());
        return category;
    }
    public List<Category> getAll() {
        String sql = "SELECT * FROM Category";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> mapRowToCategory(rs));
    }

    public Category getCategory(int id){
        String sql = "SELECT * FROM Category WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,(rs, rowNum) -> mapRowToCategory(rs),id);
    }
    public int update(Category category) {
        String sql = "UPDATE Category SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, category.getName(),category.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM Category WHERE id = ?";
        return jdbcTemplate.update(sql,id);
    }

}
