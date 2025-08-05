package com.aluracursos.foro_hub.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GestorDeErrores {

    /*@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarError404() {

        return ResponseEntity.notFound().build();
    }*/

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> gestionarError404() {
        return ResponseEntity.status(404).body("Tópico no encontrado");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionarError400(MethodArgumentNotValidException ex) {

        var errores = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errores.stream()
                .map(error -> new DatosErrorValidacion(error)).toList()); //*

    }


    public record DatosErrorValidacion(String campo, String mensaje) {//serian el field y el getmessage

        //constructor
        public DatosErrorValidacion(FieldError error) {
            this(
                    error.getField(),
                    error.getDefaultMessage());
        }

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> manejarJsonMalFormado(HttpMessageNotReadableException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("error", "El JSON enviado tiene un formato incorrecto. Verifica la sintaxis.");
        respuesta.put("detalle", ex.getMostSpecificCause().getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    public record DatosValidacion(String campo, String mensaje) {
        public DatosValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
