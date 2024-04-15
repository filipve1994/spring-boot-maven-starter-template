package org.fve.springbootprojects.springbootstarterapp.security.repositories;

import org.fve.springbootprojects.springbootstarterapp.security.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByName(String name);

}
