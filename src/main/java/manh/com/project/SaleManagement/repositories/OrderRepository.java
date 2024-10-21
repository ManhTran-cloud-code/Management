package manh.com.project.SaleManagement.repositories;

import manh.com.project.SaleManagement.models.Order;
import manh.com.project.SaleManagement.models.Sale;
import manh.com.project.SaleManagement.utis.ConvertLocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public class OrderRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Order mapRowTOOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setOrderStatus(rs.getInt("order_status"));
        order.setOrderDate(new ConvertLocalDate().convertStringToLocalDate(rs.getString("order_date")));
        order.setTotalMoney(rs.getLong("total_money"));
        return order;
    }

    public int saveOrder(Order order) {
        String sql = "INSERT INTO [Order](user_id, order_status, order_date, total_money) VALUES(?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection->{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getOrderStatus());
            ps.setDate(3, Date.valueOf(order.getOrderDate()));
            ps.setLong(4,order.getTotalMoney());
            return ps;
        },keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public List<Order> findAll() {
        String sql = "SELECT * FROM Order";
        return jdbcTemplate.query(sql,(rs,rowNum)->mapRowTOOrder(rs));
    }

//    public int updateOrder(Order order) {
//        String sql = "UPDATE Order SET order_status = ?, order_date = ?, total_money = ? WHERE order_id = ?";
//        return 0;
//    }

    //tim cac don hang chua theo trang thai gom chua xac nhan xac nhan dang giao hang va giao xong
    public List<Order> findOrderByStatus(int status) {
        String sql = "SELECT * FROM [Order] WHERE order_status = ? ORDER BY  order_date desc";
        return jdbcTemplate.query(sql,(rs,rowNum)->mapRowTOOrder(rs),status);
    }

    public int updateStatus(int status,int orderId){
        String sql = "Update [Order] SET order_status = ? WHERE order_id = ?";
        return jdbcTemplate.update(sql,status,orderId);
    }
    public List<Sale> getTotalMoneyByDay(){
        String sql = "SELECT SUM(total_money) AS money,order_date FROM [Order] GROUP BY order_date";
        List<Map<String, Object>> rows =  jdbcTemplate.queryForList(sql);
        List<Sale> listOfSale = new ArrayList<>();
        for (Map<String, Object> row : rows) {
           Sale sale = new Sale();
            BigDecimal bigDecimal = (BigDecimal) row.get("money");
            sale.setTotalMoney(bigDecimal.doubleValue());
            sale.setOrderDate((Date) row.get("order_date"));
            listOfSale.add(sale);
        }
        return listOfSale;
}
}
