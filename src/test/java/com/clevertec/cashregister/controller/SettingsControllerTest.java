package com.clevertec.cashregister.controller;

import com.clevertec.cashregister.util.TestMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:/application.yaml")
@Sql(value = "/sql/controller/before_settings_test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/controller/after_settings_test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class SettingsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getSettingsTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/settings")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn();
        Map<String, String> map = (Map<String, String>) TestMapper.map(mvcResult);
        Assertions.assertNotNull(map);
        Assertions.assertEquals(4, map.size());
        Assertions.assertEquals("receipt title", map.get("title"));
    }

    @Test
    public void addSettingsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/settings")
                        .param("key", "lalala")
                        .param("value", "ololo")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/settings")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn();
        Map<String, String> map = (Map<String, String>) TestMapper.map(mvcResult);
        Assertions.assertNotNull(map);
        Assertions.assertEquals(5, map.size());
        Assertions.assertEquals("ololo", map.get("lalala"));
    }

    @Test
    public void removeByKeyTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/settings")
                        .param("key", "title"))
                .andExpect(status().is(200));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/settings")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn();
        Map<String, String> map = (Map<String, String>) TestMapper.map(mvcResult);
        Assertions.assertNotNull(map);
        Assertions.assertEquals(3, map.size());
        Assertions.assertNull(map.get("title"));
    }
}
