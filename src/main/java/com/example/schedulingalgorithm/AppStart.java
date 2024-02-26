package com.example.schedulingalgorithm;

import com.example.schedulingalgorithm.Model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppStart extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Model.getInstance().getViewFactory().showAppWindow(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
