package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.controller.ConfirmaCorreoController;
import co.edu.uniquindio.mercado.controller.ControllerRegistrarUsuarios;
import co.edu.uniquindio.mercado.model.Administrador;
import co.edu.uniquindio.mercado.model.TipoUsuario;
import co.edu.uniquindio.mercado.model.Vendedor;
import co.edu.uniquindio.mercado.modelInterfaz.EmailSender;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrarVendedoresController implements Initializable {

    @FXML
    private MFXLegacyComboBox<TipoUsuario> boxTipoUsuario;

    @FXML
    private MFXButton btnCrearPerfil;

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
    private ControllerRegistrarUsuarios controllerRegistrarUsuarios;

    //Este metodo crea un perfil
    @FXML
    void crearPerfil(ActionEvent event) throws IOException {
        if (verificarCampos()) {//VERIFICA SI LOS CAMPOS ESTAN LLENOS
            if (boxTipoUsuario.getValue().equals(TipoUsuario.VENDEDOR)) {//VERIFICA SI ES UN PERFIL DE VENDEDOR O ADMINISTRADOR
                Vendedor vendedor = controllerRegistrarUsuarios.crearVendedor(txtNombre.getText(), Integer.parseInt(txtEdad.getText()), txtCedula.getText(), txtCorreo.getText(), txtCelular.getText(), txtNombreUsuario.getText(), txtContrasenia.getText());
                if (vendedor == null) {// si devulve null es porque el perfil ya fue creado
                    mostrarMensaje("El usuario ya existe", "Una cuenta tiene esos mismo datos", "Una cuenta tiene la misma cedula o nombre de usuario", Alert.AlertType.INFORMATION);
                } else {// de lo contrario es porque se creo correctamente el vendedor
                    mostrarMensaje("Usuario Creado", "El perfil fue creado", "Se ha creado correctamente su perfil como " + boxTipoUsuario.getValue(), Alert.AlertType.INFORMATION);
                    cerrarVentana();
                }
            } else {
                Administrador administrador = controllerRegistrarUsuarios.crearAdministrador(txtNombre.getText(), Integer.parseInt(txtEdad.getText()), txtCedula.getText(), txtCorreo.getText(), txtCelular.getText(), txtNombreUsuario.getText(), txtContrasenia.getText());
                if (administrador == null) {
                    mostrarMensaje("El usuario ya existe", "Una cuenta tiene esos mismo datos", "Una cuenta tiene la misma cedula o nombre de usuario", Alert.AlertType.INFORMATION);
                } else {
                    mostrarMensaje("Usuario Creado", "El perfil fue creado", "Se ha creado correctamente su perfil como " + boxTipoUsuario.getValue(), Alert.AlertType.INFORMATION);
                    cerrarVentana();
                }
            }
        }


    }

    //SIRVE PARA CERRAR LA VENTANA CUANDO SEA CREE EL PERFIL
    private void cerrarVentana() {
        Stage stage = (Stage) panePadre.getScene().getWindow();
        stage.close();
    }

    //verifica que lo campos de la interfas se hallan llenado correctamente sino muestra un  mensaje
    private boolean verificarCampos() {
        String mensaje = "";// si algun campo est a vacio se va a concatenar a esta variable un mensaje
        if (txtNombre.getText().isEmpty()) {
            mensaje += "El campo Nombre debe llenarlo\n";
        }
        if (txtCedula.getText().isEmpty()) {
            mensaje += "El campo Cedula debe llenarlo\n";
        }
        if (txtCelular.getText().isEmpty()) {
            mensaje += "El campo celular debe llenarlo\n";
        } else {
            try {
                long numCelular = Long.parseLong(txtCelular.getText());
            } catch (NumberFormatException e) {
                mensaje += "El campo número celular debe ser un número válido\n";
            }
        }
        if (txtContrasenia.getText().isEmpty()) {
            mensaje += "El campo contraseña debe llenarlo\n";
        }
        if (!(validarCorreo(txtCorreo.getText()))) { //valida si el formato del correo es el acertado
            mensaje += "El correo es invalido deb seguir el formato correo@example.com";
        }
        if (txtCorreo.getText().isEmpty()) {
            mensaje += "El campo correo debe  llenarlo\n";
        }
        if (txtEdad.getText().isEmpty()) {
            mensaje += "El campo edad debe llenarlo\n";
        } else {// se muestra un mensaje y se captura un exception si ingrean un string en vez de un numero
            try {
                Integer edad = Integer.parseInt(txtEdad.getText());
            } catch (NumberFormatException e) {
                mensaje += "El campo edad debe ser un número válido\n";
            }
        }
        if (txtNombreUsuario.getText().isEmpty()) {
            mensaje += "El campo Nombre de usuario debe llenarlo\n";
        }
        if (boxTipoUsuario.getValue() == null) {
            mensaje += "Debe elegir una opción en el campo Tipo de usuario\n";
        }
        if (!radioAceptaCondiciones.isSelected()) {
            mensaje += "Debe aceptar las condiciones para crear el perfil\n";
        }
        if (!mensaje.isEmpty()) {// validad si la variable mensaje no esta vacia muestra ese mensaje en panatlla
            mostrarMensaje("El registro no fue exitoso", "Faltan campos", mensaje, Alert.AlertType.INFORMATION);
            return false;
        } else {
            return true;// de lo contrario retunr true y no muestra nada en pantalla
        }
    }

    //valida si el correo es valido
    public static boolean validarCorreo(String correo) {
        // Expresión regular para validar el formato de un correo electrónico
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }


    // es un metedo por el cual mostramos mensajes al usuario
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }


    //inicializamos objetos de la interfaz
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerRegistrarUsuarios = new ControllerRegistrarUsuarios();
        boxTipoUsuario.getItems().addAll(TipoUsuario.values());
    }
}
