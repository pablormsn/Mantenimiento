package com.uma.example.springuma.integration.base;

import com.uma.example.springuma.integration.base.AbstractIntegration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MedicoControllerIntegrationTest extends AbstractIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateMedico() throws Exception {
        String medicoJson = "{\"name\":\"Dr. Smith\", \"dni\":\"12345678A\"}";
        mockMvc.perform(post("/medico")
                .contentType(MediaType.APPLICATION_JSON)
                .content(medicoJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetMedico() throws Exception {
        mockMvc.perform(get("/medico/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. Smith"));
    }

    @Test
    public void testUpdateMedico() throws Exception {
        String medicoUpdateJson = "{\"id\":1, \"name\":\"Dr. John Smith\", \"dni\":\"12345678A\"}";
        mockMvc.perform(put("/medico")
                .contentType(MediaType.APPLICATION_JSON)
                .content(medicoUpdateJson))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteMedico() throws Exception {
        mockMvc.perform(delete("/medico/1"))
                .andExpect(status().isOk());
    }
}
