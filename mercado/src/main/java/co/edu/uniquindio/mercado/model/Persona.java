package co.edu.uniquindio.mercado.model;

import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.Arbol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
// Esta anotación genera automáticamente getters, setters, toString, equals y hashCode para todos los campos de la clase.
@AllArgsConstructor // Esta anotación genera un constructor que incluye todos los campos de la clase.
@NoArgsConstructor // Esta anotación genera un constructor sin argumentos.
@ToString // Esta anotación genera automáticamente el método toString para la clase.
public abstract class Persona {
    private String nombre;
    private  int edad;
    private  String correo;
    private String numeroCelular;
    private TipoUsuario tipoUsuario;
    private String  cedula;
    private String urlImg;


    private Arbol <Persona> redAmigos = new Arbol<>();

    public Persona(String nombre, int edad,String cedula, String correo, String numeroCelular, String urlImg) {
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
        this.numeroCelular = numeroCelular;
        this.tipoUsuario = tipoUsuario;
        this.cedula = cedula;
        this.urlImg = urlImg;
    }

    public Arbol<Persona> getRedAmigos() {
        return redAmigos;
    }

    public void setRedAmigos(Arbol<Persona> redAmigos) {
        this.redAmigos = redAmigos;
    }
}
