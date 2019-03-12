package koks.demo.interfaces.repos;

import java.util.List;

public interface RoleRepository {

    Integer ROLE_ADMINISTRATOR = 1;
    Integer ROLE_USER = 2;

    List<String> getRolesById(int id);

    void setRoles(Integer id, Integer roleNumber);
}
