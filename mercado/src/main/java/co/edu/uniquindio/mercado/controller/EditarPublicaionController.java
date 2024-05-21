package co.edu.uniquindio.mercado.controller;

import co.edu.uniquindio.mercado.model.Producto;
import co.edu.uniquindio.mercado.model.Publicacion;

import java.io.IOException;

public class EditarPublicaionController {
    static ModelFactoryController modelfactoryController;

    public EditarPublicaionController() {
        modelfactoryController = ModelFactoryController.getInstance();
    }


    public Publicacion obtenerPublicacionGlobal() {
        return modelfactoryController.obtenerPublicacionGlobal();

    }

    public Producto obtenerProducto(int id ) throws IOException {
        return  modelfactoryController.obtenerProdcuto(id);
    }

    public void editarPublicaion(Publicacion publicacion) throws IOException {
        modelfactoryController.editarPublicaion(publicacion);
    }
}
