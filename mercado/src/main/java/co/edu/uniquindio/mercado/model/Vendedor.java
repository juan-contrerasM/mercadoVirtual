package co.edu.uniquindio.mercado.model;

import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaSimple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
// Esta anotación genera automáticamente getters, setters, toString, equals y hashCode para todos los campos de la clase.
@AllArgsConstructor // Esta anotación genera un constructor que incluye todos los campos de la clase.
@NoArgsConstructor // Esta anotación genera un constructor sin argumentos.
@ToString // Esta anotación genera automáticamente el método toString para la clase.
public class Vendedor extends Persona {
    private String nombreUsuario;
    private String contrasenia;
    private ListaSimple<Vendedor> solicitudesEnviadas;
    private ListaSimple<Vendedor> solicitudesRecibidas;


    public Vendedor(String nombre, int edad, String cedula, String correo, String numeroCelular, String urlImg, String nombreUsuario, String contrasenia) {
        super(nombre, edad, cedula, correo, numeroCelular, urlImg);
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public ListaSimple<Vendedor> getSolicitudesEnviadas() {
        return solicitudesEnviadas;
    }

    public void setSolicitudesEnviadas(ListaSimple<Vendedor> solicitudesEnviadas) {
        this.solicitudesEnviadas = solicitudesEnviadas;
    }

    public ListaSimple<Vendedor> getSolicitudesRecibidas() {
        return solicitudesRecibidas;
    }

    public void setSolicitudesRecibidas(ListaSimple<Vendedor> solicitudesRecibidas) {
        this.solicitudesRecibidas = solicitudesRecibidas;
    }
}
