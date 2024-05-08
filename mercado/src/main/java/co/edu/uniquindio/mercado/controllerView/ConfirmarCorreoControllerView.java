package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.controller.ConfirmaCorreoController;
import co.edu.uniquindio.mercado.model.Administrador;
import co.edu.uniquindio.mercado.model.TipoUsuario;
import co.edu.uniquindio.mercado.model.Vendedor;
import co.edu.uniquindio.mercado.modelInterfaz.EmailSender;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmarCorreoControllerView implements Initializable {


    @FXML
    private MFXComboBox<TipoUsuario> boxTipoUsuario;

    @FXML
    private MFXButton btnCambiar;

    @FXML
    private MFXButton btnEnviar;

    @FXML
    private MFXButton btnVerificar;

    @FXML
    private Label labelCambiarContrasenia;

    @FXML
    private Label labelCodigo;

    @FXML
    private Label labelDatos;

    @FXML
    private AnchorPane paneContenedor;

    @FXML
    private MFXTextField txtCedula;

    @FXML
    private MFXTextField txtConfirmacion;

    @FXML
    private MFXTextField txtDigito1;

    @FXML
    private MFXTextField txtDigito2;

    @FXML
    private MFXTextField txtDigito3;

    @FXML
    private MFXTextField txtDigito4;

    @FXML
    private MFXTextField txtNombreUsuario;

    @FXML
    private MFXTextField txtcontrasenniaNueva;

    private ConfirmaCorreoController confirmaCorreoController;
    private String codigo = "";
    private Vendedor vendedor = new Vendedor();
    private Administrador administrador= new Administrador();
    Label label;



    // este metodo  encuentra la cuenta del usuario para poder cambiar la contraseña
    @FXML
    void EnviarCorreo(ActionEvent event) throws IOException {
        if (verificarCampos()) {
            if (boxTipoUsuario.getValue() == TipoUsuario.VENDEDOR) {// valida si es un vendedor el que necesita recupera la clave
                vendedor = confirmaCorreoController.obtenerVendedor(txtNombreUsuario.getText(), txtCedula.getText());// obtenie la informacio del vendedor
                if (vendedor != null) {// si devuelve null es porque el perfil nofue encontrado
                    codigo = confirmaCorreoController.obtenerCodigoVerificacionCorreo();// pide el codigo aleatprio
                    EmailSender.enviarCorreo(codigo, vendedor.getCorreo());// envia el correo
                    habilitarCamposCodigo(); // hablita los siguiente campos
                    mostrarMensaje("Codigo enviado","El codigo fue enviado","El codigo fue enviado corretamente", Alert.AlertType.INFORMATION);
                } else {
                    mostrarMensaje("Perfil no encontrado", "Perfil no encontrado", "no hay un perfil que concuerden con esos datos que ingresaste", Alert.AlertType.INFORMATION);
                }

            }else { // sino es un vendedor es un administrador y hace lo mismo
                administrador = confirmaCorreoController.obtenerAdministrador(txtNombreUsuario.getText(), txtCedula.getText(),null);
                if (administrador != null) {
                    codigo = confirmaCorreoController.obtenerCodigoVerificacionCorreo();
                    EmailSender.enviarCorreo(codigo, administrador.getCorreo());
                    habilitarCamposCodigo();
                    mostrarMensaje("Codigo enviado","El codigo fue enviado","El codigo fue enviado corretamente", Alert.AlertType.INFORMATION);
                } else {
                    mostrarMensaje("Perfil no encontrado", "Perfil no encontrado", "no hay un perfil que concuerden con esos datos que ingresaste", Alert.AlertType.INFORMATION);
                }

            }

        }


    }

    private void habilitarCamposCodigo() {
        // no se deja editar ni hacer acciones en la seccion
        txtNombreUsuario.setDisable(true);
        boxTipoUsuario.setDisable(true);
        txtCedula.setDisable(true);
        btnEnviar.setDisable(true);
        label.setVisible(false);


        //se hacen visibles lo campos de la seccion codigo
        txtDigito1.setVisible(true);
        txtDigito2.setVisible(true);
        txtDigito3.setVisible(true);
        txtDigito4.setVisible(true);
        btnVerificar.setVisible(true);
        labelCodigo.setVisible(true);
    }

    private void habilitarCamposContrasenia() {

        //desabilita la seccion del codigo
        txtDigito1.setDisable(true);
        txtDigito2.setDisable(true);
        txtDigito3.setDisable(true);
        txtDigito4.setDisable(true);
        btnVerificar.setDisable(true);



        //se hacen visibles los campos de seccion cambio contrasenia
        labelCambiarContrasenia.setVisible(true);
        txtConfirmacion.setVisible(true);
        txtcontrasenniaNueva.setVisible(true);
        btnCambiar.setVisible(true);

    }


    //verifica que el cosigo se igual que escribio el usuario

    @FXML
    void verificar(ActionEvent event) {
        String codigoInterfaz = txtDigito1.getText() + txtDigito2.getText() + txtDigito3.getText() + txtDigito4.getText();
        if (codigo.equals(codigoInterfaz)) {
            habilitarCamposContrasenia();
        }
    }


    // verifica wue los campso esten llenos
    private boolean verificarCampos() {
        String mensaje = "";
        if (txtCedula.getText().isEmpty()) {
            mensaje += "Para poder enviar el correo debe llenar el campo cedula \n";
        }
        if (txtNombreUsuario.getText().isEmpty()) {
            mensaje += "para poder enviar el correo debe llenar el campo nombre usuario \n";
        }
        if (boxTipoUsuario.getValue() == null) {
            mensaje += "para poder enviar el correo debe elegir una opcion del tipo de usaurio";
        }
        if (!mensaje.isEmpty()) {
            mostrarMensaje("No se pudo encontrar la cuenta", "falatn campos por llenar", mensaje, Alert.AlertType.INFORMATION);
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        confirmaCorreoController = new ConfirmaCorreoController();// controller
        boxTipoUsuario.getItems().addAll(TipoUsuario.values());// se inicializa valores del combo box
        // se deshabilitan los campos  para seguri el paso a paso
        txtDigito1.setVisible(false);
        txtDigito2.setVisible(false);
        txtDigito3.setVisible(false);
        txtDigito4.setVisible(false);
        btnVerificar.setVisible(false);
        btnCambiar.setVisible(false);
        labelCodigo.setVisible(false);
        labelCambiarContrasenia.setVisible(false);
        txtConfirmacion.setVisible(false);
        txtcontrasenniaNueva.setVisible(false);
        // se muestra la marca de la empresa
         label = new Label("TRADEHUB");
        label.setStyle("-fx-font-size: 90px; -fx-font-weight: bold;");
        label.setLayoutX(100); // Establece la posición en el eje X
        label.setLayoutY(302); // Establece la posición en el eje Y
        paneContenedor.getChildren().add(label);
    }


    // cambia la contraseña y  guarda el administrado o vendedor
    public void cambiar(ActionEvent event) throws IOException {
        if(txtConfirmacion.getText().equals(txtcontrasenniaNueva.getText())){// verifica que las constraseñas que ingreso el usuario sea iguales
            if(boxTipoUsuario.getValue().equals(TipoUsuario.VENDEDOR)) {
                vendedor.setContrasenia(txtConfirmacion.getText());
                confirmaCorreoController.cambiarContraseniaVendedor(vendedor);// va y guadra el vendedor
            }else {
                administrador.setContrasenia(txtConfirmacion.getText());
                confirmaCorreoController.cambiarContraseniaAdminsitrador(administrador);
            }
            mostrarMensaje("Se cambio la contraseña", "Se cambio la contraseña","Tu contraseña ha sido cambiada", Alert.AlertType.INFORMATION);
            cerrarVentana();
        }else {
            mostrarMensaje("Contraseña","No son iguales las contraseñas","no son iguales las contraseñas que escribiste", Alert.AlertType.INFORMATION);
        }
    }


    // cierra la ventana
    private void cerrarVentana(){
        Stage stage = (Stage) paneContenedor.getScene().getWindow();
        stage.close();
    }
}






