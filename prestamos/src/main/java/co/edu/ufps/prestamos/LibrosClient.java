package co.edu.ufps.prestamos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class LibrosClient {

    private final RestClient restClient;

    public LibrosClient(@Value("${libros.url}") String librosUrl) {
        this.restClient = RestClient.create(librosUrl);
    }

    // GET http://localhost:8091/libros/{id}
    public LibroDTO obtenerLibro(Long id) {
        return restClient.get()
                .uri("/libros/{id}", id)
                .retrieve()
                .body(LibroDTO.class);
    }
}
