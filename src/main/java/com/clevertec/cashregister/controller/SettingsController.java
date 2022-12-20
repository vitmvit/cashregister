package com.clevertec.cashregister.controller;

import com.clevertec.cashregister.service.SettingsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    private final SettingsService settingsService;

    @ApiOperation(value = "Получение объекта", notes = "Один объект", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный результат", response = Map.class),
            @ApiResponse(code = 400, message = "Параметры неверны или отсутствуют"),
            @ApiResponse(code = 401, message = "Проблема авторизации"),
            @ApiResponse(code = 403, message = "Обращение запрещено"),
            @ApiResponse(code = 404, message = "Объект не обнаружен")
    })
    @GetMapping
    public ResponseEntity<Map<String, String>> getSettings() {
        return new ResponseEntity<>(settingsService.getSettings(), HttpStatus.OK);
    }

    @ApiOperation(value = "Сохранение объекта", notes = "Смотреть код ответа")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный результат"),
            @ApiResponse(code = 400, message = "Параметры неверны или отсутствуют"),
            @ApiResponse(code = 401, message = "Проблема авторизации"),
            @ApiResponse(code = 403, message = "Обращение запрещено"),
            @ApiResponse(code = 404, message = "Объект не обнаружен")
    })
    @PutMapping
    public void addSettings(@RequestParam String key, @RequestParam String value) {
        settingsService.addSettings(key, value);
    }

    @ApiOperation(value = "Удаление объекта", notes = "Смотреть код ответа")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный результат"),
            @ApiResponse(code = 400, message = "Параметры неверны или отсутствуют"),
            @ApiResponse(code = 401, message = "Проблема авторизации"),
            @ApiResponse(code = 403, message = "Обращение запрещено"),
            @ApiResponse(code = 404, message = "Объект не обнаружен")
    })
    @DeleteMapping
    public void removeByKey(@RequestParam String key) {
        settingsService.removeByKey(key);
    }
}
