package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.controller.PrincipalController;
import co.edu.uniquindio.mercado.model.Administrador;
import co.edu.uniquindio.mercado.model.Producto;
import co.edu.uniquindio.mercado.model.Publicacion;
import co.edu.uniquindio.mercado.model.Vendedor;
import co.edu.uniquindio.mercado.model.enums.TipoCategoria;
import co.edu.uniquindio.mercado.model.enums.TipoEstado;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class PrincipalControllerView implements Initializable {

    @FXML
    private AnchorPane PanePrincipal;

    @FXML
    private MFXButton btnRefescar;

    @FXML
    private MFXLegacyComboBox<TipoCategoria> boxCategoria;

    @FXML
    private MFXLegacyComboBox<TipoEstado> boxEstado;

    @FXML
    private ImageView imgAmigos;

    @FXML
    private ImageView imgEstadistica;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ImageView imgLupa;

    @FXML
    private ImageView imgMensajes;

    @FXML
    private ImageView imgSuma;

    @FXML
    private Pane paneAgregarPublicacion;

    @FXML
    private Pane paneBuscador;

    @FXML
    private Pane paneEstadisticas;

    @FXML
    private Pane paneHerramientas;

    @FXML
    private Pane paneMensajes;

    @FXML
    private Pane panePublicaciones = new Pane();


    @FXML
    private MFXTextField txtBuscador;

    @FXML
    private MFXTextField txtMaxFiltro;
    @FXML
    private ImageView imgMe;

    @FXML
    private MFXTextField txtMinFiltro;

    @FXML
    private MFXButton btnCerrar;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Label lblPublicaiones;
    private PaneDinamico paneDinamico;

    //coordenadas

    private int layaoutXPanePublicaciones;
    private int layaoutYPanePublicaciones;

    // controller
    PrincipalController principalController;

    private Vendedor vendedorGlobal;

    private Administrador administradorGlobal;
    private TreeMap<String, Publicacion>mapaPublicaciones;

    Stage ventanaCrearPublicacion;
    @FXML
    void abrirCrearPublicacion(MouseEvent event) {

        // sirve para abrir ventanas
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("crearPublicacion.fxml"));
            Parent root = loader.load();
            ventanaCrearPublicacion = new Stage();
            ventanaCrearPublicacion.setScene(new Scene(root));
            ventanaCrearPublicacion.show();
            CrearPublicacionControllerView controller = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void refrescarPublicaciones(ActionEvent event) throws IOException {
       crearPanePublicaion();
    }
    private  void crearPanePublicaion() throws IOException {
        //obtenemos denuevo las publicaiones creadas
        restablcerAjutesPanePublicaiones();
        mapaPublicaciones=principalController.obtenerPublicaciones();
        for (Map.Entry<String, Publicacion> entry : mapaPublicaciones.entrySet()) {
            String clave = entry.getKey();
            Publicacion publicacion = entry.getValue();
            agregarPane(paneDinamico.buildPane(publicacion.getTitulo()+"\n"+publicacion.getProducto().getPrecio()+"$",publicacion.getProducto().getUrlImagen(),publicacion.getProducto().getId()));
            System.out.println(publicacion.getProducto().getId());
        }
    }


    //sirve para agregar dinamicamente paneles en el panePublicaciones
    private void agregarPane(Pane pane) {
        panePublicaciones.getChildren().add(pane);
        pane.setLayoutX(layaoutXPanePublicaciones);
        pane.setLayoutY(layaoutYPanePublicaciones);
        if (layaoutXPanePublicaciones % 100 == 0) {
            layaoutXPanePublicaciones -= 274;
            layaoutYPanePublicaciones += 255;
            panePublicaciones.setPrefHeight(panePublicaciones.getPrefHeight() + 500);
        } else {
            layaoutXPanePublicaciones += 274;
        }
    }
    private void restablcerAjutesPanePublicaiones(){
        panePublicaciones.getChildren().clear();
        layaoutXPanePublicaciones = 26;
        layaoutYPanePublicaciones = 83;
    }


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarMensaje.setVisible(false);
        mostrarMensajeMisPublicaciones.setVisible(false);
        mostrarMensajePerfil.setVisible(false);
        mostrarMensajeChat.setVisible(false);
        mostrarMensajeCrearPublicacion.setVisible(false);
        mostrarMensajeNotificaciones.setVisible(false);
        // inicializa valores de componentes de la interfaz
        paneDinamico = new PaneDinamico();
        layaoutXPanePublicaciones = 26;
        layaoutYPanePublicaciones = 0;
        boxCategoria.getItems().addAll(TipoCategoria.values());
        boxEstado.getItems().addAll(TipoEstado.values());
        principalController= new PrincipalController();// controller de la clase

        // obtiene el vendedor o administrado que haya iniciado sesion
        vendedorGlobal=principalController.obtenerVendedorGlobal();
        administradorGlobal=principalController.obtenerAdministradorGlobal();
        cambiarNombreLabel();


        //cargar publicaiones
       crearPanePublicaion();


    }
    private void  cambiarNombreLabel(){
        if(vendedorGlobal!=null){
            lblNombreUsuario.setText(vendedorGlobal.getNombreUsuario());
        }else {
            lblNombreUsuario.setText(administradorGlobal.getNombreUsuario());
        }
    }




    //cierra la ventana  y abre la de login
    @FXML
    void cerrarSesion(ActionEvent event) {
        Stage stage = (Stage) PanePrincipal.getScene().getWindow();
        stage.close();
        if (ventanaBuscarAmigos != null){ventanaBuscarAmigos.close();}
        if(ventanaCrearPublicacion != null) {ventanaCrearPublicacion.close();}
        if(ventanaNotificaciones != null){ventanaNotificaciones.close();}


        abrirLogin();// abre la ventana de login
    }

    Stage ventanaLoginview;
    private  void abrirLogin() {
        // sirve para abrir ventanas
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginView.fxml"));
            Parent root = loader.load();
            ventanaLoginview = new Stage();
            ventanaLoginview.setScene(new Scene(root));
            ventanaLoginview.show();
            LoginControllerView loginControllerView = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    Stage ventanaBuscarAmigos;
    @FXML
    void abrirBuscarAmigos(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("buscarAmigos.fxml"));
            Parent root = loader.load();
            ventanaBuscarAmigos = new Stage();
            ventanaBuscarAmigos.setScene(new Scene(root));
            ventanaBuscarAmigos.show();
            buscarAmigosControllerView controller2 = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //copiar y pegar
    @FXML
    void publicacionesPersonalizada(MouseEvent event) throws IOException {
       TreeMap<String,Publicacion> publiacionesUsuario=principalController.obtenerPublicacionesUsuario();
       restablcerAjutesPanePublicaiones();
        for (Map.Entry<String, Publicacion> entry : publiacionesUsuario.entrySet()) {
            String clave = entry.getKey();
            Publicacion publicacion = entry.getValue();
            agregarPane(paneDinamico.buildPane(publicacion.getTitulo()+"\n"+publicacion.getProducto().getPrecio()+"$",publicacion.getProducto().getUrlImagen(),publicacion.getProducto().getId()));
            System.out.println(publicacion.getProducto().getId());
        }
    }

    @FXML
    void buscar(MouseEvent event) throws IOException {
        panePublicaciones.getChildren().clear();
        layaoutXPanePublicaciones = 26;
        layaoutYPanePublicaciones = 0;
        if (!(txtBuscador.getText().isEmpty())) {
            for (Map.Entry<String, Publicacion> entry : mapaPublicaciones.entrySet()) {
                String clave = entry.getKey();
                Publicacion publicacion = entry.getValue();
                if (publicacion.getTitulo().equals(txtBuscador.getText())) {// titulo
                    automatizar(publicacion);
                } else if (publicacion.getVendedor().getNombreUsuario().equals(txtBuscador.getText())) { //por nombre dle vendedor
                    automatizar(publicacion);
                } else if (publicacion.getProducto().getPrecio().equals(txtBuscador.getText())) {// producto
                    automatizar(publicacion);
                }
            }
        } else if (!(txtMinFiltro.getText().isEmpty()) || !(txtMaxFiltro.getText().isEmpty())) {
            for (Map.Entry<String, Publicacion> entry : mapaPublicaciones.entrySet()) {
                String clave = entry.getKey();
                Publicacion publicacion = entry.getValue();
                if (Float.parseFloat(txtMinFiltro.getText()) <= Float.parseFloat(publicacion.getProducto().getPrecio())) {
                    automatizar(publicacion);
                } else if (Float.parseFloat(txtMaxFiltro.getText()) >= Float.parseFloat(publicacion.getProducto().getPrecio())) {
                    automatizar(publicacion);
                }
            }
        } else if (boxCategoria.getValue() != null || boxEstado.getValue() != null) {
            for (Map.Entry<String, Publicacion> entry : mapaPublicaciones.entrySet()) {
                String clave = entry.getKey();
                Publicacion publicacion = entry.getValue();
                Producto producto=principalController.obtenerProductos(publicacion.getProducto().getId());
                publicacion.setProducto(producto);
                System.out.println(boxCategoria.getValue());
                System.out.println(publicacion.getProducto().getTipoCategoria());
                if (boxCategoria.getValue() != null) {
                    if (boxCategoria.getValue().equals(publicacion.getProducto().getTipoCategoria())) {
                        automatizar(publicacion);
                    }
                } else if (boxEstado.getValue() != null) {

                    if (boxEstado.getValue().equals(publicacion.getProducto().getTipoEstado())) {
                        automatizar(publicacion);
                    }
                }
            }
        }

    }

    @FXML
    private Label mostrarMensaje;

    public void automatizar(Publicacion publicacion) {
        agregarPane(paneDinamico.buildPane(publicacion.getTitulo() + "\n" + publicacion.getProducto().getPrecio() + "$", publicacion.getProducto().getUrlImagen(), publicacion.getProducto().getId()));
    }

    @FXML
    void mostrarMensajeBuscarAmigos(MouseEvent event) {
        try {

            mostrarMensaje.setText("Buscar amigos");// Establece el texto del mensaje
            mostrarMensaje.setFont(Font.font("Arial", 14));
            mostrarMensaje.setStyle("-fx-font-weight: bold;");

            mostrarMensaje.setVisible(true);  // Mostrar el mensaje
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }


    }
    @FXML
    void noMostrarMensajeBuscarAmigos(MouseEvent event) {
        mostrarMensaje.setVisible(false);

    }


    @FXML
    private MFXButton btnRefrescar;



    @FXML
    private ImageView imgAmigos1;
    @FXML
    private ImageView imgMiPerfil;


    @FXML
    private Label mostrarMensajeChat;

    @FXML
    private Label mostrarMensajeCrearPublicacion;

    @FXML
    private Label mostrarMensajeMisPublicaciones;

    @FXML
    private Label mostrarMensajePerfil;






    @FXML
    void mostrarMensajeChatear(MouseEvent event) {
        try {

            mostrarMensajeChat.setText("Chatear");// Establece el texto del mensaje
            mostrarMensajeChat.setFont(Font.font("Arial", 14));
            mostrarMensajeChat.setStyle("-fx-font-weight: bold;");

            mostrarMensajeChat.setVisible(true);  // Mostrar el mensaje
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void mostrarMensajeCrearPublicacion(MouseEvent event) {
        try {

        mostrarMensajeCrearPublicacion.setText("Crear Publicacion");// Establece el texto del mensaje
        mostrarMensajeCrearPublicacion.setFont(Font.font("Arial", 14));
        mostrarMensajeCrearPublicacion.setStyle("-fx-font-weight: bold;");

        mostrarMensajeCrearPublicacion.setVisible(true);  // Mostrar el mensaje
    }catch (NullPointerException e){
        System.out.println(e.getMessage());
    }


    }

    @FXML
    void mostrarMensajeMiPerfil(MouseEvent event) {
        try {

            mostrarMensajePerfil.setText("Mi perfil");// Establece el texto del mensaje
            mostrarMensajePerfil.setFont(Font.font("Arial", 14));
            mostrarMensajePerfil.setStyle("-fx-background-color: white;-fx-font-weight: bold;");

            mostrarMensajePerfil.setVisible(true);  // Mostrar el mensaje
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }


    }

    @FXML
    void mostrarMensajeMisPublicaciones(MouseEvent event) {
        try {

            mostrarMensajeMisPublicaciones.setText("Mis publicaciones");// Establece el texto del mensaje
            mostrarMensajeMisPublicaciones.setFont(Font.font("Arial", 14));
            mostrarMensajeMisPublicaciones.setStyle("-fx-font-weight: bold;");

            mostrarMensajeMisPublicaciones.setVisible(true);  // Mostrar el mensaje
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }



    }


    @FXML
    void noMostrarMensajeChatear(MouseEvent event) {
        mostrarMensajeChat.setVisible(false);

    }

    @FXML
    void noMostrarMensajeCrearPublicacion(MouseEvent event) {
        mostrarMensajeCrearPublicacion.setVisible(false);

    }

    @FXML
    void noMostrarMensajeMiPerfil(MouseEvent event) {
        mostrarMensajePerfil.setVisible(false);

    }

    @FXML
    void noMostrarMensajeMisPublicaciones(MouseEvent event) {
        mostrarMensajeMisPublicaciones.setVisible(false);

    }
    @FXML
    void abrirMiPerfil(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("miPerfil.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            MiPerfilControllerView controller =loader.getController();
//            buscarAmigosControllerView controller2 = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    @FXML
    private ImageView Notificaciones;
    @FXML
    private Label mostrarMensajeNotificaciones;

    Stage ventanaNotificaciones;
    @FXML
    void abrirNotificaciones(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Notificaciones.fxml"));
            Parent root = loader.load();
            ventanaNotificaciones = new Stage();
            ventanaNotificaciones.setScene(new Scene(root));
            ventanaNotificaciones.show();
            NotificacionesControllerView notificacionesControllerView =loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void mostrarMensajeNotificaciones(MouseEvent event) {
        try {

            mostrarMensajeNotificaciones.setText("Notificaciones");// Establece el texto del mensaje
            mostrarMensajeNotificaciones.setFont(Font.font("Arial", 14));
            mostrarMensajeNotificaciones.setStyle("-fx-font-weight: bold;");

            mostrarMensajeNotificaciones.setVisible(true);  // Mostrar el mensaje
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }


    }
    @FXML
    void noMostrarMensajeNotificaciones(MouseEvent event) {
        mostrarMensajeNotificaciones.setVisible(false);

    }





}