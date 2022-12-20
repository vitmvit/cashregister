package com.clevertec.cashregister.controller;

import com.clevertec.cashregister.dto.ProductDto;
import com.clevertec.cashregister.util.TestMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:/application.yaml")
@Sql(value = "/sql/controller/before_product_test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/controller/after_product_test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void findByIdTest() throws Exception {
        // positive
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products")
                        .param("id", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        // negative
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products")
                        .param("id", String.valueOf(Long.MAX_VALUE))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    public void findAllByDescriptionTest() throws Exception {
        // positive
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products/description")
                        .param("description", "goods")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(50)));
        // ---
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products/description")
                        .param("description", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(5)));
        // ---
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products/description")
                        .param("description", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(14)));
        // negative
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products/description")
                        .param("description", "псина сутулая детская игрушка грустная")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(0)));
    }

    @Test
    public void saveTest() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(null);
        productDto.setDescription("Экивоки");
        productDto.setPrice(BigDecimal.valueOf(100.11));
        // positive
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/products")
                        .content(TestMapper.map(productDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(productDto.getDescription()));
        // ---
        productDto.setId(1L);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/products")
                        .content(TestMapper.map(productDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(productDto.getDescription()));
    }

    @Test
    public void removeTest() throws Exception {
        // positive
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/products")
                        .param("id", "1"))
                .andExpect(status().is(200));
        // negative
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/products")
                        .param("id", "1"))
                .andExpect(status().is(404));
    }
}
