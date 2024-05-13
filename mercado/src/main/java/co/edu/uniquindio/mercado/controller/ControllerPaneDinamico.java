package co.edu.uniquindio.mercado.controller;

import java.io.IOException;

public class ControllerPaneDinamico {
    //intancia del model factory
    static ModelFactoryController modelfactoryController;

    public ControllerPaneDinamico() {
        modelfactoryController = ModelFactoryController.getInstance();
    }
    public void guardarPublicacionGlobal(int id) throws IOException {
        modelfactoryController.guardarPublicacionGlobal(id);
    }
}
