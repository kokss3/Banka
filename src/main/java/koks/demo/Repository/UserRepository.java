package koks.demo.Repository;

import koks.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findNameByIban(String iban);
    List<User> findByName(String namePerson);
    List<User> findFundsByIban(String iban);
}
