package com.clevertec.cashregister.controller;

import com.clevertec.cashregister.dto.ProductDto;
import com.clevertec.cashregister.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @ApiOperation(value = "Поиск объекта", notes = "Один объект", response = ProductDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный результат", response = ProductDto.class),
            @ApiResponse(code = 400, message = "Параметры неверны или отсутствуют"),
            @ApiResponse(code = 401, message = "Проблема авторизации"),
            @ApiResponse(code = 403, message = "Обращение запрещено"),
            @ApiResponse(code = 404, message = "Объект не обнаружен")
    })
    @GetMapping
    public ResponseEntity<ProductDto> findById(@RequestParam Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Поиск объектов", notes = "Список", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный результат", response = List.class),
            @ApiResponse(code = 400, message = "Параметры неверны или отсутствуют"),
            @ApiResponse(code = 401, message = "Проблема авторизации"),
            @ApiResponse(code = 403, message = "Обращение запрещено"),
            @ApiResponse(code = 404, message = "Объект не обнаружен")
    })
    @GetMapping("/description")
    public ResponseEntity<List<ProductDto>> findByAllByDescription(@RequestParam String description) {
        return new ResponseEntity<>(productService.findAllByDescription(description), HttpStatus.OK);
    }

    @ApiOperation(value = "Сохранение объекта", notes = "Один объект", response = ProductDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный результат", response = ProductDto.class),
            @ApiResponse(code = 400, message = "Параметры неверны или отсутствуют"),
            @ApiResponse(code = 401, message = "Проблема авторизации"),
            @ApiResponse(code = 403, message = "Обращение запрещено"),
            @ApiResponse(code = 404, message = "Объект не обнаружен")
    })
    @PostMapping
    public ResponseEntity<ProductDto> save(@Valid @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.save(productDto), HttpStatus.OK);
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
    public void remove(@RequestParam Long id) {
        productService.remove(id);
    }
}
