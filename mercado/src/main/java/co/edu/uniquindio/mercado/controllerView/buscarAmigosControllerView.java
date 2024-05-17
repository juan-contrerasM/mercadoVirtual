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

            paneDinamico = new PaneDinamico();
            layaoutXPaneSugerenciasAmigos = 26;
            layaoutYPaneSugerenciasAmigos = 0;
            crearPaneSugerenciaAmigos(txtBuscarAmigos.getText());
        }

    }

    @FXML
    private Label labelMisAmigos;


    @FXML
    private Pane paneSugerenciaAmigos;
    private PaneDinamico paneDinamico;




    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //mostrarSugerencias();
        //listaVendedores = buscarAmigosController.obtenerListaVendedores();
        //listaVendedores.imprimirLista();
        paneDinamico = new PaneDinamico();
        layaoutXPaneSugerenciasAmigos = 26;
        layaoutYPaneSugerenciasAmigos = 0;
        crearPaneSugerenciaAmigos("");
    }


    private void crearPaneSugerenciaAmigos(String palabra) throws IOException {
        restablcerAjutesPaneSugerenciasAmigos();
        listaVendedores = buscarAmigosController.obtenerListaVendedores();
        Vendedor aux ;
        aux=obtenerVendedor();
        if (!palabra.equals("")){
            for (int i = 0; i < listaVendedores.getTamanio(); i++) {
                Vendedor vendedor = listaVendedores.obtenerValorNodo(i);
                if (!aux.getNombreUsuario().equals(vendedor.getNombreUsuario())) {
                    try {
                        if (palabra.equals(vendedor.getNombreUsuario())  ) {
                            agregarPane(paneDinamico.buildPane(vendedor.getNombreUsuario() + "\n" + vendedor.getTipoUsuario()));
                        }if ( palabra.substring(0,1).equals(vendedor.getNombreUsuario().substring(0,1)) ) {
                            agregarPane(paneDinamico.buildPane(vendedor.getNombreUsuario() + "\n" + vendedor.getTipoUsuario()));
                        }if (palabra.substring(0,2).equals(vendedor.getNombreUsuario().substring(0,2))) {
                            agregarPane(paneDinamico.buildPane(vendedor.getNombreUsuario() + "\n" + vendedor.getTipoUsuario()));
                        }
                    }catch (RuntimeException e){

                    }

                    }



        }



       }else{
            for (int i = 0; i < listaVendedores.getTamanio(); i++) {
                Vendedor vendedor = listaVendedores.obtenerValorNodo(i);
                if (!aux.getNombreUsuario().equals(vendedor.getNombreUsuario())) {
                        agregarPane(paneDinamico.buildPane(vendedor.getNombreUsuario() + "\n" + vendedor.getTipoUsuario()));
                    }


            }


        }


            //  for (Map.Entry<String, Publicacion> entry : mapaPublicaciones.entrySet()) {
//            String clave = entry.getKey();
//            Publicacion publicacion = entry.getValue();
//            agregarPane(paneDinamico.buildPane(publicacion.getTitulo()+"\n"+publicacion.getProducto().getPrecio()+"$",publicacion.getProducto().getUrlImagen(),publicacion.getProducto().getId()));
//            System.out.println(publicacion.getProducto().getId());
//        }




    }


    public Vendedor obtenerVendedor() {
        return buscarAmigosController.obtenerVendedor1();
    }

    private void agregarPane(Pane pane) {
        paneSugerenciaAmigos.getChildren().add(pane);
        pane.setLayoutX(layaoutXPaneSugerenciasAmigos);
        pane.setLayoutY(layaoutYPaneSugerenciasAmigos);
        if (layaoutXPaneSugerenciasAmigos % 100 == 0) {
            layaoutXPaneSugerenciasAmigos -= 274;
            layaoutYPaneSugerenciasAmigos += 255;
            paneSugerenciaAmigos.setPrefHeight(paneSugerenciaAmigos.getPrefHeight() + 500);
        } else {
            layaoutXPaneSugerenciasAmigos += 274;
        }
    }
//    private void agregarPane(Pane pane) {
//        panePublicaciones.getChildren().add(pane);
//        pane.setLayoutX(layaoutXPanePublicaciones);
//        pane.setLayoutY(layaoutYPanePublicaciones);
//        if (layaoutXPanePublicaciones % 100 == 0) {
//            layaoutXPanePublicaciones -= 274;
//            layaoutYPanePublicaciones += 255;
//            panePublicaciones.setPrefHeight(panePublicaciones.getPrefHeight() + 500);
//        } else {
//            layaoutXPanePublicaciones += 274;
//        }
//    }

    private void restablcerAjutesPaneSugerenciasAmigos() {
        paneSugerenciaAmigos.getChildren().clear();
        layaoutXPaneSugerenciasAmigos = 26;
        layaoutYPaneSugerenciasAmigos = 83;
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


