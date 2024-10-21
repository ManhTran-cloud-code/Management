package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.models.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem saveOrderItem(OrderItem orderItem);
    List<OrderItem> findOrderItemByOrderId(int orderId);
}
