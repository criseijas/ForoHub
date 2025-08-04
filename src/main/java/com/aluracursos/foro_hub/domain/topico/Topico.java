package com.aluracursos.foro_hub.domain.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private Boolean activo;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NO_RESPONDIDO;;
    private String autor;
    private String curso;

    public Topico(DatosCrearTopico datos) {

        this.id = null;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.activo = true;
        this.fechaCreacion = LocalDateTime.now();
        this.status = StatusTopico.TOPICO_ACTIVO;
        this.autor = datos.autor();
        this.curso = datos.curso();
    }

    public void actualizarDatos(@Valid DatosActualizarTopico datos) {
        if (datos.titulo() != null) this.titulo = datos.titulo();
        if (datos.mensaje() != null) this.mensaje = datos.mensaje();
        if (datos.curso() != null) this.curso = datos.curso();
        if (datos.autor() != null) this.autor = datos.autor();
    }

    public void desactivar() {
        this.activo = false;
    }

}
