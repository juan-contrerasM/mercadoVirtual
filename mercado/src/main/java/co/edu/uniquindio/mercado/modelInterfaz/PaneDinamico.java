package co.edu.uniquindio.mercado.modelInterfaz;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class PaneDinamico {
    public static Pane buildPane(String mensajeLabel, String url) {
        Pane pane = new AnchorPane();
        pane.setPrefHeight(200);
        pane.setPrefWidth(200);

        Label label = new Label(mensajeLabel);
        label.setPrefHeight(72);
        label.setPrefWidth(171);
        label.setLayoutX(14);
        label.setLayoutY(119);

        ImageView imageView = new ImageView(new Image(url));
        imageView.setFitHeight(93);
        imageView.setFitWidth(115);
        imageView.setLayoutX(19);
        imageView.setLayoutY(26);


        pane.getChildren().addAll(label);
        pane.getChildren().addAll(imageView);

        return pane;
    }

}
