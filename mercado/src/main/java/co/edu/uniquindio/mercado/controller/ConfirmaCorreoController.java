package co.edu.uniquindio.mercado.controller;

import co.edu.uniquindio.mercado.model.Administrador;
import co.edu.uniquindio.mercado.model.Vendedor;

import java.io.IOException;

public class ConfirmaCorreoController {

    //intancia del model factory
    static ModelFactoryController modelfactoryController;

    public ConfirmaCorreoController() {
        modelfactoryController = ModelFactoryController.getInstance();
    }


    //obtiene codigo par acmabiar la clave la qu ele llega el correo al usuario
    public String obtenerCodigoVerificacionCorreo() {
      return  modelfactoryController.obtenerCodigoVerificacionCorreo();
    }

    //-------------------------- vendedor-----------------------------
    public Vendedor obtenerVendedor(String nombreUsuario, String cedula) throws IOException {
        return modelfactoryController.obtenerVendedor(nombreUsuario,cedula,null);
    }

    public void cambiarContraseniaVendedor(Vendedor vendedor) throws IOException {
        modelfactoryController.cambiarContraseniaVendedor( vendedor);
    }

    //---------------------------administrador---------------------------------------
    public Administrador obtenerAdministrador(String nombreUsuario, String cedula,String clave) throws IOException {
        return modelfactoryController.obtenerAdministrador(nombreUsuario,cedula,null);
    }
    public void cambiarContraseniaAdminsitrador(Administrador adminsitrador) throws IOException {
        modelfactoryController.cambiarContraseniaAdmisnitrador( adminsitrador);
    }

}
