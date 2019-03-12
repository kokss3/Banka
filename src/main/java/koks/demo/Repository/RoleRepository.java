package koks.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository {

    @Autowired
    JdbcTemplate template;

    public List<String> getRolesById(int id){
        String queryForUserRoles = "select role_user.role from role_user inner join auth_user " +
                "on role_user.user_id = auth_user.id where auth_user.id=?;";

        return template.query(queryForUserRoles, new Object[]{ id },
                (rs, rowNum) -> rs.getString("role"));
    }
}
