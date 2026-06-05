module grupo.uno.proyectoposfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens grupo.uno.proyectoposfx to javafx.fxml;
    exports grupo.uno.proyectoposfx;
}
