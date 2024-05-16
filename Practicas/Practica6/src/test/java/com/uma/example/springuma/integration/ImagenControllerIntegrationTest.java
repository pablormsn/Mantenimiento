/*
 * Grupo:
 * Jorge Velázquez Jiménez
 * Pablo Ruiz Galiánez
 * Pablo Robles Mansilla
 * 
 */

package com.uma.example.springuma.integration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;

import org.springframework.test.web.reactive.server.WebTestClient;

import org.springframework.core.io.FileSystemResource;

import org.springframework.web.reactive.function.BodyInserters;

import com.uma.example.springuma.model.Imagen;
import com.uma.example.springuma.model.ImagenService;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.MedicoService;
import com.uma.example.springuma.model.Paciente;
import com.uma.example.springuma.model.PacienteService;

import jakarta.annotation.PostConstruct;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImagenControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @LocalServerPort
    private Integer port;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ImagenService imagenService;

    private Imagen imagen;

    @PostConstruct
    public void init(){
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:"+port)
        .responseTimeout(Duration.ofMillis(30000)).build();
        Medico medico = new Medico("12345678A", "Cardiología", "Dr. Smith");
        medicoService.addMedico(medico);

        Paciente paciente = new Paciente("John Doe", 30, "2024-05-15", "87654321B", medico);
        pacienteService.addPaciente(paciente);

        imagen = new Imagen();
        imagen.setNombre("healthy.png");
        try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse("2024-05-15"));
        imagen.setFecha(calendar);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
       
        imagen.setPaciente(paciente);
        imagenService.addImagen(imagen);

    }


    @Test
    public void testGetImagen() {
        webTestClient.get().uri("/imagen/info/{id}", 1)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Imagen.class);
    }
    //Somos incapaces de solucionar el error de isOk() que devuelve un 500 aqui y en testUploadImage no entendemos porque salta el error
    @Test
    public void testGetImagenPrediction() {
        webTestClient.get().uri("/imagen/predict/{id}", 1)
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class);
    }

@Test
public void testUploadImage() {
    FileSystemResource resource = new FileSystemResource("src/test/resources/healthy.png");

    MultipartBodyBuilder builder = new MultipartBodyBuilder();
    builder.part("image", resource).filename("healthy.png");
    builder.part("paciente", "1");

    webTestClient.post().uri("/imagen")
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .body(BodyInserters.fromMultipartData(builder.build()))
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class);
}

    @Test
    public void testGetImagenes() {
        webTestClient.get().uri("/imagen/paciente/{id}", 1)
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Imagen.class);
    }

    @Test
    public void testDeleteCuenta() {
        webTestClient.delete().uri("/imagen/{id}", 1)
            .exchange()
            .expectStatus().isNoContent();
    }
}