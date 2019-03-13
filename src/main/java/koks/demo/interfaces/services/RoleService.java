package koks.demo.interfaces.services;

import java.util.List;

public interface RoleService {

    void setRoles(Integer id, Integer roleNumber);
    List<String> getRolesById(Integer id);
}
