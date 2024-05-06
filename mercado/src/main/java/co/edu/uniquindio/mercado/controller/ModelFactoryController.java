package co.edu.uniquindio.mercado.controller;


import co.edu.uniquindio.mercado.model.Administrador;
import co.edu.uniquindio.mercado.model.Mercado;
import co.edu.uniquindio.mercado.model.Vendedor;
import co.edu.uniquindio.mercado.utils.Persistencia;

import java.io.IOException;

public class ModelFactoryController {
    private Mercado mercado = new Mercado();

    public String obtenerCodigoVerificacionCorreo() {
        return mercado.generarCodigo();
    }


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


    //-----------------------------------------METODOS ADMINISTRADOR----------------------------------------

    // crea un administrador
    public Administrador crearAdministrador(String nombre, int edad, String cedula, String correo, String numeroCelular, String nombreUsuario, String clave) throws IOException {
        mercado.getListaAdministrador().borrarLista();
        mercado.setListaAdministrador(Persistencia.cargarAdministradores());
        Administrador administrador = mercado.guardarAdministrador(nombre, edad, cedula, correo, numeroCelular, nombreUsuario, clave);
        Persistencia.guardarAdministrador(mercado.getListaAdministrador());
        return administrador;
    }
}
