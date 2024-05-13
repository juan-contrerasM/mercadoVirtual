package co.edu.uniquindio.mercado.controller;


import co.edu.uniquindio.mercado.model.*;
import co.edu.uniquindio.mercado.model.enums.TipoCategoria;
import co.edu.uniquindio.mercado.model.enums.TipoEstado;
import co.edu.uniquindio.mercado.utils.Persistencia;

import java.io.IOException;
import java.util.TreeMap;

public class ModelFactoryController {
    private Mercado mercado = new Mercado();




    // periste teer una intancia uncia del model factory y la clase mercado

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    // contrutor
    public ModelFactoryController() {
    }

    //------------------------------------------METODOS VENDEDOR----------------------------------------------------------------
    //CREA UN VENDEDOR
    public Vendedor crearVendedor(String nombre, int edad, String cedula, String correo, String numeroCelular, String nombreUsuario, String clave) throws IOException {
        mercado.getListaVendedores().eliminarTodo();// elimina todo lo que haya en la lista
        mercado.setListaVendedores(Persistencia.cargarVendedores()); // se vuelvea cargar los datos a la lista princpial
        Vendedor vendedor = mercado.guardarVendedor(nombre, edad, cedula, correo, numeroCelular, nombreUsuario, clave);// se guarda el vendedor
        Persistencia.guardarVendedores(mercado.getListaVendedores());// vuelve y guarda los vendedores en el txt
        return vendedor; // return el vendedor

    }

    //obtiene al vendedor
    public Vendedor obtenerVendedor(String nombreUsuario, String cedula, String clave) throws IOException {
        mercado.getListaVendedores().eliminarTodo();// elimina todo lo que haya en la lista
        mercado.setListaVendedores(Persistencia.cargarVendedores()); // se vuelvea cargar los datos a la lista princpial
        Persistencia.guardarVendedores(mercado.getListaVendedores());// vuelve y guarda los vendedores en el txt
        return mercado.obtenerVendedor(nombreUsuario, cedula, clave);
    }

    // cambia la contaseña d eun vendedor
    public void cambiarContraseniaVendedor(Vendedor vendedor) throws IOException {
        mercado.getListaVendedores().eliminarTodo();// elimina todo lo que haya en la lista
        mercado.setListaVendedores(Persistencia.cargarVendedores()); // se vuelvea cargar los datos a la lista princpial
        mercado.cambiarContraseniaVendedor(vendedor);
        Persistencia.guardarVendedores(mercado.getListaVendedores());// vuelve y guarda los vendedores en el txt

    }

    //obteien al vendedor que ingreso a la app
    public Vendedor obtenerVendedorGlobal() {
        return mercado.getEstadoGlobalVendedor();
    }

    // guarda quien es el que ingresa a la aplicacion de los vendedor
    public void estadoGlobalVendedor(Vendedor vendedor) {
        mercado.setEstadoGlobalVendedor(vendedor);
    }
    //-----------------------------------------METODOS ADMINISTRADOR----------------------------------------

    // crea un administrador
    public Administrador crearAdministrador(String nombre, int edad, String cedula, String correo, String numeroCelular, String nombreUsuario, String clave) throws IOException {
        mercado.getListaAdministrador().borrarLista();
        mercado.setListaAdministrador(Persistencia.cargarAdministradores());
        Administrador administrador = mercado.guardarAdministrador(nombre, edad, cedula, correo, numeroCelular, nombreUsuario, clave);
        Persistencia.guardarAdministrador(mercado.getListaAdministrador());
        return administrador;
    }

    //sirve para obtener un abminstrador
    public Administrador obtenerAdministrador(String nombreUsuario, String cedula, String clave) throws IOException {
        mercado.getListaAdministrador().borrarLista();// elimina todo lo que haya en la lista
        mercado.setListaAdministrador(Persistencia.cargarAdministradores()); // se vuelvea cargar los datos a la lista princpial
        Persistencia.guardarAdministrador(mercado.getListaAdministrador());// vuelve y guarda los vendedores en el txt
        return mercado.obtenerAdministrador(nombreUsuario, cedula, clave);
    }

