package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.controller.BuscarAmigosController;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaSimple;
import co.edu.uniquindio.mercado.model.Publicacion;
import co.edu.uniquindio.mercado.model.Vendedor;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import lombok.SneakyThrows;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class buscarAmigosControllerView implements Initializable {

    BuscarAmigosController buscarAmigosController = new BuscarAmigosController();
    private int layaoutXPaneSugerenciasAmigos;
    private int layaoutYPaneSugerenciasAmigos;
    ListaSimple<Vendedor> listaVendedores;

    @FXML
    private Pane panePrincipalBuscarAmigos;

    @FXML
    private MFXTextField txtBuscarAmigos;

    @FXML
    void buscarPersonaEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("si");
            txtBuscarAmigos.setText("");
            mostrarSugerencias();
            listaVendedores = buscarAmigosController.obtenerListaVendedores();
            System.out.println("ImprimiendoLista");
            listaVendedores.imprimirLista();
            System.out.println("ImprimiendoLista");
        }

    }

    @FXML
    private Label labelMisAmigos;


    @FXML
    private Pane paneSugerenciaAmigos;


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarSugerencias();
        listaVendedores = buscarAmigosController.obtenerListaVendedores();
        System.out.println("ImprimiendoLista");
        listaVendedores.imprimirLista();
        System.out.println("ImprimiendoLista");


    }

    public void mostrarSugerencias() throws IOException {
        paneSugerenciaAmigos.getChildren().clear();
        layaoutXPaneSugerenciasAmigos = 500;
        layaoutYPaneSugerenciasAmigos = 500;
    }


//    private  void crearPanePublicaion() throws IOException {
//        //obtenemos denuevo las publicaiones creadas
//        restablcerAjutesPanePublicaiones();
//        mapaPublicaciones=principalController.obtenerPublicaciones();
//        for (Map.Entry<String, Publicacion> entry : mapaPublicaciones.entrySet()) {
//            String clave = entry.getKey();
//            Publicacion publicacion = entry.getValue();
//            agregarPane(paneDinamico.buildPane(publicacion.getTitulo()+"\n"+publicacion.getProducto().getPrecio()+"$",publicacion.getProducto().getUrlImagen(),publicacion.getProducto().getId()));
//            System.out.println(publicacion.getProducto().getId());
//        }
//    }
}


