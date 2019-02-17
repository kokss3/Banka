package koks.demo.Service;

import koks.demo.Configuration.AuthUserPrincipal;
import koks.demo.Model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    JdbcTemplate template;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //neda mi se raditi inicijalizacije
        return new AuthUserPrincipal(new AuthUser(
                //get username
                template.queryForObject("select username from auth_user where username=?"
                        , new Object[] {username}, String.class),
                //get password
                template.queryForObject("select password from auth_user where username=?"
                        , new Object[] {username}, String.class)
            )
        );
    }
}
