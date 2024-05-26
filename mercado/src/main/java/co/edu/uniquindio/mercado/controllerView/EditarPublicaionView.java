package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.controller.CrearPublicacionController;
import co.edu.uniquindio.mercado.controller.EditarPublicaionController;
import co.edu.uniquindio.mercado.model.Producto;
import co.edu.uniquindio.mercado.model.Publicacion;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EditarPublicaionView implements Initializable {

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

    private EditarPublicaionController editarPublicaionController;
    private Publicacion publicacion;
    private Producto producto;


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
                    File destino = new File("mercado/src/main/resources/co/edu/uniquindio/imagenesUsuario/" + file.getName());
                    Files.copy(file.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Ruta del archivo: " + destino.getAbsolutePath());
                    // la ruta no absoluta se utiliza para poder cargar las imagenes desde cualquier computador
                    System.out.println("Ruta del archivo no absoluta: " + destino.getPath());

                    // Convierte la ruta del archivo a una URL válida
                    String url = destino.toURI().toString();
                    // Carga la imagen en el ImageView
                    Image image = new Image(url);
                    imgUrlProducto.setImage(image);
                    String urlAux = "file:/C:"+destino.toPath();
                    txtUrl.setText(urlAux);
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
            producto.setNombre(txtNombreProducto.getText());
            producto.setPrecio(txtPrecio.getText());
            producto.setUrlImagen(txtUrl.getText());
            producto.setTipoCategoria(comboTipoCategoria.getValue());
            publicacion.setDescripcion(textDescripcion.getText());
            publicacion.setTitulo(txtTitulo.getText());
            publicacion.setProducto(producto);
            editarPublicaionController.editarPublicaion(publicacion);
            cerrarVentana();
        }

    }



    private void mostrarPantalla(){
        txtTitulo.setText(publicacion.getTitulo());
        txtPrecio.setText(publicacion.getProducto().getPrecio());
        txtUrl.setText(publicacion.getProducto().getUrlImagen());
        textDescripcion.setText(publicacion.getDescripcion());
        txtNombreProducto.setText(publicacion.getProducto().getNombre());
        comboTipoCategoria.setValue(publicacion.getProducto().getTipoCategoria());
    }


    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Agrega los valores del enum al MFXComboBox
        comboTipoCategoria.getItems().addAll(TipoCategoria.values());
        comboTipoEstado.getItems().addAll(TipoEstado.values());
         editarPublicaionController = new EditarPublicaionController();
        publicacion=editarPublicaionController.obtenerPublicacionGlobal();
      producto= editarPublicaionController.obtenerProducto(publicacion.getProducto().getId());
      mostrarPantalla();
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
            mostrarMensaje("Se ha modificado la publicacion", "se modifoco la publicaicon", " Se ha modificado correctamente  la publicaicon", Alert.AlertType.INFORMATION);
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
    private void cerrarVentana(){
        Stage stage = (Stage) paneDatos.getScene().getWindow();
        stage.close();
    }
}