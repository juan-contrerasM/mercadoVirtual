package co.edu.uniquindio.mercado.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor // Esta anotación genera un constructor sin argumentos.
@ToString // Esta anotación genera automáticamente el método toString para la clase.
public class Comentario extends  Interaccion {
    String mensaje;
    public Comentario(Vendedor vendedor, LocalTime hora, LocalDate fecha, Publicacion publicacion,String mensaje) {
        super(vendedor, hora, fecha, publicacion);
        this.mensaje=mensaje;
    }
}
