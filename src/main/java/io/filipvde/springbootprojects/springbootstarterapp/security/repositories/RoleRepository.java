package org.fve.springbootprojects.springbootstarterapp.security.repositories;

import org.fve.springbootprojects.springbootstarterapp.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
