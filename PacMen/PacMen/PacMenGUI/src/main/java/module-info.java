module org.fhict {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.fhict to javafx.fxml;
    exports org.fhict;
}