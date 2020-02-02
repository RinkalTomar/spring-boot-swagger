package com.java.dao;

import com.java.entities.AppConfig;
import com.java.enums.Enumeration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppConfigRepository extends JpaRepository<AppConfig, Long> {


    @Query("SELECT ac.configValue FROM AppConfig ac WHERE configKey = ?1")
    String getConfigValue(Enumeration.ConfigKeys keys) throws Exception;
}
