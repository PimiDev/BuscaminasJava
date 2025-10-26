module com.example.buscaminas {
    requires javafx.controls;
    requires javafx.fxml;

    // Exportamos los paquetes que queremos usar desde fuera
    exports com.example.buscaminas;
    exports Logica;
    exports GUI;  // <--- agregar esto

    // Abrimos paquetes para reflexión (FXML)
    opens com.example.buscaminas to javafx.fxml;
    opens Logica to javafx.fxml;
    opens GUI to javafx.fxml;  // <--- opcional si usarás FXML en GUI
}
