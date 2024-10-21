package manh.com.project.SaleManagement.controller;

import manh.com.project.SaleManagement.exceptions.ObjectNotFoundException;
import manh.com.project.SaleManagement.exceptions.PhoneNotFoundException;
import manh.com.project.SaleManagement.models.Cart;
import manh.com.project.SaleManagement.models.Order;
import manh.com.project.SaleManagement.models.OrderItem;
import manh.com.project.SaleManagement.models.User;
import manh.com.project.SaleManagement.services.*;
import manh.com.project.SaleManagement.utis.CurrentDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Order> saveOrder(@RequestParam("totalMoney") Long totalMoney, @RequestParam("card_id") List<Integer> listId, Principal principal) throws ObjectNotFoundException, PhoneNotFoundException {
        User user = userService.findUserByEmail(principal.getName());
        userDetailService.findPhoneByUserId(user.getId());
        Order order = new Order();
        order.setUserId(user.getId());
        order.setOrderDate(new CurrentDate().getCurrentDate());
        order.setTotalMoney(totalMoney);
        order.setOrderStatus(1);
        int keyHolder = orderService.addOrder(order);
        if(keyHolder==0) {
            return ResponseEntity.badRequest().build();
        }
        for(Integer id : listId) {
            Cart cart = cartService.findCartById(id);
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cart.getProduct());
            orderItem.setOrderId(keyHolder);
            orderItem.setQuantity(cart.getQuantity());
            if(orderItemService.saveOrderItem(orderItem)==null){
                return ResponseEntity.badRequest().build();
            }
            cartService.deleteCart(id);
            productService.updateQuantityProduct(cart.getQuantity(),cart.getProduct().getId());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status/{status}")
        public List<Order> getOrderByStatus(@PathVariable("status") int status){
        return orderService.findOrderByStatus(status);
        }

    @GetMapping("/detail/{orderId}")
    public List<OrderItem> getOrderItem(@PathVariable("orderId") int orderId){
        return orderItemService.findOrderItemByOrderId(orderId);
    }

    @GetMapping("/updateStatus/{status}/{orderId}")
    public ResponseEntity<Order>  updateStatus(@PathVariable("status") int status, @PathVariable("orderId") int orderId){
        if((status>=1)&&(status<=3)){
            status = status+1;
        }
        if(orderService.updateStatus(status,orderId)==0){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
