package co.edu.ufps.prestamos;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoRepository repositorio;
    private final LibrosClient libros;

    public PrestamoController(PrestamoRepository repositorio, LibrosClient libros) {
        this.repositorio = repositorio;
        this.libros = libros;
    }

    @GetMapping
    public List<Prestamo> listar() {
        return repositorio.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prestamo crear(@Valid @RequestBody NuevoPrestamo solicitud) {
        LibroDTO libro;
        try {
            libro = libros.obtenerLibro(solicitud.libroId());
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El libro " + solicitud.libroId() + " no existe");
        } catch (ResourceAccessException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "El servicio de libros no responde");
        }

        if (libro.ejemplaresDisponibles() == null || libro.ejemplaresDisponibles() <= 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "No hay ejemplares disponibles");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setLibroId(libro.id());
        prestamo.setTituloLibro(libro.titulo());
        prestamo.setCodigoEstudiante(solicitud.codigoEstudiante());
        return repositorio.save(prestamo);
    }
}
