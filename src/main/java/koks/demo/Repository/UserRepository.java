package koks.demo.Repository;

import koks.demo.Model.Account;
import koks.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate template;

    //TODO Obavezno treba razdvojiti repozitorije za usera, accounte i role. Ne smiju biti pomiješani
    /*
    Ako te zbunjuje kako ćeš onda dobiti Usera s rolama i accountima, to ćeš raditi u servisu - prvo ćeš dobiti osnovne podatke korisnika
    i onda ako ti trebaju accounti od njega otići ćeš u AccountRepository i pozvati metodu findAllByUserId(userId)
    ako ti trebaju role otići ćeš u RoleRepository.... itd...
     */

    ///OVERLOADING METHODS
    //getUser by account-iban
    public User getUser(Account acc) {
        String queryForUserId = "select user_id from user_accounts where iban=?;";
        return getUser(template.queryForObject(queryForUserId, new Object[]{acc.getIban()}, Integer.class));
    }

    //getUser by username
    //TODO - zašto ne vratiti odmah cijelog usera?
    public User getUser(String username) {
        String queryForId = "select id from auth_user where username=?;";
        return getUser(template.queryForObject(queryForId, new Object[]{username}, Integer.class));
    }

    //getUser by id
    //TODO Kako sam rekao gore, vrati samo usera i onda traži dalje i ne treba za svaki field raditi poseban query
    // Trebalo bi biti samo "select * from users where user_id = id" i to je to
    public User getUser(int id){
        User user = new User();

        //declare queries
        String queryForAccounts = "select user_accounts.id, user_accounts.iban, user_accounts.funds, " +
                                    "user_accounts.real_name from user_accounts inner join auth_user " +
                                    "on user_accounts.user_id = auth_user.id where auth_user.id=?;";
        String queryForUserName = "select auth_user.username from auth_user where id=?;";
        String queryForPassword= "select auth_user.password from auth_user where id=?;";
        String queryForUserRoles = "select role_user.role from role_user inner join auth_user " +
                                    "on role_user.user_id = auth_user.id where auth_user.id=?;";

        //get id
        user.setId(id);

        //get list of accounts
        user.setAccounts(template.query(queryForAccounts,new Object[]{ id }, (rs, rowNum) ->
                new Account(rs.getInt("id"),rs.getString("real_name"),
                        rs.getString("iban"), rs.getDouble("funds"))));

        //get roles
        user.setRoles(template.query(queryForUserRoles, new Object[]{ id },
                (rs, rowNum) -> rs.getString("role")));

        //get username and password
        user.setUsername(template.queryForObject(queryForUserName, new Object[]{id}, String.class));
        user.setPassword(template.queryForObject(queryForPassword, new Object[]{id}, String.class));

        return user;
    }

    //get all users in database
    //TODO ne treba ovdje za svaki id posebno zvati userById.. može se napraviti da result vraća odmah listu svih podataka od usera -> queryForList("select * from users") i to je to
    public List<User> findAll() {
        List<Integer> ids = new ArrayList<>();
        List<User> users = new ArrayList<>();

        ids.addAll(template.query("select id from auth_user;",
                (rs, rowNum) -> rs.getInt("id")));
        for(Integer id: ids) {
            users.add(getUser(id));
        }
        return users;
    }

    //save new User to DB
    //TODO ovdje bi trebao spremati samo korisnika... dodavanje accounta i rola moraju biti posebno
    public void saveUserToDB(User user, int status){
        String account = "INSERT into user_accounts (user_id, iban, funds, real_name) values (?,?,?,?);";
        String userRoles = "INSERT into role_user (user_id, role) values (?,?);";
        String userCreds = "INSERT into user_accounts (id, username, password) values (?,?,?);";
        Account acc = user.getAccounts().get(0);
        switch (status){
            case 1: //save username password
                template.update(userCreds, user.getId(), user.getUsername(), user.getPassword());
                break;

            case 2: //save role
                for (String roles:user.getRoles()) template.update(userRoles, user.getId(), roles);
                break;

            case 3: //save account
                template.update(account, user.getId(), acc.getIban(), acc.getFunds(), acc.getRealName());
                break;

            default: //save whole user
                template.update(userCreds, user.getId(), user.getUsername(), user.getPassword());
                for (String roles:user.getRoles()) template.update(userRoles, user.getId(), roles);
                template.update(account, user.getId(), acc.getIban(), acc.getFunds(), acc.getRealName());
        }
    }

    //update funds by account, by iban
    public void updateFundsByIban(Account acc){
        String updateString = "update user_accounts set funds = funds + ? where user_accounts.iban=?";
        template.update(updateString, acc.getFunds(), acc.getIban());
    }

    //update funds by account by Holder name
    public void updateFundsByRealName(Account acc){
        String updateString = "update user_accounts set funds = funds + ? where user_accounts.real_name=?";
        template.update(updateString, acc.getFunds(), acc.getRealName());
    }
}
