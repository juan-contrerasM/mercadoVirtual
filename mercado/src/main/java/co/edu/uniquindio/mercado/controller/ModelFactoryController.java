package co.edu.uniquindio.mercado.controller;


import co.edu.uniquindio.mercado.Mercadod;

public class ModelFactoryController {
    private Mercadod mercadod= new Mercadod();

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    // contrutor
    public ModelFactoryController() {
    }
}
