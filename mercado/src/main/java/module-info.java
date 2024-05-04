module co.edu.uniquindio.mercado {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires lombok;


    opens co.edu.uniquindio.mercado to javafx.fxml;
    exports co.edu.uniquindio.mercado;
    exports co.edu.uniquindio.mercado.controllerView;
    opens co.edu.uniquindio.mercado.controllerView to javafx.fxml;
}