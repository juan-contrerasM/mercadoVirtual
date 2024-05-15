package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.controller.ConfirmaCorreoController;
import co.edu.uniquindio.mercado.controller.LoginController;
import co.edu.uniquindio.mercado.model.Administrador;
import co.edu.uniquindio.mercado.model.TipoUsuario;
import co.edu.uniquindio.mercado.model.Vendedor;
import co.edu.uniquindio.mercado.modelInterfaz.EmailSender;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
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
    private Label labelRegistrarse;
    private Vendedor vendedor;
    private Administrador administrador;
    private LoginController loginController;

    public Vendedor getVendedor() {
        return vendedor;
    }

    @FXML
    void AbrirVentanaRegistrarse(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registrarUsuarios.fxml"));// nombre de la ventana a aabrir
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            RegistrarVendedoresController controller = loader.getController();// caraga el controllerview de la vista a la que se abre

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // sirve para que los usuarios que tienen cuesta inicien a la al app
    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {
        if (verificarCampos()) {
            if (comboTipoUsuario.getValue() == TipoUsuario.VENDEDOR) {// valida si es un vendedor el que necesita recupera la clave
                 vendedor=loginController.obtenerVendedor(txtNombreUsuario.getText(),null, txtClave.getText());// obtenie la informacio del vendedor
                if (vendedor != null) {// si devuelve null es porque el perfil nofue encontrado
                    loginController.estadoGlobalVendedor(vendedor);
                    abrirVentanaPrincipal();
                    cerrarVentana();
                } else {
                    mostrarMensaje("Sesion incorrecta","No se pudo Iniciar sesion","la consatraseña o el nombre de usario estan incorrectos", Alert.AlertType.INFORMATION);
                }

            }else { // sino es un vendedor es un administrador y hace lo mismo
                administrador = loginController.obtenerAdministrador(txtNombreUsuario.getText(),null, txtClave.getText());
                if (administrador != null) {
                    loginController.estadoGlobalAdministrador(administrador);
                    abrirVentanaPrincipal();
                    cerrarVentana();
                } else {
                    mostrarMensaje("Sesion incorrecta","No se pudo Iniciar sesion","la consatraseña o el nombre de usario estan incorrectos", Alert.AlertType.INFORMATION);
                }

            }

        }

    }

    //sirve para ceerar la ventana
    private void cerrarVentana(){
        Stage stage = (Stage) PaneContenedor.getScene().getWindow();
        stage.close();
    }
    //abre la ventana principal
    private void abrirVentanaPrincipal(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("principalView.fxml"));// nombre de la ventana a aabrir
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            PrincipalControllerView principalControllerView=loader.getController();// caraga el controllerview de la vista a la que se abre
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //verifica que los campos tdos este llenos
    private boolean verificarCampos() {
        String mensaje = "";
        if (txtNombreUsuario.getText().isEmpty()) {
            mensaje += "Para poder enviar el correo debe llenar el campo cedula \n";
        }
        if (txtClave.getText().isEmpty()) {
            mensaje += "para poder enviar el correo debe llenar el campo nombre usuario \n";
        }
        if (comboTipoUsuario.getValue() == null) {
            mensaje += "para poder enviar el correo debe elegir una opcion del tipo de usaurio";
        }
        if (!mensaje.isEmpty()) {
            mostrarMensaje("No se pudo encontrar la cuenta", "faltan campos por llenar", mensaje, Alert.AlertType.INFORMATION);
            return false;
        } else {
            return true;
        }

    }
    //muestra mensajes
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }



    @FXML
    void recuperarContraseña(MouseEvent event) {
       abrirVentaConfirmacionCorreo();// abre la ventana de  recuperar clave
    }

    // abre la ventana para recuperar contraseña
    private void abrirVentaConfirmacionCorreo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("validacionCorreo.fxml"));// nombre de la ventana a aabrir
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            ConfirmarCorreoControllerView confirmarCorreoControllerView =loader.getController();// caraga el controllerview de la vista a la que se abre
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //nicaliza componentes
        comboTipoUsuario.getItems().addAll(TipoUsuario.values());
        loginController=new LoginController();
    }
}