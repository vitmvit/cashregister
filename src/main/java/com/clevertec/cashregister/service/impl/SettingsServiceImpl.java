package com.clevertec.cashregister.service.impl;

import com.clevertec.cashregister.entity.Settings;
import com.clevertec.cashregister.repository.SettingsRepository;
import com.clevertec.cashregister.service.SettingsService;
import com.clevertec.cashregister.pattern.singleton.SettingsSingleton;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@AllArgsConstructor
@Service
public class SettingsServiceImpl implements SettingsService {

    private final SettingsRepository settingsRepository;

    @Override
    public Map<String, String> getSettings() {
        SettingsSingleton settingsSingleton = SettingsSingleton.getInstance(null);
        if (settingsSingleton == null || settingsSingleton.getSettings().size() == 0) {
            SettingsSingleton.getInstance(settingsRepository.getSettings());
        }
        return SettingsSingleton.getInstance(null).getSettings();
    }

    @Override
    @Transactional
    public void addSettings(String key, String value) {
        Settings settings = settingsRepository.findByKey(key).orElse(null);
        if (settings == null) {
            settingsRepository.save(new Settings(key, value));
        } else {
            settings.setValue(value);
            settingsRepository.save(settings);
        }
        SettingsSingleton.getInstance(settingsRepository.getSettings());
    }

    @Override
    @Transactional
    public void removeByKey(String key) {
        settingsRepository.deleteByKey(key);
        SettingsSingleton.getInstance(settingsRepository.getSettings());
    }
}
