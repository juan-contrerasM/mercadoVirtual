package co.edu.uniquindio.mercado.controller;

import co.edu.uniquindio.mercado.model.Producto;
import co.edu.uniquindio.mercado.model.Publicacion;

import java.io.IOException;

public class ControllerMostrarPublicacion {
    //intancia del model factory
    static ModelFactoryController modelfactoryController;

    public ControllerMostrarPublicacion() {
        modelfactoryController= ModelFactoryController.getInstance();
    }


    public Publicacion obtenerPublicacionGlobal() {
        return modelfactoryController.obtenerPublicacionGlobal();
    }

    public Producto obtenerProdcuto(int id) throws IOException {
        return modelfactoryController.obtenerProdcuto(id);
    }

    public void modificarPublicacionLikes() throws IOException {
        modelfactoryController.modificarPublicaionLikes();
    }

    public void modificarPublicacionComentarios(String mensaje) throws IOException {
        modelfactoryController.modificarPublicaionComentario(mensaje);
    }

    public void filtrarComenatrios() throws IOException {
        modelfactoryController.filtrarComentario();
    }
}
