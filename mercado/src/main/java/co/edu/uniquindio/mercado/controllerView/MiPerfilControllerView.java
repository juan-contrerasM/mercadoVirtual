package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.controller.MiPerfilController;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.Arbol;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaSimple;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.NodoArbol;
import co.edu.uniquindio.mercado.model.SolicitudesAmistad;
import co.edu.uniquindio.mercado.model.Vendedor;
import io.github.palexdev.mfxcore.controls.Label;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MiPerfilControllerView implements Initializable {

    MiPerfilController miPerfilController = new MiPerfilController();

    @FXML
    private ImageView imgPerfil;

    @FXML
    private Label labelCorreo;

    @FXML
    private Label labelEdad;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelUsuario;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vendedor vendedor = obtenerVendedor();
        inicializarCampos(vendedor);
        paneDinamico = new PaneDinamico();
        layaoutXPaneAmigos = 26;
        layaoutYPaneAmigos = 0;
        try {
            mostrarAmiigos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void mostrarAmiigos() throws IOException {
        ListaSimple<Vendedor> listaAmigos;

        restablcerAjutesPaneSugerenciasAmigos();
        Vendedor aux = obtenerVendedor();
        listaAmigos=aux.getAmigos();
        System.out.println("tamanño de lista amigos"+aux.getAmigos().getTamanio());

        System.out.println("holaaaaaaa");
        for (int i =0;i<listaAmigos.getTamanio();i++) {
            System.out.println(1);
            System.out.println(listaAmigos.obtenerValorNodo(i).getNombreUsuario());
            agregarPane(paneDinamico.buildPane4(listaAmigos.obtenerValorNodo(i)));

        }






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


    public void inicializarCampos(Vendedor vendedor){
        labelNombre.setText(vendedor.getNombre());
        labelCorreo.setText(vendedor.getCorreo());
        labelEdad.setText(String.valueOf(vendedor.getEdad()));
        labelUsuario.setText(vendedor.getNombreUsuario());
        try {
            Image nueva  = new Image(vendedor.getUrlImg());
            imgPerfil.setImage(nueva);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());

        }


    }
    public Vendedor obtenerVendedor() {
        return miPerfilController.obtenerVendedor1();
    }

}

