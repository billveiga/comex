package br.com.alura.comex.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
class CacheControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveriaInvalidarCache() throws Exception {
        URI uri = new URI("/api/aW52YWxpZGEgcmVsYXTDs3JpbyBkZSB2ZW5kYXM");

        mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(status().isNoContent());
    }

}