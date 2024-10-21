package manh.com.project.SaleManagement.repositories;

import manh.com.project.SaleManagement.models.OrderItem;
import manh.com.project.SaleManagement.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderItemRepository {
    @Autowired
    private JdbcTemplate template;
    @Autowired
    private ProductService productService;
    private JdbcTemplate jdbcTemplate;

    public OrderItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public OrderItem mapRowToOrderItem(ResultSet rs) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(rs.getInt("order_item_id"));
        orderItem.setOrderId(rs.getInt("order_id"));
        orderItem.setProduct(productService.findProductById(rs.getInt("product_id")));
        orderItem.setQuantity(rs.getInt("quantity"));
        return orderItem;
    }

    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into OrderItem(order_id,product_id,quantity) values(?,?,?)";
        return jdbcTemplate.update(sql, orderItem.getOrderId(), orderItem.getProduct().getId(),orderItem.getQuantity());
    }

    public List<OrderItem> findOrderItemByOrderId(int orderId){
        String sql = "SELECT * FROM OrderItem WHERE order_id=?";
        return jdbcTemplate.query(sql,(rs,rowNum)->mapRowToOrderItem(rs),orderId);
    }


}
