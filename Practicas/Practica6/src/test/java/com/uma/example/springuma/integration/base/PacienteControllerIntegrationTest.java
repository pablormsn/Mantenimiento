package com.uma.example.springuma.integration.base;

import com.uma.example.springuma.integration.base.AbstractIntegration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PacienteControllerIntegrationTest extends AbstractIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreatePaciente() throws Exception {
        String pacienteJson = "{\"name\":\"Jane Doe\", \"medicoId\":1}";
        mockMvc.perform(post("/paciente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pacienteJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetPaciente() throws Exception {
        mockMvc.perform(get("/paciente/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    public void testUpdatePaciente() throws Exception {
        String pacienteUpdateJson = "{\"id\":1, \"name\":\"Jane Smith\", \"medicoId\":1}";
        mockMvc.perform(put("/paciente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pacienteUpdateJson))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeletePaciente() throws Exception {
        mockMvc.perform(delete("/paciente/1"))
                .andExpect(status().isOk());
    }
}
