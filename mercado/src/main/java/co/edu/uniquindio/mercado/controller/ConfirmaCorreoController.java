package co.edu.uniquindio.mercado.controller;

public class ConfirmaCorreoController {
    static ModelFactoryController modelfactoryController;

    public ConfirmaCorreoController() {
        modelfactoryController = ModelFactoryController.getInstance();
    }


    public String obtenerCodigoVerificacionCorreo() {
      return  modelfactoryController.obtenerCodigoVerificacionCorreo();
    }
}
