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
                case "FCFS"-> menuParent.setCenter(Model.getInstance().getViewFactory().getFCFSView());
                case "SJF" -> menuParent.setCenter(Model.getInstance().getViewFactory().getSJFView());
                case "NonSJF" -> menuParent.setCenter(Model.getInstance().getViewFactory().getNonSJFView());
                case "Priority" -> menuParent.setCenter(Model.getInstance().getViewFactory().getPriorityView());
                case "NonPriority" -> menuParent.setCenter(Model.getInstance().getViewFactory().getNonPriorityView());
                case "RR" -> menuParent.setCenter(Model.getInstance().getViewFactory().getRoundRobinView());
                case "Menu" -> menuParent.setCenter(Model.getInstance().getViewFactory().getMenuView());
            }
        });
    }
}
