package org.fve.springbootprojects.springbootstarterapp.modules.dummydata.service;

import lombok.extern.slf4j.Slf4j;
import org.fve.springbootprojects.springbootstarterapp.modules.auth.service.IRoleService;
import org.fve.springbootprojects.springbootstarterapp.security.enums.RoleEnum;
import org.fve.springbootprojects.springbootstarterapp.security.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DummyDataService implements CommandLineRunner {

    @Autowired
    private IRoleService roleService;

//    @Autowired
//    private IUserService userService;

    @Override
    public void run(String... args) throws Exception {
        if (roleService.count() == 0) {
            log.info("Creating roles...");
            createRoles();
            log.info("Roles created.");
        }

//        if (userService.count() == 0) {
//            log.info("Creating users...");
//            createUsers();
//            log.info("Users created.");
//        }
    }

    /**
     * Create roles.
     */
    private void createRoles() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(Role.builder().name(RoleEnum.ADMIN.name()).build());
        roleList.add(Role.builder().name(RoleEnum.USER.name()).build());

        roleService.saveList(roleList);
    }
}
