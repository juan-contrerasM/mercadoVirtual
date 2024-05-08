package co.edu.uniquindio.mercado.controller;

import co.edu.uniquindio.mercado.model.Administrador;
import co.edu.uniquindio.mercado.model.Vendedor;

import java.io.IOException;

public class LoginController {
    //instancia del modelfactory
    static   ModelFactoryController modelfactoryController;

    //intancia unica
    public LoginController(){
        modelfactoryController=ModelFactoryController.getInstance();
    }

    public Vendedor obtenerVendedor(String nombreUsuario, String cedula, String clave) throws IOException {
        return modelfactoryController.obtenerVendedor(nombreUsuario,cedula,clave);
    }
    public void estadoGlobalVendedor(Vendedor vendedor) {
        modelfactoryController.estadoGlobalVendedor(vendedor);
    }
    public Administrador obtenerAdministrador(String nombreUsuario, String cedula, String clave) throws IOException {
        return modelfactoryController.obtenerAdministrador(nombreUsuario,cedula,clave);
    }



    public void estadoGlobalAdministrador(Administrador administrador) {
        modelfactoryController.estadoGlobalAdministrador(administrador);
    }
}
