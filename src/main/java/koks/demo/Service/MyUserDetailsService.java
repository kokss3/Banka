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

    //TODO Ovdje prvo zoveš getUser(username) što vraća samo ID od usera
    /*
    Prvo - treba paziti na imena metoda, ako tražiš usera u UserRepositoriju po username-u metoda se treba zvati findByUsername(username)
    i trebala bi vraćati cijelog usera da ne moraš još kasnije zvati ponovno nešto
    Pogledaj komentar u data.sql -> nije ti dobra struktura baze tj. relacija između rola i usera
    Ako želiš pronaći role od korisnika, to bi se trebalo raditi u repozitoriju od rola (svaka tablica bi trebala imati svoj repozitorij)
    znači imat ćeš RoleRepository u kojem je metoda findAllByUserId(userId) koja vraća listu rola od usera
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        int id = repository.getUser(username).getId();
        List<GrantedAuthority> grantList = new ArrayList<>();
        for (String role : repository.getUser(id).getRoles()) {

            // ROLE_USER, ROLE_ADMIN,..
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantList.add(authority);
        }

        UserDetails userDetails = new User(repository.getUser(id).getUsername(), //
                repository.getUser(id).getPassword(), grantList);
        return userDetails;
    }



}
