package co.edu.uniquindio.mercado.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Data// Esta anotación genera automáticamente getters, setters, toString, equals y hashCode para todos los campos de la clase.
@NoArgsConstructor // Esta anotación genera un constructor sin argumentos.
@ToString // Esta anotación genera automáticamente el método toString para la clase.
public abstract class Interaccion {
    private  Vendedor vendedor;
    private LocalTime hora;
    private LocalDate fecha;
    private Publicacion publicacion;
}
