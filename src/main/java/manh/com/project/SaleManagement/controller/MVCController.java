package manh.com.project.SaleManagement.controller;

import manh.com.project.SaleManagement.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MVCController {

    @Autowired
    private ProductService productService;

    @GetMapping("category/add")
    public String getHomePage(){
        return "add-category";
    }

    @GetMapping("/product/add")
    public String getProductPage(){
        return "add-product";
    }

    @GetMapping("/product/getAll")
    public String getAllProductPage(){
        return "list-product";
    }

    @GetMapping("/product/{id}")
    public String getProductPage(@PathVariable int id, Model model){;
        model.addAttribute("product",productService.findById(id));
        return "update-product";
    }

    @GetMapping("/user/login")
    public String login(){
        return "login";
    }

    @GetMapping("/index")
    public String indexPage(){
        return "index";
    }
    @GetMapping("/product/{id}/{productName}")
    public String getProductPage(@PathVariable int id, @PathVariable String productName, Model model){
        model.addAttribute("product",productService.findById(id));
        return "product-detail";
    }

    @GetMapping("/cart")
    public String getCartPage(){
        return "cart";
    }
    @GetMapping("/profile")
    public String getProfilePage(){
        return "user-profile";
    }
    @GetMapping("/register")
    public String getRegisterPage(){
        return "register";
    }

    @GetMapping("admin/order")
    public String getOrderPage(){
        return "order-detail";
    }
    @GetMapping("/sale")
    public String getSalePage(){
        return "sale";
    }

    @GetMapping("/addDiscount")
    public String getDiscountPage(){
        return "add-discount";
    }
}
