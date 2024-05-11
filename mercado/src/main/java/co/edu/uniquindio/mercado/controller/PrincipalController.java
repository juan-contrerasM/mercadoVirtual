package co.edu.uniquindio.mercado.controller;

import co.edu.uniquindio.mercado.model.Administrador;
import co.edu.uniquindio.mercado.model.Vendedor;

public class PrincipalController {
    //instancia del modelfactory
    static   ModelFactoryController modelfactoryController;

    //intancia unica
    public PrincipalController(){
        modelfactoryController=ModelFactoryController.getInstance();
    }


    public Vendedor obtenerVendedorGlobal() {
        return modelfactoryController.obtenerVendedorGlobal();
    }

    public Administrador obtenerAdministradorGlobal() {
        return  modelfactoryController.obtenerAdministradorGlobal();
    }
}
