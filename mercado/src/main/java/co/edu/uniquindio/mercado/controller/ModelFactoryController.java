package co.edu.uniquindio.mercado.controller;


import co.edu.uniquindio.mercado.model.Administrador;
import co.edu.uniquindio.mercado.model.Mercado;
import co.edu.uniquindio.mercado.model.Vendedor;
import co.edu.uniquindio.mercado.utils.Persistencia;

import java.io.IOException;

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

    public Vendedor obtenerVendedor(String nombreUsuario, String cedula, String clave) throws IOException {
        mercado.getListaVendedores().eliminarTodo();// elimina todo lo que haya en la lista
        mercado.setListaVendedores(Persistencia.cargarVendedores()); // se vuelvea cargar los datos a la lista princpial
        Persistencia.guardarVendedores(mercado.getListaVendedores());// vuelve y guarda los vendedores en el txt
        return mercado.obtenerVendedor(nombreUsuario, cedula,clave);
    }

    public void cambiarContraseniaVendedor(Vendedor vendedor) throws IOException {
        mercado.getListaVendedores().eliminarTodo();// elimina todo lo que haya en la lista
        mercado.setListaVendedores(Persistencia.cargarVendedores()); // se vuelvea cargar los datos a la lista princpial
        mercado.cambiarContraseniaVendedor(vendedor);
        Persistencia.guardarVendedores(mercado.getListaVendedores());// vuelve y guarda los vendedores en el txt

    }
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

    public String obtenerCodigoVerificacionCorreo() {
        return mercado.generarCodigo();
    }
    public Administrador obtenerAdministrador(String nombreUsuario, String cedula,String clave) throws IOException {
        mercado.getListaAdministrador().borrarLista();// elimina todo lo que haya en la lista
        mercado.setListaAdministrador(Persistencia.cargarAdministradores()); // se vuelvea cargar los datos a la lista princpial
        Persistencia.guardarAdministrador(mercado.getListaAdministrador());// vuelve y guarda los vendedores en el txt
        return mercado.obtenerAdministrador(nombreUsuario, cedula,clave);
    }
    public void cambiarContraseniaAdmisnitrador(Administrador administrador) throws IOException {
        mercado.getListaAdministrador().borrarLista();// elimina todo lo que haya en la lista
        mercado.setListaAdministrador(Persistencia.cargarAdministradores()); // se vuelvea cargar los datos a la lista princpial
        mercado.cambiarContraseniaAdministrador(administrador);
        Persistencia.guardarAdministrador(mercado.getListaAdministrador());// vuelve y guarda los vendedores en el txt

    }
    public void estadoGlobalAdministrador(Administrador administrador) {
        mercado.setEstadoGlobalAdministrador(administrador);
    }

}

