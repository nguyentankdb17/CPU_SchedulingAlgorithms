package com.example.schedulingalgorithm.Controller;

import com.example.schedulingalgorithm.Process.GeneralMethods;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarController implements Initializable {
    public Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exitButton.setOnAction(event -> GeneralMethods.onMenu());
    }
}
