module co.edu.uniquindio.mercado {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;


    opens co.edu.uniquindio.mercado to javafx.fxml;
    exports co.edu.uniquindio.mercado;
}