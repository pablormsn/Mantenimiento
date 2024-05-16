package com.uma.example.springuma.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uma.example.springuma.integration.base.AbstractIntegration;
import com.uma.example.springuma.model.Imagen;
import com.uma.example.springuma.model.ImagenService;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.MedicoService;
import com.uma.example.springuma.model.Paciente;
import com.uma.example.springuma.model.PacienteService;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;

public class ImagenControllerIT extends AbstractIntegration {

    @LocalServerPort
    private Integer port;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private DataSource dataSource;

    private Imagen imagen;

    @Autowired
    private ImagenService imagenService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @PostConstruct
    public void setUp() throws SQLException, ParseException {
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:"+port)
        .responseTimeout(Duration.ofMillis(30000)).build();
        Medico medico = new Medico("12345678A", "Cardiología", "Dr. Smith");
        medicoService.addMedico(medico);
        Paciente paciente = new Paciente("John Doe", 30, "2024-05-15", "87654321B", medico);
        pacienteService.addPaciente(paciente);
        imagen = new Imagen();
        imagen.setNombre("healthy.png");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse("2024-05-15"));
        imagen.setFecha(calendar);
        imagen.setPaciente(paciente);
        imagenService.addImagen(imagen);
    }

    @Test
    public void testUploadImage() throws Exception {
        // Crear un objeto Paciente
        Paciente paciente = new Paciente();

        paciente.setDni("87654321B");
        paciente.setEdad(30);
        paciente.setNombre("John Doe");
        paciente.setCita("2024-05-15");
    
        // Convertir el objeto Paciente a JSON
        String pacienteJson = new ObjectMapper().writeValueAsString(paciente);
    
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("image", new ClassPathResource("healthy.png"));
        bodyBuilder.part("paciente", pacienteJson).header("Content-Type", "application/json");
    
        webTestClient
                .post()
                .uri("/imagen")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("healthy.png");
    }

    @Test
    public void testGetImage() throws Exception {
        webTestClient
                .get()
                .uri("/imagen/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.nombre")
                .isEqualTo("healthy.png");
    }


}
