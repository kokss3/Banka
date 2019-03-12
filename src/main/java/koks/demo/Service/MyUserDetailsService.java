package koks.demo.Service;

import koks.demo.Repository.RoleRepository;
import koks.demo.Repository.UserRepositoryImpl;
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
    UserRepositoryImpl repository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        int id = repository.getUser(username).getId();
        List<GrantedAuthority> grantList = new ArrayList<>();
        for (String role : roleRepository.getRolesById(id)) {

            // ROLE_USER, ROLE_ADMIN,..
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantList.add(authority);
        }

        UserDetails userDetails = new User(repository.getUser(id).getUsername(), //
                repository.getUser(id).getPassword(), grantList);
        return userDetails;
    }



}
