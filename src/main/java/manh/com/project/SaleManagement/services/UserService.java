package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.models.User;

public interface UserService {
    User findUserByEmail(String email);
    User addUser(User user);
}
