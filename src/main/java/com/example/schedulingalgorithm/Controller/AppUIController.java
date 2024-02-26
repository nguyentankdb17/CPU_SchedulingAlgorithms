package com.example.schedulingalgorithm.Controller;

import com.example.schedulingalgorithm.Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AppUIController implements Initializable {

    public BorderPane menuParent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getCurrentViewString().addListener((observableValue, oldVal, newVal) -> {
        switch (newVal) {
            case "Menu":
            default:
                menuParent.setCenter(Model.getInstance().getViewFactory().getMenuView());
        }
        });
    }
}
