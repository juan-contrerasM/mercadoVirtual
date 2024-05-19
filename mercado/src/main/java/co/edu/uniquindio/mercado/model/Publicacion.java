package co.edu.uniquindio.mercado.model;

import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.Pila;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

@Data // Esta anotación genera automáticamente getters, setters, toString, equals y hashCode para todos los campos de la clase.
@AllArgsConstructor // Esta anotación genera un constructor que incluye todos los campos de la clase.
@NoArgsConstructor // Esta anotación genera un constructor sin argumentos.
@ToString // Esta anotación genera automáticamente el método toString para la clase.

public class Publicacion {
    private String titulo;
    private Producto producto;
    private Vendedor vendedor;
    private String descripcion;
    private LocalTime horaPublicacion;
    private LocalDate diaPublicado;
    private Integer contadorComentarios;
    private  Integer contadorMegusta;
    private Pila<Comentario> listComentario;
    private ArrayList<Megusta> listMegusta;
    private int visualizacion;
}
