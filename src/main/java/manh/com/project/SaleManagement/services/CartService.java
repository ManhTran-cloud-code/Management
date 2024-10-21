package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.models.Cart;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.security.Principal;
import java.util.List;

public interface CartService {
    List<Cart> findCartByUSerId(int userId);
    Cart addCart(Cart cart,Principal principal);
    int deleteCart(int cartId);
    Cart findCartById(int cartId);
}
