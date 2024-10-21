package manh.com.project.SaleManagement.controller;

import manh.com.project.SaleManagement.models.Cart;
import manh.com.project.SaleManagement.models.Product;
import manh.com.project.SaleManagement.models.User;
import manh.com.project.SaleManagement.repositories.CartRepository;
import manh.com.project.SaleManagement.services.CartService;
import manh.com.project.SaleManagement.services.ProductService;
import manh.com.project.SaleManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/getAll")
    public List<Cart> findCartByUser(Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        return cartService.findCartByUSerId(user.getId());
    }


    @PostMapping("/addCart")
    public ResponseEntity<Cart> addCart(@RequestParam("quantity") int quantity,@RequestParam("product_Id") int productId,Principal principal) {
       Product product = productService.findProductById(productId);
       Cart cart = new Cart();
       cart.setProduct(product);
       cart.setQuantity(quantity);
        if(cartService.addCart(cart,principal)==null){
           return ResponseEntity.badRequest().build();
       }
       return ResponseEntity.ok(cart);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Cart> deleteCart(@PathVariable int id) {
        if(cartService.deleteCart(id)==0){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }


}
