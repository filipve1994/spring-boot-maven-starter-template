package org.fve.springbootprojects.springbootstarterapp.modules.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.fve.springbootprojects.springbootstarterapp.modules.globalapp.exception.NotFoundException;
import org.fve.springbootprojects.springbootstarterapp.modules.internationalization.service.MessageSourceService;
import org.fve.springbootprojects.springbootstarterapp.security.models.Role;
import org.fve.springbootprojects.springbootstarterapp.security.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MessageSourceService messageSourceService;

    /**
     * Count roles.
     *
     * @return Long
     */
    @Override
    public long count() {
        return roleRepository.count();
    }

    /**
     * Find by role name.
     *
     * @param name Constants.RoleEnum
     * @return Role
     */
    @Override
//    public Role findByName(final Constants.RoleEnum name) {
    public Role findByName(final String name) {
        return roleRepository.findByName(name)
//                .orElseThrow(() -> new NotFoundException(messageSourceService.get("role_not_found")))
                ;
    }

    /**
     * Create role.
     *
     * @param role Role
     * @return Role
     */
    @Override
    public Role create(final Role role) {
        return roleRepository.save(role);
    }

    /**
     * Save list roles.
     *
     * @param roleList List
     * @return List
     */
    @Override
    public List<Role> saveList(List<Role> roleList) {
        return roleRepository.saveAll(roleList);
    }
}
