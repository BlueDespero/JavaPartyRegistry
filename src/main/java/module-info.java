module com.registry.partyregistry {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.joda.money;
    requires org.joda.time;


    opens com.registry.partyregistry to javafx.fxml;
    exports com.registry.partyregistry;
    exports com.registry.partyregistry.controllers;
    opens com.registry.partyregistry.controllers to javafx.fxml;
    exports com.registry.partyregistry.event;
    opens com.registry.partyregistry.event to javafx.fxml;
}