package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.models.Authority;
import manh.com.project.SaleManagement.models.CustomUserDetail;
import manh.com.project.SaleManagement.models.User;
import manh.com.project.SaleManagement.repositories.AuthorityRepository;
import manh.com.project.SaleManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException(email);
        }
        List<Authority> authorities = authorityRepository.findAuthorityByUserEmail(email);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
       for(Authority authority:authorities){
           GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthorityName());
           grantedAuthorities.add(grantedAuthority);

       }
        return new CustomUserDetail(email,user.getEncryptPassword(),grantedAuthorities);
    }
}
