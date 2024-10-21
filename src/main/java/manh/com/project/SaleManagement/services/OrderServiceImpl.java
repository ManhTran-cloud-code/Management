package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.models.Order;
import manh.com.project.SaleManagement.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public int addOrder(Order order)  {
        return orderRepository.saveOrder(order);
    }


    //Tim tat ca cac don hang theo trang thai
    @Override
    public List<Order> findOrderByStatus(int status) {
        return orderRepository.findOrderByStatus(status);
    }

    @Override
    public int updateStatus(int status, int orderId) {
        return orderRepository.updateStatus(status,orderId);
    }


  /*  @Override
    public Order updateOrder(Order order) {
        return (orderRepository.updateOrder(order)==0)?null:order;
    }*/
}
