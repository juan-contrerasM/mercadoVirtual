package co.edu.uniquindio.mercado;

import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalController {

    @FXML
    private AnchorPane PanePrincipal;

    @FXML
    private MFXLegacyComboBox<?> boxCategoria;

    @FXML
    private MFXLegacyComboBox<?> boxEstado;

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
    void abrirCrearPublicacion(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("crearPublicacion.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}