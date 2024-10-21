package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.models.OrderItem;
import manh.com.project.SaleManagement.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
@Autowired
private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return (orderItemRepository.saveOrderItem(orderItem)==0)?null:orderItem;
    }

    @Override
    public List<OrderItem> findOrderItemByOrderId(int orderId) {
        return orderItemRepository.findOrderItemByOrderId(orderId);
    }
}
