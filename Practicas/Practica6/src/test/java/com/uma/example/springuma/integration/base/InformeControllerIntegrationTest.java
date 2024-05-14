package com.uma.example.springuma.integration.base;

import com.uma.example.springuma.integration.base.AbstractIntegration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class InformeControllerIntegrationTest extends AbstractIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateInforme() throws Exception {
        String informeJson = "{\"description\":\"No abnormalities detected.\", \"imagenId\":1}";
        mockMvc.perform(post("/informe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(informeJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetInforme() throws Exception {
        mockMvc.perform(get("/informe/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("No abnormalities detected."));
    }

    @Test
    public void testDeleteInforme() throws Exception {
        mockMvc.perform(delete("/informe/1"))
                .andExpect(status().isNoContent());
    }
}
