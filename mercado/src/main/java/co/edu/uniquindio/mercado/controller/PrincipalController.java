package co.edu.uniquindio.mercado.controller;

import co.edu.uniquindio.mercado.model.Administrador;
import co.edu.uniquindio.mercado.model.Producto;
import co.edu.uniquindio.mercado.model.Publicacion;
import co.edu.uniquindio.mercado.model.Vendedor;

import java.io.IOException;
import java.util.TreeMap;

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

    public TreeMap<String, Publicacion> obtenerPublicaciones() throws IOException {return modelfactoryController.obtenerPublicaiones();
    }

    public TreeMap<String, Publicacion> obtenerPublicacionesUsuario() throws IOException {
        return modelfactoryController.obtenerPublicaionesUsuario();
    }
    public Producto obtenerProductos(int id) throws IOException {
        return modelfactoryController.obtenerProdcuto(id);
    }
}
