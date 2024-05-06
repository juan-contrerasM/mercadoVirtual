package co.edu.uniquindio.mercado.model;

import co.edu.uniquindio.mercado.controllerView.IniciarApp;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaDoble;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaSimple;
import javafx.fxml.Initializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
// Esta anotación genera automáticamente getters, setters, toString, equals y hashCode para todos los campos de la clase.
@AllArgsConstructor // Esta anotación genera un constructor que incluye todos los campos de la clase.
@NoArgsConstructor // Esta anotación genera un constructor sin argumentos.
@ToString // Esta anotación genera automáticamente el método toString para la clase.
public class Mercado  {
    private ListaSimple<Vendedor> listaVendedores = new ListaSimple<>();
    private ListaDoble<Administrador> listaAdministrador = new ListaDoble<>();
    private Boolean verificacionCorreo=false;


    //verifica si un usuario ya se registro, atraves del nombre de usuario y cedula
    public boolean verificarVendedor(Vendedor vendedor) {
        for (int i = 0; i < listaVendedores.getTamanio(); i++) {
            if ((listaVendedores.obtenerValorNodo(i).getNombreUsuario().equals(vendedor.getNombreUsuario()) || listaVendedores.obtenerValorNodo(i).getCedula().equals(vendedor.getCedula()))) {
                return false;
            }
        }
        return true;

    }

    public Vendedor guardarVendedor(String nombre, int edad, String cedula, String correo, String numeroCelular, String nombreUsuario, String clave) {
        //se crea el vendedor
        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(nombre);
        vendedor.setEdad(edad);
        vendedor.setCedula(cedula);
        vendedor.setCorreo(correo);
        vendedor.setNumeroCelular(numeroCelular);
        vendedor.setNombreUsuario(nombreUsuario);
        vendedor.setContrasenia(clave);
        vendedor.setTipoUsuario(TipoUsuario.VENDEDOR);
        //se verifica que el vendedor no exista
        if (verificarVendedor(vendedor)) {
            listaVendedores.agregarfinal(vendedor);
            return vendedor;
        }
        return null;// si existe return null
    }

    //verifica si un usuario ya se registro, atraves del nombre de usuario y cedula
    public boolean verificarAdministrador(Administrador administrador) {
        for (int i = 0; i < listaAdministrador.getTamanio(); i++) {
            if ((listaAdministrador.obtenerValorNodo(i).getNombreUsuario().equals(administrador.getNombreUsuario()) || listaAdministrador.obtenerValorNodo(i).getCedula().equals(administrador.getCedula()))) {
                return false;
            }
        }
        return true;

    }

    public Administrador guardarAdministrador(String nombre, int edad, String cedula, String correo, String numeroCelular, String nombreUsuario, String clave) {
        //se crea el vendedor
        Administrador administrador = new Administrador();
        administrador.setNombre(nombre);
        administrador.setEdad(edad);
        administrador.setCedula(cedula);
        administrador.setCorreo(correo);
        administrador.setNumeroCelular(numeroCelular);
        administrador.setNombreUsuario(nombreUsuario);
        administrador.setContrasenia(clave);
        administrador.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
        //se verifica que el vendedor no exista
        if (verificarAdministrador(administrador)) {
            listaAdministrador.agregarfinal(administrador);
            return administrador;
        }
        return null;
    }
    // Método para generar un código de verificación aleatorio (puedes implementar tu propia lógica)
    public String generarCodigo() {
        // En este ejemplo, generamos un código de 6 dígitos aleatorio
        return String.format("%06d", (int) (Math.random() * 9999));
    }


}
