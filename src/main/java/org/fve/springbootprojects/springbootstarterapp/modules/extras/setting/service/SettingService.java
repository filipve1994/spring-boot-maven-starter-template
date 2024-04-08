package org.fve.springbootprojects.springbootstarterapp.modules.extras.setting.service;

import lombok.extern.slf4j.Slf4j;
import org.fve.springbootprojects.springbootstarterapp.modules.extras.setting.entity.Setting;
import org.fve.springbootprojects.springbootstarterapp.modules.extras.setting.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SettingService {

    @Autowired
    private SettingRepository settingRepository;

    public Setting create() {
        return settingRepository.save(Setting.builder().key("foo").value("bar").build());
    }
}