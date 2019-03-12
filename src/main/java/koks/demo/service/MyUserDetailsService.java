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
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepositoryImpl userRepository;

    @Autowired
    RoleRepositoryImpl roleRepositoryImpl;

    private Integer getUserId(String username){
        return userRepository.getUser(username).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        int id = userRepository.getUser(username).getId();
        List<GrantedAuthority> grantList = new ArrayList<>();
        for (String role : roleRepositoryImpl.getRolesById(id)) {
            System.out.println(role);

            // ROLE_USER, ROLE_ADMIN,..
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantList.add(authority);
        }

        UserDetails userDetails = new User(userRepository.getUser(id).getUsername(), //
                userRepository.getUser(id).getPassword(), grantList);
        return userDetails;
    }



}
