package co.edu.uniquindio.mercado.model;

import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.Arbol;
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
public class Vendedor extends Persona implements Comparable<Vendedor> {
    private String nombreUsuario;
    private String urlImg;
    private String contrasenia;
    private ListaSimple<SolicitudesAmistad> solicitudesEnviadas = new ListaSimple<>();
    private ListaSimple<SolicitudesAmistad> solicitudesRecibidas= new ListaSimple<>();;
    private Arbol<Vendedor> redAmigos = new Arbol<Vendedor>();
    private ListaSimple<Vendedor> amigos= new ListaSimple<>();;


    public Vendedor(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Vendedor(String nombre, int edad, String cedula, String correo, String numeroCelular, String urlImg, String nombreUsuario, String contrasenia) {
        super(nombre, edad, cedula, correo, numeroCelular);
        this.nombreUsuario = nombreUsuario;
        this.urlImg = urlImg;
        this.contrasenia = contrasenia;

    }

    public ListaSimple<SolicitudesAmistad> getSolicitudesEnviadas() {
        return solicitudesEnviadas;
    }

    public void setSolicitudesEnviadas(ListaSimple<SolicitudesAmistad> solicitudesEnviadas) {
        this.solicitudesEnviadas = solicitudesEnviadas;
    }

    public ListaSimple<SolicitudesAmistad> getSolicitudesRecibidas() {
        return solicitudesRecibidas;
    }

    public void setSolicitudesRecibidas(ListaSimple<SolicitudesAmistad> solicitudesRecibidas) {
        this.solicitudesRecibidas = solicitudesRecibidas;
    }

    public Arbol<Vendedor> getRedAmigos() {
        return redAmigos;
    }

    public void setRedAmigos(Arbol<Vendedor> redAmigos) {
        this.redAmigos = redAmigos;
    }



    public void agregarSolicitudesEnviadas(SolicitudesAmistad controladorSolicitudes){
        System.out.println("EnviandoSolicitud");

        if (solicitudesEnviadas.estaVacia()){
            solicitudesEnviadas.agregarInicio(controladorSolicitudes);
        }else{
            solicitudesEnviadas.agregarfinal(controladorSolicitudes);
        }

    }

    public void agregarSolicitudesRecibidas(SolicitudesAmistad solicitudesAmistad){
        System.out.println("guardandoSolicitud");
        if (solicitudesRecibidas.estaVacia()){
            solicitudesRecibidas.agregarInicio(solicitudesAmistad);
        }else{
            solicitudesRecibidas.agregarfinal(solicitudesAmistad);
        }
    }


    public void agregarAmigos(Vendedor vendedor){
        System.out.println("agregando a amigos");

        amigos.agregarInicio(vendedor);

    }


    @Override
    public int compareTo( Vendedor vendedor) {
        return this.nombreUsuario.compareTo(vendedor.nombreUsuario);

    }

    public ListaSimple<Vendedor> getAmigos() {
        return amigos;
    }

    public void setAmigos(ListaSimple<Vendedor> amigos) {
        this.amigos = amigos;
    }
}
