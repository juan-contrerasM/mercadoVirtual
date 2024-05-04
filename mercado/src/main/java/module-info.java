module co.edu.uniquindio.mercado {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires lombok;


    opens co.edu.uniquindio.mercado.model;
    exports  co.edu.uniquindio.mercado.model;
    exports co.edu.uniquindio.mercado.controllerView;
    opens co.edu.uniquindio.mercado.controllerView to javafx.fxml;
    exports co.edu.uniquindio.mercado;
    opens co.edu.uniquindio.mercado;
}