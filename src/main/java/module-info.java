module com.registry.partyregistry {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.registry.partyregistry to javafx.fxml;
    exports com.registry.partyregistry;
}