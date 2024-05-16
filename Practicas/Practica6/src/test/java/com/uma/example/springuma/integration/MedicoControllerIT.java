package com.uma.example.springuma.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.uma.example.springuma.integration.base.AbstractIntegration;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.MedicoService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MedicoControllerIT extends AbstractIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MedicoService medicoService;

    @BeforeEach
    public void setUp() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            // Limpiar la tabla MEDICO
            statement.execute("DELETE FROM MEDICO");


        }
    }

    @Test
    public void testSaveMedico() throws Exception {
        String medicoJson = "{\"id\":2,\"dni\":\"87654321B\", \"especialidad\":\"Dermatología\", \"nombre\":\"Dr. John\"}"; 
        mockMvc.perform(post("/medico")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicoJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetMedico() throws Exception {
        Medico medico = new Medico("12345678A", "Dr. Smith", "Cardiología");
        medicoService.addMedico(medico);
        mockMvc.perform(get("/medico/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Dr. Smith"));
    }

    @Test
    public void testUpdateMedico() throws Exception {
        Medico medico = new Medico("12345678A", "Cardiología", "Dr. Smith");
        medicoService.addMedico(medico);
        String medicoUpdateJson = "{\"id\":1, \"dni\":\"12345678A\", \"especialidad\":\"Cardiología\", \"nombre\":\"Dr. John Smith\"}";
        mockMvc.perform(put("/medico")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicoUpdateJson))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteMedico() throws Exception {
        Medico medico = new Medico("12345678A", "Cardiología", "Dr. Smith");
        medicoService.addMedico(medico);
        mockMvc.perform(delete("/medico/1"))
                .andExpect(status().isOk());
    }
}
