package co.edu.uniquindio.mercado.model;

import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaDoble;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaSimple;
import co.edu.uniquindio.mercado.model.enums.TipoCategoria;
import co.edu.uniquindio.mercado.model.enums.TipoEstado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;

@Data
// Esta anotación genera automáticamente getters, setters, toString, equals y hashCode para todos los campos de la clase.
@AllArgsConstructor // Esta anotación genera un constructor que incluye todos los campos de la clase.
@NoArgsConstructor // Esta anotación genera un constructor sin argumentos.
@ToString // Esta anotación genera automáticamente el método toString para la clase.
public class Mercado {
    //-------------------------------- atributos-----------------------
    private ListaSimple<Vendedor> listaVendedores = new ListaSimple<>();
    private ListaDoble<Administrador> listaAdministrador = new ListaDoble<>();
    private Boolean verificacionCorreo = false;
    private  Vendedor estadoGlobalVendedor=new Vendedor();
    private Administrador estadoGlobalAdministrador=new Administrador();
    private HashMap<String, Producto> productos = new HashMap<>();

    //---------------------------------------------------------------------------
//-------------------------metodos vendedor--------------------------------------
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

    public Vendedor obtenerVendedor(String nombreUsuario, String cedula, String clave) {
        for (int i = 0; i < listaVendedores.getTamanio(); i++) {
            // validamos si ha yun usuario con esa credenciales
            if (listaVendedores.obtenerValorNodo(i).getCedula().equals(cedula) && listaVendedores.obtenerValorNodo(i).getNombreUsuario().equals(nombreUsuario) || listaVendedores.obtenerValorNodo(i).getContrasenia().equals(clave) && listaVendedores.obtenerValorNodo(i).getNombreUsuario().equals(nombreUsuario)) {
                return listaVendedores.obtenerValorNodo(i);// lo return si lo encuentra
            }
        }
        return null;// sino es null
    }

    // estado globan quien esta en la app
    public void setEstadoGlobalVendedor(Vendedor estadoGlobalVendedor) {
        this.estadoGlobalVendedor = estadoGlobalVendedor;
    }

    public Vendedor getEstadoGlobalVendedor() {
        return estadoGlobalVendedor;
    }

    //-----------------metodos administrador---------------------------------------

    public void cambiarContraseniaVendedor(Vendedor vendedor) {
        for (int i = 0; i < listaVendedores.getTamanio(); i++) {
            // validamos si ha yun usuario con esa credenciales
            if (listaVendedores.obtenerValorNodo(i).getCedula().equals(vendedor.getCedula()) && listaVendedores.obtenerValorNodo(i).getNombreUsuario().equals(vendedor.getNombreUsuario())) {
                listaVendedores.obtenerValorNodo(i).setContrasenia(vendedor.getContrasenia()); // se cambia la contraseña
            }
        }
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


    //sirve para busar un vendedor

    public Administrador obtenerAdministrador(String nombreUsuario, String cedula, String clave) {
        for (int i = 0; i < listaAdministrador.getTamanio(); i++) {
            // validamos si ha yun usuario con esa credenciales
            if (listaAdministrador.obtenerValorNodo(i).getCedula().equals(cedula) && listaAdministrador.obtenerValorNodo(i).getNombreUsuario().equals(nombreUsuario)||listaAdministrador.obtenerValorNodo(i).getContrasenia().equals(clave) && listaAdministrador.obtenerValorNodo(i).getNombreUsuario().equals(nombreUsuario)) {
                return listaAdministrador.obtenerValorNodo(i);// lo return si lo encuentra
            }
        }
        return null;// sino es null
    }

    public void cambiarContraseniaAdministrador(Administrador administrador) {
        for (int i = 0; i < listaAdministrador.getTamanio(); i++) {
            // validamos si ha yun usuario con esa credenciales
            if (listaAdministrador.obtenerValorNodo(i).getCedula().equals(administrador.getCedula()) && listaAdministrador.obtenerValorNodo(i).getNombreUsuario().equals(administrador.getNombreUsuario())) {
                listaAdministrador.obtenerValorNodo(i).setContrasenia(administrador.getContrasenia()); // se cambia la contraseña
            }
        }
    }


    //--------------------------------------------------------------------------------------
    // Método para generar un código de verificación aleatorio (puedes implementar tu propia lógica)
    public String generarCodigo() {
        // En este ejemplo, generamos un código de 6 dígitos aleatorio
        return String.format("%04d", (int) (Math.random() * 9999));
    }


    public void setEstadoGlobalAdministrador(Administrador estadoGlobalAdministrador) {
        this.estadoGlobalAdministrador = estadoGlobalAdministrador;
    }

    public Administrador getEstadoGlobalAdministrador() {
        return estadoGlobalAdministrador;
    }

    //--------------------------------------------metodoProducto------------------------------------
    public Producto guardarProducto (String url, String precio, String nombreProducto, TipoEstado tipoEstado, TipoCategoria tipoCategoria){
        Producto producto = new Producto(nombreProducto, url, precio, tipoCategoria, tipoEstado);// cre un producto
        productos.put(nombreProducto, producto);// lo guarda en el map
        return producto;// return el productoGuardado
    }
}

