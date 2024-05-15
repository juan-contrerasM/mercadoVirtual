package co.edu.uniquindio.mercado.controller;

import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaSimple;
import co.edu.uniquindio.mercado.model.Vendedor;

public class BuscarAmigosController {
    ModelFactoryController modelFactoryController = new ModelFactoryController();


    public ListaSimple<Vendedor> obtenerListaVendedores() {
        return modelFactoryController.obtenerListaVendedores1();
    }

    public Vendedor obtenerVendedor1() {
        return modelFactoryController.obtenerVendedorGlobal();
    }
}
