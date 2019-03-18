package koks.demo.service;

import koks.demo.repository.RoleRepositoryImpl;
import koks.demo.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService extends UserServiceImpl implements UserDetailsService  {

    @Autowired
    UserRepositoryImpl userRepository;

    @Autowired
    RoleRepositoryImpl roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        koks.demo.model.User user = userRepository.getUser(username);

        List<GrantedAuthority> grantList = new ArrayList<>();
        for (String role : roleRepository.getRolesById(user.getId())) grantList.add(new SimpleGrantedAuthority(role));

        return new User(user.getUsername(), user.getPassword(), grantList);
    }
}
