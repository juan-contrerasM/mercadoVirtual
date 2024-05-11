package co.edu.uniquindio.mercado.controller;

import co.edu.uniquindio.mercado.model.Producto;
import co.edu.uniquindio.mercado.model.enums.TipoCategoria;
import co.edu.uniquindio.mercado.model.enums.TipoEstado;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;

import java.io.IOException;

public class CrearPublicacionController {
    //instancia del modelfactory
    static   ModelFactoryController modelfactoryController;

    //intancia unica
    public CrearPublicacionController(){
        modelfactoryController=ModelFactoryController.getInstance();
    }


    public Producto guadarProducto(String url, String precio, String nombreProducto, TipoEstado tipoEstado, TipoCategoria comboTipoCategoria) throws IOException {
        return modelfactoryController.crearProducto(url, precio, nombreProducto,  tipoEstado, comboTipoCategoria);
    }
}
