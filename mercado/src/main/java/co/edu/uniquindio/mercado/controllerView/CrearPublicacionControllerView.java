package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.model.enums.TipoCategoria;
import co.edu.uniquindio.mercado.model.enums.TipoEstado;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CrearPublicacionControllerView implements Initializable {

    @FXML
    private MFXButton btnCargarImagen;

    @FXML
    private MFXButton btnPublicar;

    @FXML
    private MFXLegacyComboBox<TipoCategoria> comboTipoCategoria;

    @FXML
    private MFXLegacyComboBox<TipoEstado> comboTipoEstado;

    @FXML
    private ImageView imgUrlProducto;

    @FXML
    private Pane paneDatos;

    @FXML
    private Pane paneImagen;

    @FXML
    private TextArea textDescripcion;

    @FXML
    private MFXTextField txtNombreProducto;

    @FXML
    private MFXTextField txtPrecio;

    @FXML
    private MFXTextField txtTitulo;

    @FXML
    private MFXTextField txtUrl;

    @FXML
    void cargarImagen(ActionEvent event) {

    }

    @FXML
    void publicar(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Agrega los valores del enum al MFXComboBox
        comboTipoCategoria.getItems().addAll(TipoCategoria.values());
        comboTipoEstado.getItems().addAll(TipoEstado.values());
    }
}


