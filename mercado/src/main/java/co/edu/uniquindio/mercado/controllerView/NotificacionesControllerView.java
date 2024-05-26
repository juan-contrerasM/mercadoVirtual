package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.controller.NotificacionesController;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaSimple;
import co.edu.uniquindio.mercado.model.SolicitudesAmistad;
import co.edu.uniquindio.mercado.model.Vendedor;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class NotificacionesControllerView implements Initializable {
    ListaSimple<SolicitudesAmistad> listaSolicitudes;



    @FXML
    private Pane paneAmigos;

    @FXML
    private Pane panePrincipalBuscarAmigos;
    private PaneDinamico paneDinamico;
    private int layaoutXPaneAmigos;
    private int layaoutYPaneAmigos;
    private void restablcerAjutesPaneSugerenciasAmigos() {
        paneAmigos.getChildren().clear();
        layaoutXPaneAmigos = 26;
        layaoutYPaneAmigos = 83;
    }

    NotificacionesController notificacionesController = new NotificacionesController();

    public Vendedor obtenerVendedor() {
        return notificacionesController.obtenerVendedor1();
    }
    private void agregarPane(Pane pane) {
        paneAmigos.getChildren().add(pane);
        pane.setLayoutX(layaoutXPaneAmigos);
        pane.setLayoutY(layaoutYPaneAmigos);
        if (layaoutXPaneAmigos % 100 == 0) {
            layaoutXPaneAmigos -= 274;
            layaoutYPaneAmigos += 255;
            paneAmigos.setPrefHeight(paneAmigos.getPrefHeight() + 500);
        } else {
            layaoutXPaneAmigos += 274;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneDinamico = new PaneDinamico();
        layaoutXPaneAmigos = 26;
        layaoutYPaneAmigos = 0;
        try {
            notificaciones();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void notificaciones() throws IOException {
        ListaSimple<SolicitudesAmistad> listaSolicitudes;

        restablcerAjutesPaneSugerenciasAmigos();
        Vendedor aux = obtenerVendedor();
        listaSolicitudes= aux.getSolicitudesRecibidas();

        for (int i =0;i<listaSolicitudes.getTamanio();i++) {

            agregarPane(paneDinamico.buildPane3(listaSolicitudes.obtenerValorNodo(i)));

        }



    }

}
