package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.exceptions.ObjectNotFoundException;
import manh.com.project.SaleManagement.models.Order;

import java.util.List;

public interface OrderService {
    public int addOrder(Order order) throws ObjectNotFoundException;
   /* public Order updateOrder(Order order);*/
    List<Order> findOrderByStatus(int status);
    int updateStatus(int status,int orderId);
}
