package org.fve.springbootprojects.springbootstarterapp.modules.auth.service;

import org.fve.springbootprojects.springbootstarterapp.security.models.Role;

import java.util.List;

public interface IRoleService {
    long count();

    //    public Role findByName(final Constants.RoleEnum name) {
    Role findByName(String name);

    Role create(Role role);

    List<Role> saveList(List<Role> roleList);
}
