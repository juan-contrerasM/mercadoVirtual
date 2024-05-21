package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.controller.ControllerMostrarPublicacion;
import co.edu.uniquindio.mercado.controller.EditarPublicaionController;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.Pila;
import co.edu.uniquindio.mercado.model.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.SneakyThrows;

public class MostrarPublicaionController implements Initializable {

    @FXML
    private MFXButton btnVolver;

    @FXML
    private ImageView imgProducto;

    @FXML
    private Label lblMensaje;
    @FXML
    private MFXButton btnComentar;

    @FXML
    private Pane paneComentarios;

    @FXML
    private ImageView imgComentario;

    @FXML
    private ImageView imgLike;


    @FXML
    private Label lblContadorComentarios;

    @FXML
    private Label lblContadorMegusta;

    @FXML
    private Label lblContadorVisualizaciones;


    @FXML
    private MFXTextField txtCoemnatrio;

    @FXML
    private AnchorPane panePadre;

    private int layoutxComentario;
    private int layaoutYComentario;

    private Producto producto;

    private ControllerMostrarPublicacion controllerMostrarPublicacion;
    private Publicacion publicacion;
    private PaneDinamico paneDinamico;

    @FXML
    void volver(ActionEvent event) throws IOException {
        imgProducto.setImage(null);
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();

    }

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerMostrarPublicacion = new ControllerMostrarPublicacion();
        publicacion = controllerMostrarPublicacion.obtenerPublicacionGlobal();
        producto= controllerMostrarPublicacion.obtenerProdcuto(publicacion.getProducto().getId());
        publicacion.setProducto(producto);
        Image image = new Image(publicacion.getProducto().getUrlImagen());
        imgProducto.setImage(image);
        String mensaje = publicacion.getTitulo() + "\n" + publicacion.getDescripcion() + "\nFecha de publicaion: " + publicacion.getDiaPublicado() + " hora de publicaion: " + publicacion.getHoraPublicacion() + "\nPublicado por: " + publicacion.getVendedor().getNombreUsuario() + "\nNombre del producto: " + publicacion.getProducto().getPrecio() + "\nCategoria: " + publicacion.getProducto().getTipoCategoria() + "\nEstado: " + publicacion.getProducto().getTipoEstado();
        lblMensaje.setText(mensaje);
        lblContadorMegusta.setText(String.valueOf(publicacion.getContadorMegusta()));
        lblContadorVisualizaciones.setText(String.valueOf(publicacion.getVisualizacion()));
        lblContadorComentarios.setText(String.valueOf(publicacion.getContadorComentarios()));
        paneDinamico=new PaneDinamico();
        layoutxComentario=10;
        layaoutYComentario=0;
         controllerMostrarPublicacion.filtrarComenatrios();
        cargarComentario(publicacion.getListComentario());
        Vendedor vendedor= controllerMostrarPublicacion.obtenerVendedorGlobal();

        //---------------copiar y pegar--------------------------------
        if(publicacion.getVendedor().getNombreUsuario().equals(vendedor.getNombreUsuario())){
            //para agregar imagen y label estadistica
            URL imageUrl = getClass().getResource("/co/edu/uniquindio/mercado/imagenes/estadistica.png");
            Image imagen = new Image(imageUrl.toString());
            ImageView imageView = new ImageView(imagen);
            imageView.setLayoutX(20); // Posición X
            imageView.setLayoutY(30); // Posición Y

            // Ajustar el tamaño de la imagen si es necesario
            imageView.setFitWidth(56); // Ancho deseado de la imagen
            imageView.setFitHeight(40); // Alto deseado de la imagen
            // Añadir evento de clic
            imageView.setOnMouseClicked(event -> {
                abrirInterfazEdtaidtica();
                cerrarVentana();
            });
            Label lbstadistica = new Label("Estadistica");
            lbstadistica.setLayoutY(77);
            lbstadistica.setLayoutX(20);


            //para agregar la imagen y label editar

            URL imageUrlEditiar = getClass().getResource("/co/edu/uniquindio/mercado/imagenes/imgeditar.png");
            Image imagenEditar = new Image(imageUrlEditiar.toString());
            ImageView imageViewEditar = new ImageView(imagenEditar);
            imageViewEditar.setLayoutX(20); // Posición X
            imageViewEditar.setLayoutY(120); // Posición Y

            // Ajustar el tamaño de la imagen si es necesario
            imageViewEditar.setFitWidth(56); // Ancho deseado de la imagen
            imageViewEditar.setFitHeight(40); // Alto deseado de la imagen
            // Añadir evento de clic
            imageViewEditar.setOnMouseClicked(event -> {
                abrirInterfazEditar();
                cerrarVentana();
            });
            Label lbEditar = new Label("Editar");
            lbEditar.setLayoutY(166);
            lbEditar.setLayoutX(20);
            panePadre.getChildren().add(imageViewEditar);
            panePadre.getChildren().add(lbEditar);
            panePadre.getChildren().add(imageView);
            panePadre.getChildren().add(lbstadistica);

        }
    }
    private  void cerrarVentana(){
        Stage stage = (Stage) panePadre.getScene().getWindow();
        stage.close();

    }

    private void abrirInterfazEdtaidtica() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("estadisticaView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Obtener el controlador
            EstadisticaControllerView  controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void abrirInterfazEditar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editarPublicacion.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Obtener el controlador
            EditarPublicaionView controller = loader.getController();
            // Aquí puedes utilizar el controlador para inicializar datos o pasar parámetros si es necesario
        } catch (IOException e) {
            e.printStackTrace();
        }
    }








    public void cargarComentario(Pila<Comentario> comentarios){
        int tamanio= comentarios.getTamanio();
        Comentario comentario= new Comentario();
        for (int i = 0; i < tamanio; i++) {
            comentario=comentarios.desapilar();
            agregarPane(paneDinamico.buildPaneComentario(publicacion.getVendedor().getNombreUsuario(),comentario.getMensaje(), comentario.getFecha()));

        }

    }




    public void agregarPane(Pane pane){
        // Ajusta el layout del Pane antes de agregarlo a la interfaz
        pane.setLayoutX(layoutxComentario);
        pane.setLayoutY(layaoutYComentario);

        // Incrementa la posición Y para la próxima adición
        layaoutYComentario += 60;

        // Ahora agrega el Pane al contenedor
        paneComentarios.getChildren().add(pane);
        paneComentarios.setPrefHeight(paneComentarios.getPrefHeight() + 100);
    }
    @FXML
    void meGusta(MouseEvent event) throws IOException {
        controllerMostrarPublicacion.modificarPublicacionLikes();
        lblContadorMegusta.setText(String.valueOf(publicacion.getContadorMegusta()));
    }
    @FXML
    void comentar(ActionEvent event) throws IOException {
        agregarPane(paneDinamico.buildPaneComentario(publicacion.getVendedor().getNombreUsuario(),txtCoemnatrio.getText(), LocalDate.now()));
        controllerMostrarPublicacion.modificarPublicacionComentarios(txtCoemnatrio.getText());
        lblContadorComentarios.setText(String.valueOf(publicacion.getContadorComentarios()));

    }



}
