module co.edu.uniquindio.mercado {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires lombok;
    requires java.logging;
    requires java.mail;


    opens co.edu.uniquindio.mercado.model;
    exports  co.edu.uniquindio.mercado.model;
    exports co.edu.uniquindio.mercado.controllerView;
    opens co.edu.uniquindio.mercado.controllerView to javafx.fxml;
    opens  co.edu.uniquindio.mercado.controller;
    exports co.edu.uniquindio.mercado.controller;
}