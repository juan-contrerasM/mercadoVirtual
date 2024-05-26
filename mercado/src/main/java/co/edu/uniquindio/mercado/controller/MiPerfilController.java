package co.edu.uniquindio.mercado.controller;

import co.edu.uniquindio.mercado.model.Vendedor;

public class MiPerfilController {

    ModelFactoryController modelFactoryController = new ModelFactoryController();

    public  Vendedor obtenerVendedor1() {
        return modelFactoryController.obtenerVendedorGlobal();

    }
}
