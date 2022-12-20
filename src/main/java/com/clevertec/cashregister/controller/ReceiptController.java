package com.clevertec.cashregister.controller;

import com.clevertec.cashregister.dto.ReceiptDto;
import com.clevertec.cashregister.dto.page.PageContentDto;
import com.clevertec.cashregister.service.ReceiptService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    @ApiOperation(value = "Поиск объекта", notes = "Один объект", response = ReceiptDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный результат", response = ReceiptDto.class),
            @ApiResponse(code = 400, message = "Параметры неверны или отсутствуют"),
            @ApiResponse(code = 401, message = "Проблема авторизации"),
            @ApiResponse(code = 403, message = "Обращение запрещено"),
            @ApiResponse(code = 404, message = "Объект не обнаружен")
    })
    @GetMapping
    public ResponseEntity<ReceiptDto> findById(@RequestParam Long id) {
        return new ResponseEntity<>(receiptService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Поиск объектов", notes = "Список", response = PageContentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный результат", response = PageContentDto.class),
            @ApiResponse(code = 400, message = "Параметры неверны или отсутствуют"),
            @ApiResponse(code = 401, message = "Проблема авторизации"),
            @ApiResponse(code = 403, message = "Обращение запрещено"),
            @ApiResponse(code = 404, message = "Объект не обнаружен")
    })
    @GetMapping("/page")
    public ResponseEntity<PageContentDto<ReceiptDto>> findAll(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                                              @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(receiptService.findAll(pageNumber, pageSize), HttpStatus.OK);
    }

    @ApiOperation(value = "Сохранение объекта", notes = "Один объект", response = ReceiptDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный результат", response = ReceiptDto.class),
            @ApiResponse(code = 400, message = "Параметры неверны или отсутствуют"),
            @ApiResponse(code = 401, message = "Проблема авторизации"),
            @ApiResponse(code = 403, message = "Обращение запрещено"),
            @ApiResponse(code = 404, message = "Объект не обнаружен")
    })
    @PostMapping
    public ResponseEntity<ReceiptDto> create(@RequestParam(name = "card", required = false) Long card,
                                             @RequestParam Long[] barcode) {
        return new ResponseEntity<>(receiptService.create(card, barcode), HttpStatus.OK);
    }
}
