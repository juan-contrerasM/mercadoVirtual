package co.edu.uniquindio.mercado.model;

import co.edu.uniquindio.mercado.model.enums.TipoCategoria;
import co.edu.uniquindio.mercado.model.enums.TipoEstado;
import lombok.*;
@Data // Esta anotación genera automáticamente getters, setters, toString, equals y hashCode para todos los campos de la clase.
@AllArgsConstructor // Esta anotación genera un constructor que incluye todos los campos de la clase.
@NoArgsConstructor // Esta anotación genera un constructor sin argumentos.
@ToString // Esta anotación genera automáticamente el método toString para la clase.

public class Producto {
    private String nombre;
    private String urlImagen;
    private String precio;
    private TipoCategoria tipoCategoria;
    private TipoEstado tipoEstado;


}
