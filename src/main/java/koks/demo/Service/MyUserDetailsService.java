package koks.demo.Service;

import koks.demo.Configuration.AuthUserPrincipal;
import koks.demo.Model.AuthUser;
import koks.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
                    repository.queryForUsername(username),
                    repository.queryForPassword(username)
                )
        );
    }
}
