package org.fve.springbootprojects.springbootstarterapp.modules.extras.setting.repository;

import org.fve.springbootprojects.springbootstarterapp.modules.extras.setting.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface SettingRepository extends JpaRepository<Setting, UUID> {
public interface SettingRepository extends JpaRepository<Setting, Long> {
}