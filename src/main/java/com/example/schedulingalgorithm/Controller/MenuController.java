package com.example.schedulingalgorithm.Controller;

import com.example.schedulingalgorithm.Process.GeneralMethods;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public Button NonPrePriorityAlgorithm;
    public Button RoundRobinAlgorithm;
    public Button PreSJFAlgorithm;
    public Button NonPreSJFAlgorithm;
    public Button PrePriorityAlgorithm;
    public Button FCFSAlgorithm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FCFSAlgorithm.setOnAction(event -> GeneralMethods.onFCFS());
        PreSJFAlgorithm.setOnAction(event -> GeneralMethods.onPreSJF());
        NonPreSJFAlgorithm.setOnAction(event -> GeneralMethods.onNonPreSJF());
        PrePriorityAlgorithm.setOnAction(event -> GeneralMethods.onPrePriority());
        NonPrePriorityAlgorithm.setOnAction(event -> GeneralMethods.onNonPrePriority());
        RoundRobinAlgorithm.setOnAction(event -> GeneralMethods.onRoundRobin());
    }
}
