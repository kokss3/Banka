package koks.demo.Service;

import koks.demo.Repository.UserRepository;
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
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        int id = repository.getIdformLoggedUser(username);
        List<GrantedAuthority> grantList = new ArrayList<>();
        for (String role : repository.getRolesById(id)) {
            // ROLE_USER, ROLE_ADMIN,..
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantList.add(authority);
        }

        UserDetails userDetails = (UserDetails) new User(repository.getUsernameById(id), //
                repository.getPasswordById(id), grantList);
        return userDetails;
    }
}
