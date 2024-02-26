module com.example.schedulingalgorithm {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens com.example.schedulingalgorithm to javafx.fxml;
    exports com.example.schedulingalgorithm;
}