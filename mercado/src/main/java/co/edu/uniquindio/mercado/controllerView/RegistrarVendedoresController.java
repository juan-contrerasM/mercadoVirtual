package co.edu.uniquindio.mercado.controllerView;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class RegistrarVendedoresController {

    @FXML
    private MFXLegacyComboBox<?> boxTipoUsuario;

    @FXML
    private MFXButton btnCrearPerfil;

    @FXML
    private Pane paneInformacionUsuario;

    @FXML
    private AnchorPane panePadre;

    @FXML
    private MFXRadioButton radioAceptaCondiciones;

    @FXML
    private MFXTextField txtCedula;

    @FXML
    private MFXTextField txtCelular;

    @FXML
    private MFXTextField txtContrasenia;

    @FXML
    private MFXTextField txtCorreo;

    @FXML
    private MFXTextField txtEdad;

    @FXML
    private MFXTextField txtNombre;

    @FXML
    private MFXTextField txtNombreUsuario;

    @FXML
    void crearPerfil(ActionEvent event) {

    }

}
