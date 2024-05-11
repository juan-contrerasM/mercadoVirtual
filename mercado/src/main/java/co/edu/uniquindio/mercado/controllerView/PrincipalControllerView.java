package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.controller.PrincipalController;
import co.edu.uniquindio.mercado.model.Administrador;
import co.edu.uniquindio.mercado.model.Vendedor;
import co.edu.uniquindio.mercado.model.enums.TipoCategoria;
import co.edu.uniquindio.mercado.model.enums.TipoEstado;
import co.edu.uniquindio.mercado.modelInterfaz.PaneDinamico;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalControllerView implements Initializable {

    @FXML
    private AnchorPane PanePrincipal;

    @FXML
    private MFXButton btnRefescar;

    @FXML
    private MFXLegacyComboBox<TipoCategoria> boxCategoria;

    @FXML
    private MFXLegacyComboBox<TipoEstado> boxEstado;

    @FXML
    private ImageView imgAmigos;

    @FXML
    private ImageView imgEstadistica;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ImageView imgLupa;

    @FXML
    private ImageView imgMensajes;

    @FXML
    private ImageView imgSuma;

    @FXML
    private Pane paneAgregarPublicacion;

    @FXML
    private Pane paneBuscador;

    @FXML
    private Pane paneEstadisticas;

    @FXML
    private Pane paneHerramientas;

    @FXML
    private Pane paneMensajes;

    @FXML
    private Pane panePublicaciones;

    @FXML
    private MFXTextField txtBuscador;

    @FXML
    private MFXTextField txtMaxFiltro;

    @FXML
    private MFXTextField txtMinFiltro;

    @FXML
    private MFXButton btnCerrar;
    @FXML
    private Label lblNombreUsuario;
    private PaneDinamico paneDinamico;

    //coordenadas

    private int layaoutXPanePublicaciones;
    private int layaoutYPanePublicaciones;

    // controller
    PrincipalController principalController;

    private Vendedor vendedorGlobal;

    private Administrador administradorGlobal;

    @FXML
    void abrirCrearPublicacion(MouseEvent event) {

        // sirve para abrir ventanas
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("crearPublicacion.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            CrearPublicacionControllerView controller = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void refrescarPublicaciones(ActionEvent event) {
        agregarPane(paneDinamico.buildPane());
    }


    //sirve para agregar dinamicamente paneles en el panePublicaciones
    private void agregarPane(Pane pane) {
        panePublicaciones.getChildren().add(pane);
        pane.setLayoutX(layaoutXPanePublicaciones);
        pane.setLayoutY(layaoutYPanePublicaciones);
        if (layaoutXPanePublicaciones % 100 == 0) {
            layaoutXPanePublicaciones -= 274;
            layaoutYPanePublicaciones += 255;
            panePublicaciones.setPrefHeight(panePublicaciones.getPrefHeight() + 500);
        } else {
            layaoutXPanePublicaciones += 274;
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // inicializa valores de componentes de la interfaz
        paneDinamico = new PaneDinamico();
        layaoutXPanePublicaciones = 26;
        layaoutYPanePublicaciones = 83;
        boxCategoria.getItems().addAll(TipoCategoria.values());
        boxEstado.getItems().addAll(TipoEstado.values());
        principalController= new PrincipalController();// controller de la clase

        // obtiene el vendedor o administrado que haya iniciado sesion
        vendedorGlobal=principalController.obtenerVendedorGlobal();
        administradorGlobal=principalController.obtenerAdministradorGlobal();
        cambiarNombreLabel();
    }
    private void  cambiarNombreLabel(){
        if(vendedorGlobal!=null){
            lblNombreUsuario.setText(vendedorGlobal.getNombreUsuario());
        }else {
            lblNombreUsuario.setText(administradorGlobal.getNombreUsuario());
        }
    }




    //cierra la ventana  y abre la de login
    @FXML
    void cerrarSesion(ActionEvent event) {
        Stage stage = (Stage) PanePrincipal.getScene().getWindow();
        stage.close();
        abrirLogin();// abre la ventana de login
    }


    private  void abrirLogin() {
        // sirve para abrir ventanas
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            LoginControllerView loginControllerView = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}