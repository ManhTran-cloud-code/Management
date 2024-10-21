package manh.com.project.SaleManagement.repositories;

import manh.com.project.SaleManagement.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CartRepository {
    @Autowired
    private ProductRepository productRepository;
@Autowired
private final JdbcTemplate jdbcTemplate;
public CartRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
}

public Cart mapRowToCart(ResultSet rs) throws SQLException {
    Cart cart = new Cart();
    cart.setCartId(rs.getInt("cart_id"));
    cart.setUserId(rs.getInt("user_id"));
    cart.setProduct(productRepository.getProduct(rs.getInt("product_id")));
    cart.setQuantity(rs.getInt("quantity"));
    return cart;
}

public List<Cart> getCartByUserId(int userId){
    String sql = "SELECT * FROM Cart WHERE user_id=?";
    return jdbcTemplate.query(sql, (rs,rowNum)->mapRowToCart(rs),userId);
}

public int addCart(Cart cart, int userId) {
    String sql = "INSERT INTO Cart(user_id,product_id,quantity) VALUES(?,?,?)";
    return jdbcTemplate.update(sql,userId,cart.getProduct().getId(),cart.getQuantity());
}

public Cart findCartById(int cartId){
    String sql = "SELECT * FROM Cart WHERE cart_id=?";
    return  jdbcTemplate.queryForObject(sql,(rs, rowNum)->mapRowToCart(rs),cartId);
}

public int deleteCart(int cartId){
    String sql = "DELETE FROM Cart WHERE cart_id=?";
    return jdbcTemplate.update(sql,cartId);
}

}
