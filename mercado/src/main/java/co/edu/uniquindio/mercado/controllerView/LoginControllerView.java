package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.model.TipoUsuario;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginControllerView implements Initializable {

    @FXML
    private AnchorPane PaneContenedor;

    @FXML
    private MFXButton btnInicoSesion;

    @FXML
    private Label lblRecuperarClave;

    @FXML
    private MFXPasswordField txtClave;

    @FXML
    private MFXTextField txtNombreUsuario;
    @FXML
    private MFXLegacyComboBox<TipoUsuario> comboTipoUsuario;
    @FXML
    void iniciarSesion(ActionEvent event) {

    }

    @FXML
    void recuperarContraseña(MouseEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboTipoUsuario.getItems().addAll(TipoUsuario.values());
    }
}