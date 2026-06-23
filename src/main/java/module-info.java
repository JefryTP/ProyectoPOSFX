module proyectoposfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    opens proyectoposfx to javafx.fxml;
    opens proyectoposfx.controladores to javafx.fxml;
    opens proyectoposfx.modelos to javafx.base;
    exports proyectoposfx;
    exports proyectoposfx.controladores;
}
