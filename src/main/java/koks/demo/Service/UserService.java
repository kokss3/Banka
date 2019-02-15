package koks.demo.Service;

import koks.demo.Model.User;
import koks.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Repository
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> getIban(String name){
        return repository.findByNameOrderByFundsAsc(name);
    }

    //removes multiple entries, and saves new entry, or updates old
    public void saveInDB(User user){
        List<User> temp = repository.findByNameAndIban(user.getName(),user.getIban());
        repository.deleteInBatch(temp);
        repository.save(fixMultipleEntries(user,temp));
    }

    //if there are multiple entries it takes funds and saves it in current user
    public User fixMultipleEntries(User currentUser,List<User> multiple){
        Integer funds =0;
        for(User temp:multiple)
            funds += temp.getFunds();

        currentUser.setFunds(currentUser.getFunds()+funds);

        return currentUser;
    }


}
