package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.controller.ControllerPaneDinamico;
import co.edu.uniquindio.mercado.controllerView.LoginControllerView;
import co.edu.uniquindio.mercado.controllerView.MostrarPublicaionController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PaneDinamico  {
    private ControllerPaneDinamico controllerPaneDinamico= new ControllerPaneDinamico();
    public Pane buildPane(String mensajeLabel, String url, int id) {
        Pane pane = new AnchorPane();
        pane.setPrefHeight(200);
        pane.setPrefWidth(200);

        Label label = new Label(mensajeLabel);
        label.setPrefHeight(72);
        label.setPrefWidth(171);
        label.setLayoutX(14);
        label.setLayoutY(140);
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 17));

        ImageView imageView = new ImageView(new Image(url));
        imageView.setFitHeight(120);
        imageView.setFitWidth(140);
        imageView.setLayoutX(30);
        imageView.setLayoutY(26);


        // Creamos el efecto de sombra
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.GRAY);
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);

        // Manejamos el evento de entrada del mouse
        pane.setOnMouseEntered(event -> {
            // Cambiamos el tamaño y el estilo al pasar el cursor sobre el Pane
            pane.setStyle("-fx-background-color: lightgray; -fx-border-color: black;-fx-border-width: 2px; -fx-background-radius: 10px;");
            pane.setScaleX(1.1);
            pane.setScaleY(1.1);
        });

        // Manejamos el evento de salida del mouse
        pane.setOnMouseExited(event -> {
            // Restauramos el tamaño y el estilo al quitar el cursor del Pane
            pane.setStyle("-fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 2px;");
            pane.setScaleX(1.0);
            pane.setScaleY(1.0);
        });
        // Aplicamos el efecto de sombra al Pane
        pane.setEffect(shadow);
        pane.setStyle("-fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 2px;");
        pane.getChildren().addAll(label);
        pane.getChildren().addAll(imageView);

        // Manejamos el evento de clic del botón
        pane.setOnMouseClicked(event -> {
            try {
                controllerPaneDinamico.guardarPublicacionGlobal(id);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("mostrarPublicacion.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                MostrarPublicaionController mostrarPublicaionController = loader.getController();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return pane;
    }

    public Pane buildPane(String mensajeLabel) {
        Pane pane = new AnchorPane();
        pane.setPrefHeight(100);
        pane.setPrefWidth(120);

        Label label = new Label(mensajeLabel);
        label.setPrefHeight(50);
        label.setPrefWidth(100);
        label.setLayoutX(14);
        label.setLayoutY(90);
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 17));

//        Button botonAgregar = new Button("agregar");
//        botonAgregar.setVisible(true);
//        //botonAgregar.getScaleX()




        // Creamos el efecto de sombra
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.GRAY);
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);

        // Manejamos el evento de entrada del mouse
        pane.setOnMouseEntered(event -> {
            // Cambiamos el tamaño y el estilo al pasar el cursor sobre el Pane
            pane.setStyle("-fx-background-color: lightgray; -fx-border-color: black;-fx-border-width: 2px; -fx-background-radius: 10px;");
            pane.setScaleX(1.1);
            pane.setScaleY(1.1);
        });

        // Manejamos el evento de salida del mouse
        pane.setOnMouseExited(event -> {
            // Restauramos el tamaño y el estilo al quitar el cursor del Pane
            pane.setStyle("-fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 2px;");
            pane.setScaleX(1.0);
            pane.setScaleY(1.0);
        });
        // Aplicamos el efecto de sombra al Pane
        pane.setEffect(shadow);
        pane.setStyle("-fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 2px;");
        pane.getChildren().addAll(label);


        return pane;
    }



}
