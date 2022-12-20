package com.clevertec.cashregister.pattern.singleton;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * хранит мапу основных конфигов, чтобы не дергать БД лишний раз
 */
public class SettingsSingleton {

    private static SettingsSingleton instance;

    @Getter
    private final Map<String, String> settings;

    private SettingsSingleton(Map<String, String> settings) {
        this.settings = settings;
    }

    public static SettingsSingleton getInstance(Map<String, String> settings) {
        if (instance == null) {
            instance = new SettingsSingleton(new HashMap<>(0));
        }
        if (settings != null) {
            instance = new SettingsSingleton(settings);
        }
        return instance;
    }
}
