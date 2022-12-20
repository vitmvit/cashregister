package com.clevertec.cashregister.service;

import java.util.Map;

public interface SettingsService {

    Map<String, String> getSettings();

    void addSettings(String key, String value);

    void removeByKey(String key);
}
