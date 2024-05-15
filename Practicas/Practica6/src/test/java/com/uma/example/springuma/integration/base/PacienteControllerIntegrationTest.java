package com.uma.example.springuma.integration.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.MedicoService;
import com.uma.example.springuma.model.Paciente;
import com.uma.example.springuma.model.PacienteService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PacienteControllerIntegrationTest extends AbstractIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;
    
    @BeforeEach
    public void setUp() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            // Limpiar las tablas en el orden correcto
            statement.execute("DELETE FROM INFORME");
            statement.execute("DELETE FROM IMAGEN");
            statement.execute("DELETE FROM PACIENTE");
            statement.execute("DELETE FROM MEDICO");

        }
    }

    @Test
    public void testSavePaciente() throws Exception {
        String pacienteJson = "{\"cita\":\"2024-06-01\", \"dni\":\"87654321C\", \"edad\":25, \"nombre\":\"Jane Doe\", \"medico_id\":1}";
        mockMvc.perform(post("/paciente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pacienteJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetPaciente() throws Exception {
        Medico medico = new Medico("12345678A", "Cardiología", "Dr. Smith");
        medicoService.addMedico(medico);
        Paciente paciente = new Paciente("John Doe", 30, "2024-05-15", "87654321B", medico);
        pacienteService.addPaciente(paciente);
        mockMvc.perform(get("/paciente/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("John Doe"));
    }

    @Test
    public void testUpdatePaciente() throws Exception {
        Medico medico = new Medico("12345678A", "Cardiología", "Dr. Smith");
        medicoService.addMedico(medico);
        Paciente paciente = new Paciente("John Doe", 30, "2024-05-15", "87654321B", medico);
        pacienteService.addPaciente(paciente);
        String pacienteUpdateJson = "{\"id\":1, \"cita\":\"2024-05-15\", \"dni\":\"87654321B\", \"edad\":31, \"nombre\":\"John Smith\", \"medico_id\":1}";
        mockMvc.perform(put("/paciente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pacienteUpdateJson))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeletePaciente() throws Exception {
        Medico medico = new Medico("12345678A", "Cardiología", "Dr. Smith");
        medicoService.addMedico(medico);
        Paciente paciente = new Paciente("John Doe", 30, "2024-05-15", "87654321B", medico);
        pacienteService.addPaciente(paciente);
        mockMvc.perform(delete("/paciente/1"))
                .andExpect(status().isOk());
    }
}
