package co.edu.uniquindio.mercado.modelInterfaz;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class PaneDinamico {
    public static Pane buildPane() {
        Pane pane = new AnchorPane();
        pane.setPrefHeight(200);
        pane.setPrefWidth(200);

        Label label = new Label("Sample Label");
        label.setPrefHeight(72);
        label.setPrefWidth(171);
        label.setLayoutX(14);
        label.setLayoutY(119);
/*
        ImageView imageView = new ImageView(new Image("sample.jpg"));
        imageView.setFitHeight(93);
        imageView.setFitWidth(115);
        imageView.setLayoutX(42);
        imageView.setLayoutY(26);
        */

        pane.getChildren().addAll(label);

        return pane;
    }

}
