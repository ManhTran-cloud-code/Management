package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.exceptions.PhoneNotFoundException;
import manh.com.project.SaleManagement.models.User;
import manh.com.project.SaleManagement.models.UserDetail;
import manh.com.project.SaleManagement.repositories.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private UserService userService;
    
    @Override
    public UserDetail findUserDetailByUser(User user) {
        if(userDetailRepository.findUserDetailByUserId(user.getId())==null){
           return null;
        }
        return userDetailRepository.findUserDetailByUserId(user.getId());
    }

    @Override
    public String findPhoneByUserId(int userId) throws PhoneNotFoundException {
        if(userDetailRepository.getPhoneByUserId(userId).equals("[]")){
            throw new PhoneNotFoundException("Cant found");
        }
        return userDetailRepository.getPhoneByUserId(userId);
    }

    @Override
    public UserDetail saveUserDetail(UserDetail userDetail) {
        return (userDetailRepository.saveUserDetail(userDetail)==0)?null:userDetail;
    }
}
