package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.controller.ConfirmaCorreoController;
import co.edu.uniquindio.mercado.modelInterfaz.EmailSender;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmarCorreoControllerView implements  Initializable {
    @FXML
    private MFXButton btnVerificar;

    @FXML
    private MFXTextField txtCedula;

    @FXML
    private MFXTextField txtNombreUsuario;
    @FXML
    private AnchorPane paneContenedor;

    @FXML
    void verificar(ActionEvent event) {
        modificarInterfaz();


    }
    private  void modificarInterfaz(){
        // Crear y configurar el nuevo MFXTextField
        MFXTextField txtCodigoCorreo = new MFXTextField("Ingrese el codigo");
        txtCodigoCorreo.setLayoutX(130);
        txtCodigoCorreo.setLayoutY(168);
        txtCodigoCorreo.setStyle("-fx-border-color: black; -fx-text-fill: black");

        // Crear y configurar el nuevo MFXButton
        // Cargar el archivo CSS
        MFXButton nuevoBoton = new MFXButton("Nuevo Botón");
        nuevoBoton.setLayoutX(319);
        nuevoBoton.setLayoutY(168);
        nuevoBoton.setStyle("-fx-border-color: black; -fx-text-fill: black");

        // Agregar el archivo CSS a la lista de hojas de estilo de la escena

        // Eliminar los elementos existentes
        paneContenedor.getChildren().removeAll(btnVerificar, txtCedula, txtNombreUsuario);

        // Agregar los nuevos elementos al contenedor
        paneContenedor.getChildren().addAll(txtCodigoCorreo, nuevoBoton);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}








