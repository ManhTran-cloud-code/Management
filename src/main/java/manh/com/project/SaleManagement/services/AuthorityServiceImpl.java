package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.models.Authority;
import manh.com.project.SaleManagement.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;
    @Override
    public int saveAuthority(Authority authority) {
        return authorityRepository.saveAuthority(authority);
    }
}
