package koks.demo.Service;

import koks.demo.Configuration.AuthUserPrincipal;
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
        return new AuthUserPrincipal(
                new AuthUser(
                    repository.createUser(1).getUsername(),repository.createUser(1).getCredentials().get(0).getPassword()

                )
        );
    }
}
