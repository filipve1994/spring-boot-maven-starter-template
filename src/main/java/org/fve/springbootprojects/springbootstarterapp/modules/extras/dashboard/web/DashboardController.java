package org.fve.springbootprojects.springbootstarterapp.modules.extras.dashboard.web;

import org.fve.springbootprojects.springbootstarterapp.modules.globalapp.web.AbstractBaseController;
import org.fve.springbootprojects.springbootstarterapp.modules.internationalization.service.MessageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController extends AbstractBaseController {

    @Autowired
    private MessageSourceService messageSourceService;

    @GetMapping
    public ResponseEntity<String> dashboard() {
        return ResponseEntity.ok(messageSourceService.get("hi"));
    }
}