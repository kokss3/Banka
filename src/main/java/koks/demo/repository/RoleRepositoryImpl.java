package koks.demo.repository;

import koks.demo.interfaces.repos.RoleRepository;
import koks.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<String> getRolesById(int id){
        String queryForUserRoles = "select roles from role " +
        "inner join user_roles ur on role.id = ur.role_id " +
        "inner join user_auth au on ur.user_id = au.id " +
        "where au.id=?;";

        return template.query(queryForUserRoles, new Object[]{ id },
                (rs, rowNum) -> rs.getString("roles"));
    }

    @Override
    public void setRoles(Integer id, Integer roleNumber){
        String saveCommand = "insert into user_roles (user_id, role_id) value(?,?);";
        template.update(saveCommand,id, roleNumber);
    }

    @Override
    public void removeRole(User user) {
        String removeCommand = "delete from user_roles where user_id=?;";
        template.update(removeCommand,user.getId());
    }
}
