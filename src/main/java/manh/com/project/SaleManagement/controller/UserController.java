package manh.com.project.SaleManagement.controller;

import manh.com.project.SaleManagement.exceptions.PhoneNotFoundException;
import manh.com.project.SaleManagement.models.Authority;
import manh.com.project.SaleManagement.models.User;
import manh.com.project.SaleManagement.models.UserDetail;
import manh.com.project.SaleManagement.services.AuthorityService;
import manh.com.project.SaleManagement.services.UserDetailService;
import manh.com.project.SaleManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;

    @GetMapping("/userDetail")
    public ResponseEntity<UserDetail> getUserDetailByUser(Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        if(userDetailService.findUserDetailByUser(user)==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/userDetail/{id}")
    public String getPhoneById(@PathVariable int id) throws PhoneNotFoundException {
        return userDetailService.findPhoneByUserId(id);
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<UserDetail> updateProfile(@RequestBody UserDetail userDetail, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        userDetail.setUserId(user.getId());
        if(userDetailService.saveUserDetail(userDetail)==null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user) {

        if(userService.addUser(user)==null) {
            return ResponseEntity.badRequest().build();
        }
        Authority authority = new Authority(user.getEmail(),"USER");
        authorityService.saveAuthority(authority);
        return ResponseEntity.ok().build();
    }
}
