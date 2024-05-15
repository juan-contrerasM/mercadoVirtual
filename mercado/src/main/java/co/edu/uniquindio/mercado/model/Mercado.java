package co.edu.uniquindio.mercado.model;

import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaDoble;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaSimple;
import co.edu.uniquindio.mercado.model.enums.TipoCategoria;
import co.edu.uniquindio.mercado.model.enums.TipoEstado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import co.edu.uniquindio.mercado.utils.Persistencia;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Data
// Esta anotación genera automáticamente getters, setters, toString, equals y hashCode para todos los campos de la clase.
@AllArgsConstructor // Esta anotación genera un constructor que incluye todos los campos de la clase.
@NoArgsConstructor // Esta anotación genera un constructor sin argumentos.
@ToString // Esta anotación genera automáticamente el método toString para la clase.
public class Mercado {
    //-------------------------------- atributos-----------------------
    private  ListaSimple<Vendedor> listaVendedores = new ListaSimple<>();
    private ListaDoble<Administrador> listaAdministrador = new ListaDoble<>();
    private Boolean verificacionCorreo = false;
    private  static Vendedor estadoGlobalVendedor = new Vendedor();
    private Administrador estadoGlobalAdministrador = new Administrador();
    private HashMap<String, Producto> productos = new HashMap<>();
    private TreeMap<String,Publicacion>publicaciones= new TreeMap<>();
    int codigoProdcuto=0;
    private Publicacion publicaionGlobal;


    public static ListaSimple<Vendedor> cargarVendedores(){
        try {
             return Persistencia.cargarVendedores();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void subirVendedores(){
         listaVendedores =cargarVendedores();

    }







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
        System.out.println("el nombre del usuario es : "+estadoGlobalVendedor.getNombreUsuario());
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
            if (listaAdministrador.obtenerValorNodo(i).getCedula().equals(cedula) && listaAdministrador.obtenerValorNodo(i).getNombreUsuario().equals(nombreUsuario) || listaAdministrador.obtenerValorNodo(i).getContrasenia().equals(clave) && listaAdministrador.obtenerValorNodo(i).getNombreUsuario().equals(nombreUsuario)) {
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
    public Producto guardarProducto(String url, String precio, String nombreProducto, TipoEstado tipoEstado, TipoCategoria tipoCategoria) {
        codigoProdcuto+=1;
        Producto producto = new Producto(nombreProducto, url, precio, tipoCategoria, tipoEstado, codigoProdcuto);// cre un producto
        productos.put(nombreProducto, producto);// lo guarda en el map
        return producto;// return el productoGuardado
    }

    public Publicacion guadarPublicaion(Producto producto, String decripcion, String titulo) {
        Publicacion publicacion= new Publicacion(titulo,producto,estadoGlobalVendedor,decripcion,obtenerHoraActual(),obtenerFechaActual(),0,0,null,null,0);
        publicaciones.put(String.valueOf(producto.getId()),publicacion);
        return  publicacion;
    }
    public static LocalTime obtenerHoraActual() {
        return LocalTime.now();
    }

    // Método para obtener la fecha actual
    public static LocalDate obtenerFechaActual() {
        return LocalDate.now();
    }

    public void guardarPublicacionGlobal(int id) {
        for (Map.Entry<String, Publicacion> entry : publicaciones.entrySet()) {
            String key = entry.getKey();
            Publicacion value = entry.getValue();
            if(value.getProducto().getId()==id){
                publicaionGlobal=value;
                publicaionGlobal.setVisualizacion(publicaionGlobal.getVisualizacion()+1);
                value=publicaionGlobal;
                break;
            }
            System.out.println("Clave: " + key + ", Valor: " + value);
        }

    }

    public Producto buscarProducto(int id) {
        for (Map.Entry<String, Producto> entry : productos.entrySet()) {
            String key = entry.getKey();
            Producto value = entry.getValue();
            if(value.getId()==id){
                return value;
            }
            System.out.println("Clave: " + key + ", Valor: " + value);
        }
        return null;

    }

    public void modificarLike() {
        LocalTime horaInteraccion = LocalTime.now();
        LocalDate fechaInteraccion = LocalDate.now();
        Megusta megusta = new Megusta(estadoGlobalVendedor, horaInteraccion, fechaInteraccion, publicaionGlobal);
        for (Map.Entry<String, Publicacion> entry : publicaciones.entrySet()) {
            String key = entry.getKey();
            Publicacion value = entry.getValue();
            if(value.getProducto().getId()==publicaionGlobal.getProducto().getId()){
                value.getListMegusta().add(megusta);
                value.setContadorMegusta(publicaionGlobal.getContadorMegusta()+1);
                publicaionGlobal.setContadorMegusta(value.getContadorMegusta());
            }
        }


    }

    public ListaSimple<Vendedor> obtenerListaVendedores2() {
        subirVendedores();
        return listaVendedores;
    }
}

