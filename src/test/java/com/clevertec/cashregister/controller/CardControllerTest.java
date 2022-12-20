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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:/application.yaml")
@Sql(value = "/sql/controller/before_card_test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/controller/after_card_test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CardControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void findByNumberTest() throws Exception {
        // positive
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/cards")
                        .param("number", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        // negative
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/cards")
                        .param("number", String.valueOf(Long.MAX_VALUE))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    public void saveTest() throws Exception {
        // positive
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/cards")
                        .param("cardType", "C")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.percent").value(5));
        // positive
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/cards")
                        .param("cardType", "S")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.percent").value(10));
        // positive
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/cards")
                        .param("cardType", "P")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.percent").value(15));
        // positive
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/cards")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.percent").value(5));
        // negative
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/cards")
                        .param("cardType", "Z")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    public void removeTest() throws Exception {
        // positive
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/cards")
                        .param("number", "1"))
                .andExpect(status().is(200));
        // negative
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/cards")
                        .param("number", "1"))
                .andExpect(status().is(404));
    }
}
