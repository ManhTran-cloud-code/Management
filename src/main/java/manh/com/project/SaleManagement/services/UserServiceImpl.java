package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.models.User;
import manh.com.project.SaleManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User addUser(User user) {
        user.setEncryptPassword(new BCryptPasswordEncoder().encode(user.getEncryptPassword()));
        return (userRepository.saveUser(user)==0)?null:user;
    }
}
