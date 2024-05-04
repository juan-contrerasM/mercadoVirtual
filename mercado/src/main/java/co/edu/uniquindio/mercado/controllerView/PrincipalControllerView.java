package co.edu.uniquindio.mercado.controllerView;

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
    private PaneDinamico paneDinamico;

    //coordenadas

    private int layaoutXPanePublicaciones;
    private int layaoutYPanePublicaciones;


    @FXML
    void abrirCrearPublicacion(MouseEvent event) {
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
        paneDinamico = new PaneDinamico();
        layaoutXPanePublicaciones = 26;
        layaoutYPanePublicaciones = 83;

        boxCategoria.getItems().addAll(TipoCategoria.values());
        boxEstado.getItems().addAll(TipoEstado.values());
    }
}