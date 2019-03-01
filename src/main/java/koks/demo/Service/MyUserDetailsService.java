package koks.demo.Service;

import koks.demo.Configuration.AuthUserPrincipal;
import koks.demo.Model.User;
import koks.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        int id = repository.getIdformLoggedUser(username);

        return new AuthUserPrincipal(
                new User(repository.getUsernameById(id),repository.getPasswordById(id))
        );
    }
}
