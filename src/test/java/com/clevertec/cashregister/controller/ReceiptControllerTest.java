package com.clevertec.cashregister.controller;

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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:/application.yaml")
@Sql(value = "/sql/controller/before_receipt_test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/controller/after_receipt_test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ReceiptControllerTest {

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
                        .get("/api/receipts")
                        .param("id", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        // negative
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/receipts")
                        .param("id", String.valueOf(Long.MAX_VALUE))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    public void findAllTest() throws Exception {
        // positive
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/receipts/page")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.pageSize").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.pageNumber").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(10)));
        // ---
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/receipts/page")
                        .param("pageNumber", "1")
                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.pageSize").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.pageNumber").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(10)));
        // ---
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/receipts/page")
                        .param("pageNumber", "3")
                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.pageSize").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.pageNumber").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(10)));
        // ---
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/receipts/page")
                        .param("pageNumber", "4")
                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.pageSize").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.pageNumber").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(0)));
        // ---
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/receipts/page")
                        .param("pageNumber", "2")
                        .param("pageSize", "30")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.pageSize").value(30))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.pageNumber").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(0)));
        // ---
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/receipts/page")
                        .param("pageNumber", "0")
                        .param("pageSize", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.pageSize").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.pageNumber").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(10)));
        // ---
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/receipts/page")
                        .param("pageNumber", "-1")
                        .param("pageSize", "-1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.pageSize").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page.pageNumber").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(10)));
    }

    @Test
    public void createTest() throws Exception {
        Long[] barcodeArray;
        // positive
        barcodeArray = new Long[]{1L};
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/receipts?card=1&" + getUrl(barcodeArray))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.footer.discount").value(0.0));
        // positive
        barcodeArray = new Long[]{1L, 1L, 1L, 1L, 1L, 1L};
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/receipts?card=1&" + getUrl(barcodeArray))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.footer.discount").value(0.3));
        // positive
        barcodeArray = new Long[]{1L};
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/receipts?card=2&" + getUrl(barcodeArray))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        // positive
        barcodeArray = new Long[]{1L, 1L, 1L, 1L, 1L, 1L};
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/receipts?card=2&" + getUrl(barcodeArray))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.footer.discount").value(0.60));
        // positive
        barcodeArray = new Long[]{1L};
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/receipts?card=3&" + getUrl(barcodeArray))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        // positive
        barcodeArray = new Long[]{1L, 1L, 1L, 1L, 1L, 1L};
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/receipts?card=3&" + getUrl(barcodeArray))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.footer.discount").value(0.90));

        // big receipts

        // positive
        barcodeArray = new Long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/receipts?card=2&" + getUrl(barcodeArray))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        // positive
        barcodeArray = new Long[]{1L, 1L, 1L, 1L, 1L, 1L, 10L, 10L, 10L, 10L, 10L, 10L};
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/receipts?card=1&" + getUrl(barcodeArray))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.footer.discount").value(0.3));
        // positive
        barcodeArray = new Long[]{1L, 1L, 1L, 1L, 1L, 1L, 10L, 10L, 10L, 10L, 10L, 10L};
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/receipts?card=2&" + getUrl(barcodeArray))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.footer.discount").value(0.6));
        // positive
        barcodeArray = new Long[]{1L, 1L, 1L, 1L, 1L, 1L, 10L, 10L, 10L, 10L, 10L, 10L};
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/receipts?card=3&" + getUrl(barcodeArray))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.footer.discount").value(0.9));
        // positive
        barcodeArray = new Long[]{1L, 1L, 1L, 1L, 1L, 1L, 10L, 10L, 10L, 10L, 10L, 10L};
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/receipts?" + getUrl(barcodeArray))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.footer.discount").value(0.0));
        // negative
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/receipts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
        // ---
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/receipts?card=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    private String getUrl(Long[] barcodeArray) {
        StringBuilder str = new StringBuilder();
        for (Long barcode : barcodeArray) {
            str.append("barcode=").append(barcode).append("&");
        }
        str.setLength(str.length() - 1);
        return str.toString();
    }
}
