package co.edu.uniquindio.mercado.controller;

import co.edu.uniquindio.mercado.model.Megusta;
import co.edu.uniquindio.mercado.model.Publicacion;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerEstadistica {
    static ModelFactoryController modelfactoryController;

    public ControllerEstadistica() {
        modelfactoryController = ModelFactoryController.getInstance();
    }


    public Publicacion obtenerPublicaion() {
        return modelfactoryController.obtenerPublicacionGlobal();
    }

    public ArrayList<Megusta> obtenerlistPublicaciones() throws IOException {
        return  modelfactoryController.obtenerLikesPersonalizada();
    }
}
