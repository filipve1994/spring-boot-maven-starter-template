package org.fve.springbootprojects.springbootstarterapp.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/roleuser")
    public ResponseEntity testRightsRoleUser() {
        return ResponseEntity.ok("user can see this as ROLE_USER");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/roleadmin")
    public ResponseEntity testRightsRoleAdmin() {
        return ResponseEntity.ok("user can see this as ROLE_ADMIN");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "/anyrole")
    public ResponseEntity testRightsAnyRole(){
        return ResponseEntity.ok("user can see this as ROLE_ADMIN or ROLE_USER");
    }
}
