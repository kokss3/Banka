package koks.demo.interfaces.services;

import koks.demo.model.User;

import java.util.List;

public interface RoleService {

    void setRoles(Integer id, Integer roleNumber);
    List<String> getRolesById(Integer id);
    void removeRole(User user, String role);
}
