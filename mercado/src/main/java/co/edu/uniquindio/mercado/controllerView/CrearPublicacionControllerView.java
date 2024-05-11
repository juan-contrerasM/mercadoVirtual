package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.controller.CrearPublicacionController;
import co.edu.uniquindio.mercado.model.Producto;
import co.edu.uniquindio.mercado.model.enums.TipoCategoria;
import co.edu.uniquindio.mercado.model.enums.TipoEstado;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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
    private CrearPublicacionController crearPublicacionController;


    @FXML
    void cargarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imágenes");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png", "*.gif"));

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(new Stage());

        if (selectedFiles != null) {
            for (File file : selectedFiles) {
                // Copia o mueve el archivo a la ubicación deseada en tu proyecto
                try {
                    File destino = new File("src/main/resources/co/edu/uniquindio/imagenesUsuario/" + file.getName());
                    Files.copy(file.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Ruta del archivo: " + destino.getAbsolutePath());

                    // Convierte la ruta del archivo a una URL válida
                    String url = destino.toURI().toString();
                    // Carga la imagen en el ImageView
                    Image image = new Image(url);
                    imgUrlProducto.setImage(image);
                    txtUrl.setText(url);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al importar imagen: " + e);

                }
            }

        } else {
            mostrarMensaje("Esta imagen no se puede cargar", "Esta imagen no se puede cargar", "No fue posible cargar la imagen seleccionada", Alert.AlertType.ERROR);

        }

    }

    @FXML
    void publicar(ActionEvent event) throws IOException {
        if (verificarCampos()) {
            Producto producto=crearPublicacionController.guadarProducto(txtUrl.getText(),txtPrecio.getText(),txtNombreProducto.getText(),comboTipoEstado.getValue(),comboTipoCategoria.getValue());


        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Agrega los valores del enum al MFXComboBox
        comboTipoCategoria.getItems().addAll(TipoCategoria.values());
        comboTipoEstado.getItems().addAll(TipoEstado.values());
        crearPublicacionController= new CrearPublicacionController();
    }

    private boolean verificarCampos() {
        String mensaje = "";
        if (txtNombreProducto.getText().isEmpty()) {
            mensaje += ("El campo Nombre Producto está vacío.\n");
        }
        if (txtTitulo.getText().isEmpty()) {
            mensaje += ("El campo Título está vacío.\n");
        }
        if (textDescripcion.getText().isEmpty()) {
            mensaje += ("El campo Descripción está vacío.\n");
        }
        if (txtPrecio.getText().isEmpty()) {
            mensaje += ("El campo Precio está vacío.\n");
        } else if (!esNumero(txtPrecio.getText())) {
            mensaje += ("El campo Precio debe contener solo números.\n");
        }
        if (comboTipoCategoria.getSelectionModel().isEmpty()) {
            mensaje += ("Seleccione una Categoría.\n");
        }
        if (comboTipoEstado.getSelectionModel().isEmpty()) {
            mensaje += ("Seleccione un Estado.\n");
        }

        if (!(mensaje.isEmpty())) {
            mostrarMensaje("Faltan campos por llenar", "Faltan campos por llenar", mensaje, Alert.AlertType.INFORMATION);
            return false; // faltan campos por llenar
        } else {
            mostrarMensaje("Se creo la publicacion", "se creo la publicacion", " Se ha creado correftamente la publicaicon", Alert.AlertType.INFORMATION);
            return true;// todos los campos estan llenos
        }
    }

    // VERIFICA QUE EL STRING SE SOLO NUMEROS
    private boolean esNumero(String texto) {
        // Utiliza una expresión regular para verificar si el texto contiene solo números
        return Pattern.matches("\\d+", texto);
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
}