    // cambia la contraseña del un administrador
    public void cambiarContraseniaAdmisnitrador(Administrador administrador) throws IOException {
        mercado.getListaAdministrador().borrarLista();// elimina todo lo que haya en la lista
        mercado.setListaAdministrador(Persistencia.cargarAdministradores()); // se vuelvea cargar los datos a la lista princpial
        mercado.cambiarContraseniaAdministrador(administrador);
        Persistencia.guardarAdministrador(mercado.getListaAdministrador());// vuelve y guarda los vendedores en el txt

    }

    //guarda el adminitrador que inicia a la aplicacion
    public void estadoGlobalAdministrador(Administrador administrador) {
        mercado.setEstadoGlobalAdministrador(administrador);
    }

    // srive para obtener el adminitrador que inicia sesion
    public Administrador obtenerAdministradorGlobal() {
        return mercado.getEstadoGlobalAdministrador();
    }

    //--------------------------------------------------metodos productos--------------------------------------
    public Producto crearProducto(String url, String precio, String nombreProducto, TipoEstado tipoEstado, TipoCategoria tipoCategoria) throws IOException {
        mercado.getProductos().clear();
        mercado.setProductos(Persistencia.cargarProductos());
        mercado.setCodigoProdcuto(Persistencia.cargarCodigo());
        Producto producto = mercado.guardarProducto(url, precio, nombreProducto, tipoEstado, tipoCategoria);
        Persistencia.guadrarCodigoProducto(mercado.getCodigoProdcuto());
        Persistencia.guardarProductos(mercado.getProductos());

        return producto;
    }
    public Producto obtenerProdcuto(int id) throws IOException {
        mercado.getProductos().clear();
        mercado.setProductos(Persistencia.cargarProductos());
        Persistencia.guardarProductos(mercado.getProductos());
        Producto producto=mercado.buscarProducto(id);
        return producto;




    }
    //---------------------------metodos difernetes------------------------
    //crea un codigo de 4 digitos para cambiar la contraseña
    public String obtenerCodigoVerificacionCorreo() {
        return mercado.generarCodigo();
    }

    //----------------------------metodos Publicaciones-----------------------
    public Publicacion crearPublicacion(Producto producto, String decripcion, String titulo) throws IOException {
        mercado.getPublicaciones().clear();
        mercado.setPublicaciones(Persistencia.cargarPublicaciones());
        mercado.setCodigoProdcuto(Persistencia.cargarCodigo());
        Persistencia.guadrarCodigoProducto(mercado.getCodigoProdcuto());
        Publicacion publicacion = mercado.guadarPublicaion(producto, decripcion, titulo);
        Persistencia.guardarPublicaciones(mercado.getPublicaciones());
        return publicacion;
    }
    public TreeMap<String, Publicacion> obtenerPublicaiones() throws IOException {
        mercado.setPublicaciones(Persistencia.cargarPublicaciones());
        Persistencia.guardarPublicaciones(mercado.getPublicaciones());
        return  mercado.getPublicaciones();
    }
    public void guardarPublicacionGlobal(int id) throws IOException {
        mercado.getPublicaciones().clear();
        mercado.setPublicaciones(Persistencia.cargarPublicaciones());
        mercado.guardarPublicacionGlobal(id);
        Persistencia.guardarPublicaciones(mercado.getPublicaciones());

    }
    public  void modificarPublicaionLikes() throws IOException {
        mercado.getPublicaciones().clear();
        mercado.setPublicaciones(Persistencia.cargarPublicaciones());
        mercado.modificarLike();
        Persistencia.guardarPublicaciones(mercado.getPublicaciones());

    }
    public Publicacion obtenerPublicacionGlobal() {
        return mercado.getPublicaionGlobal();
    }
}

