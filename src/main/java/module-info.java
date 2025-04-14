module org.example.miniproyecto2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens org.example.miniproyecto2 to javafx.fxml;
    exports org.example.miniproyecto2;
    exports org.example.miniproyecto2.Controller;
    opens org.example.miniproyecto2.Controller to javafx.fxml;
}