package co.edu.uniquindio.mercado.controller;

import co.edu.uniquindio.mercado.model.Administrador;
import co.edu.uniquindio.mercado.model.Vendedor;

import java.io.IOException;

public class ControllerRegistrarUsuarios {

    //instancia del modelfactory
    static   ModelFactoryController modelfactoryController;

    //intancia unica
    public  ControllerRegistrarUsuarios(){
        modelfactoryController=ModelFactoryController.getInstance();
    }


    // metodo para crear un vendedor

    public Vendedor crearVendedor(String nombre, int edad, String cedula, String correo, String numeroCelular,String url ,String nombreUsuario, String clave) throws IOException {
        return modelfactoryController.crearVendedor(nombre,edad,cedula,correo,numeroCelular,url,nombreUsuario,clave);
    }

    // crea un administrador
    public Administrador crearAdministrador(String nombre, int edad, String cedula, String correo, String numeroCelular, String nombreUsuario, String clave) throws IOException {
        return modelfactoryController.crearAdministrador(nombre,edad,cedula,correo,numeroCelular,nombreUsuario,clave);
    }
}
