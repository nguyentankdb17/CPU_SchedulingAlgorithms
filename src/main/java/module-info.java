module com.example.schedulingalgorithm {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires de.jensd.fx.glyphs.fontawesome;
    requires javafx.web;
    requires javafx.media;

    opens com.example.schedulingalgorithm to javafx.fxml;
    exports com.example.schedulingalgorithm;
    exports com.example.schedulingalgorithm.Controller;
}