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
public class Megusta extends Interaccion {
    public Megusta(Vendedor vendedor, LocalTime hora, LocalDate fecha, Publicacion publicacion) {
        super(vendedor, hora, fecha, publicacion);
    }


}
