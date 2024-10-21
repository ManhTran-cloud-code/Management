package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.exceptions.CartNotFoundException;
import manh.com.project.SaleManagement.models.Cart;
import manh.com.project.SaleManagement.models.User;
import manh.com.project.SaleManagement.repositories.CartRepository;
import manh.com.project.SaleManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Cart> findCartByUSerId(int userId) {
        return cartRepository.getCartByUserId(userId);
    }

    @Override
    public Cart addCart(Cart cart,Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        if (cartRepository.addCart(cart, user.getId())==0){
            throw new CartNotFoundException();
        }
        return cart;
    }

    @Override
    public int deleteCart(int cartId) {
        return cartRepository.deleteCart(cartId);
    }

    @Override
    public Cart findCartById(int cartId) {
        return cartRepository.findCartById(cartId);
    }
}
