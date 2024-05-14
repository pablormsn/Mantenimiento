package com.uma.example.springuma.integration.base;

import com.uma.example.springuma.integration.base.AbstractIntegration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ImagenControllerIntegrationTest extends AbstractIntegration {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testUploadImage() throws Exception {
        byte[] imageBytes = Files.readAllBytes(Paths.get("src/test/resources/healthy.png"));
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("image", new ByteArrayResource(imageBytes));
        parts.add("paciente", "{\"id\":1}");

        webTestClient.post()
                .uri("/imagen")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(parts))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testGetPrediction() {
        webTestClient.get()
                .uri("/imagen/predict/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.prediction").isEqualTo("No Cancer (label 0)");
    }
}
