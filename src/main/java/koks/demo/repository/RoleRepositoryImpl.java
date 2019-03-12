package koks.demo.repository;

import koks.demo.interfaces.repos.RoleRepository;
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
        "inner join auth_user au on ur.user_id = au.id " +
        "where au.id=?;";

        return template.query(queryForUserRoles, new Object[]{ id },
                (rs, rowNum) -> rs.getString("roles"));
    }
}
