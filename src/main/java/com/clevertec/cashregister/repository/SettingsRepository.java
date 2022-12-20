package com.clevertec.cashregister.repository;

import com.clevertec.cashregister.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {

    Optional<Settings> findByKey(String key);

    default Map<String, String> getSettings() {
        List<Settings> list = findAll();
        return list
                .stream()
                .collect(
                        Collectors
                                .toMap(
                                        Settings::getKey,
                                        Settings::getValue,
                                        (k, v) -> v, () -> new HashMap<>(list.size())
                                )
                );
    }

    void deleteByKey(String key);
}
