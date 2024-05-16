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
import org.springframework.test.web.reactive.server.WebTestClient;

import com.uma.example.springuma.model.Informe;
import com.uma.example.springuma.model.InformeService;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.MedicoService;
import com.uma.example.springuma.model.Paciente;
import com.uma.example.springuma.model.PacienteService;

import jakarta.annotation.PostConstruct;

import com.uma.example.springuma.model.Imagen;
import com.uma.example.springuma.model.ImagenService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InformeControllerIntegrationTest {

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

    @Autowired
    private InformeService informeService;

    private Imagen imagen;

    @PostConstruct
    public void init() {
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

        Informe informe = new Informe("prediccion", "contenido", imagen);
        try {
            informeService.addInforme(informe);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void testGetInforme() {
        Long id = 1L; // replace with a valid id

        webTestClient.get().uri("/informe/" + id)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Informe.class);
    }

    @Test
    public void testGetInformes() {
        Long id = 1L; // replace with a valid id

        webTestClient.get().uri("/informe/imagen/" + id)
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Informe.class);
    }


    //Somos incapaces de hacer el test de saveInforme y por lo tanto de las predicciones no podemos solucionar el error de isCreated() que devuelve un 500
    @Test
    public void testSaveInforme() {
        Imagen imagen = new Imagen(); // replace with a valid Imagen object
        Informe informe = new Informe("prediccion", "contenido", imagen);

        webTestClient.post().uri("/informe")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(informe)
            .exchange()
            .expectStatus().isCreated();
    }
}