package org.fve.springbootprojects.springbootstarterapp.modules.extras.setting.service;

import lombok.extern.slf4j.Slf4j;
import org.fve.springbootprojects.springbootstarterapp.modules.extras.setting.entity.Setting;
import org.fve.springbootprojects.springbootstarterapp.modules.extras.setting.repository.SettingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    private static final Logger log = LoggerFactory.getLogger(SettingService.class);


    @Autowired
    private SettingRepository settingRepository;

    public Setting create() {
        return settingRepository.save(Setting.builder().key("foo").value("bar").build());
    }
}