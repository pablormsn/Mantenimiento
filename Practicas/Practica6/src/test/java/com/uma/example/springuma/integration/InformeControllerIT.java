package com.uma.example.springuma.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.uma.example.springuma.integration.base.AbstractIntegration;
import com.uma.example.springuma.model.Imagen;
import com.uma.example.springuma.model.ImagenService;
import com.uma.example.springuma.model.Informe;
import com.uma.example.springuma.model.InformeService;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.MedicoService;
import com.uma.example.springuma.model.Paciente;
import com.uma.example.springuma.model.PacienteService;

import io.swagger.v3.oas.models.info.Info;
import net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.Accessor.Simple;
import reactor.core.publisher.Mono;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InformeControllerIT extends AbstractIntegration {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ImagenService imagenService;

    @Autowired
    private InformeService informeService;

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
    public void testCreateInforme() throws Exception {
        String informeJson = "{\"contenido\":\"Nuevo contenido\", \"prediccion\":\"Nueva predicción\", \"imagen_id\":1}";
        webTestClient.post()
                .uri("/informe")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(informeJson), String.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void testGetInforme() throws Exception {
        Medico medico = new Medico("12345678A", "Cardiología", "Dr. Smith");
        medicoService.addMedico(medico);
        Paciente paciente = new Paciente("John Doe", 30, "2024-05-15", "87654321B", medico);
        pacienteService.addPaciente(paciente);
        Imagen imagen = new Imagen();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse("2024-05-15"));
        imagen.setFecha(calendar);
        imagen.setNombre("healthy.png");
        imagen.setPaciente(paciente);
        
        Informe informe = new Informe("Contenido del informe", "Predicción del informe", imagen);
        imagenService.addImagen(imagen);
        informeService.addInforme(informe);
        webTestClient.get()
                .uri("/informe/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.contenido").isEqualTo("Contenido del informe");
    }

    @Test
    public void testUpdateInforme() throws Exception {
        Medico medico = new Medico("12345678A", "Cardiología", "Dr. Smith");
        medicoService.addMedico(medico);
        Paciente paciente = new Paciente("John Doe", 30, "2024-05-15", "87654321B", medico);
        pacienteService.addPaciente(paciente);
        String informeUpdateJson = "{\"id\":1, \"contenido\":\"Contenido actualizado\", \"prediccion\":\"Predicción actualizada\", \"imagen_id\":1}";
        webTestClient.put()
                .uri("/informe")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(informeUpdateJson), String.class)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void testDeleteInforme() throws Exception {
        Medico medico = new Medico("12345678A", "Cardiología", "Dr. Smith");
        medicoService.addMedico(medico);
        Paciente paciente = new Paciente("John Doe", 30, "2024-05-15", "87654321B", medico);
        pacienteService.addPaciente(paciente);
        webTestClient.delete()
                .uri("/informe/1")
                .exchange()
                .expectStatus().isOk();
    }
}
