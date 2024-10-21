package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.exceptions.PhoneNotFoundException;
import manh.com.project.SaleManagement.models.User;
import manh.com.project.SaleManagement.models.UserDetail;

public interface UserDetailService {
    UserDetail findUserDetailByUser(User user);
    String findPhoneByUserId(int userId) throws PhoneNotFoundException;
    UserDetail saveUserDetail(UserDetail userDetail);
}
