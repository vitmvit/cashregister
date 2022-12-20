package com.clevertec.cashregister.controller;

import com.clevertec.cashregister.dto.CardDto;
import com.clevertec.cashregister.service.CardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    @ApiOperation(value = "Поиск объекта", notes = "Один объект", response = CardDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный результат", response = CardDto.class),
            @ApiResponse(code = 400, message = "Параметры неверны или отсутствуют"),
            @ApiResponse(code = 401, message = "Проблема авторизации"),
            @ApiResponse(code = 403, message = "Обращение запрещено"),
            @ApiResponse(code = 404, message = "Объект не обнаружен")
    })
    @GetMapping
    public ResponseEntity<CardDto> findByNumber(@RequestParam Long number) {
        return new ResponseEntity<>(cardService.findById(number), HttpStatus.OK);
    }

    @ApiOperation(value = "Создание объекта", notes = "Один объект", response = CardDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный результат", response = CardDto.class),
            @ApiResponse(code = 400, message = "Параметры неверны или отсутствуют"),
            @ApiResponse(code = 401, message = "Проблема авторизации"),
            @ApiResponse(code = 403, message = "Обращение запрещено"),
            @ApiResponse(code = 404, message = "Объект не обнаружен")
    })
    @PostMapping
    public ResponseEntity<CardDto> save(@RequestParam(name = "cardType", defaultValue = "C") String cardType) {
        return new ResponseEntity<>(cardService.create(cardType), HttpStatus.OK);
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
    public void remove(@RequestParam Long number) {
        cardService.remove(number);
    }
}
