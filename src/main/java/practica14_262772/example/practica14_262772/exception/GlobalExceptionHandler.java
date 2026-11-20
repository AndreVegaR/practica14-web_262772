package practica14_262772.example.practica14_262772.exception;

import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import practica14_262772.example.practica14_262772.dto.ErrorDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> manejarValidaciones(MethodArgumentNotValidException e) {
        List<String> detalles = new ArrayList<>();
        for (FieldError error: e.getBindingResult().getFieldErrors()) {
            detalles.add(error.getField()+": "+error.getDefaultMessage());
        }
        String mensaje = "Error en la validación de los datos enviados";
        HttpStatus estado = HttpStatus.NOT_FOUND;
        return crearDTO(mensaje, detalles, estado);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> manejarRecursoNoEncontrado(MethodArgumentNotValidException e) {
        List<String> detalles = new ArrayList<>();
        detalles.add(e.getMessage());
        HttpStatus estado = HttpStatus.NOT_FOUND;
        String mensaje = "Recurso no encontrado";
        return crearDTO(mensaje, detalles, estado);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> manejarExcepcionGlobal(Exception e) {
        List<String> detalles = new ArrayList<>();
        detalles.add(e.getLocalizedMessage());
        String mensaje = "Ocurrió un error interno en el servidor";
        HttpStatus estado = HttpStatus.NOT_FOUND;
        return crearDTO(mensaje, detalles, estado);
    }

    private ResponseEntity<ErrorDTO> crearDTO(String mensaje, List<String> detalles, HttpStatus estado) {
        ErrorDTO errorDTO = new ErrorDTO(mensaje, detalles, LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, estado);
    }
}