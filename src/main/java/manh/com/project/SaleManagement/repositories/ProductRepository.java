package manh.com.project.SaleManagement.repositories;

import manh.com.project.SaleManagement.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product mapRowToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));
        product.setQuantity(rs.getInt("quantity"));
        product.setImage(rs.getString("image"));
        product.setCategoryId(rs.getInt("category_id"));
        return product;

    }

    public Product getProduct(int id) {
        String sql = "select * from product where id = ?";
       return jdbcTemplate.queryForObject(sql,(rs,rowNum)->mapRowToProduct(rs),id);
    }
    public List<Product> getAllProducts() {
        String sql = "select * from product";
        return jdbcTemplate.query(sql,(rs,rowNum)->mapRowToProduct(rs));
    }
    public int addProduct(Product product) {
        String sql="insert into product(id,name,price,quantity,image,category_id) values(?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,product.getId(),product.getName(),product.getPrice(),product.getQuantity(),product.getImage(),product.getCategoryId());

    }
    public int updateProduct(Product product) {
        String sql = "UPDATE product set name=?,price=?,quantity=?,image=?,category_id=? WHERE id=?";
        return jdbcTemplate.update(sql,product.getName(),product.getPrice(),product.getQuantity(),product.getImage(),product.getCategoryId(),product.getId());
    }
    public int deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE id=?";
        return jdbcTemplate.update(sql,id);
    }

    public List<Product> findProductByCategory(int categoryId) {
        String sql = "select * from product where category_id=?";
        return jdbcTemplate.query(sql,(rs,rowNum)->mapRowToProduct(rs),categoryId);
    }

    public Product findProductById(int productId){
        String sql = "select * from product where id=?";
        return jdbcTemplate.queryForObject(sql,(rs, rowNum)->mapRowToProduct(rs),productId);
    }

    public int updateQuantityProduct(int quantity,int productId) {
        String sql = "Update Product SET quantity= quantity - ? WHERE id=?";
        return jdbcTemplate.update(sql,quantity,productId);
    }
}
