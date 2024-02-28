package com.example.schedulingalgorithm.View;

import com.example.schedulingalgorithm.Controller.AppUIController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewFactory {

    private StringProperty currentViewString = new SimpleStringProperty();

    private AnchorPane menuView;
    private AnchorPane FCFSView;
    private AnchorPane SJFView;
    private AnchorPane NonSJFView;
    private AnchorPane PriorityView;
    private AnchorPane NonPriorityView;
    private AnchorPane RoundRobinView;

    public StringProperty getCurrentViewString() {
        return currentViewString;
    }

    private void createStage(FXMLLoader fxmlLoader, Stage stage) {
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.setScene(scene);
        stage.setTitle("Scheduling Calculate Tool");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public void showAppWindow(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/AppUI.fxml"));
        AppUIController appUIController = new AppUIController();
        fxmlLoader.setController(appUIController);
        createStage(fxmlLoader, stage);
    }

    public AnchorPane getMenuView() {
        try {
            menuView = new FXMLLoader(getClass().getResource("/FXML/Menu.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuView;
    }

    public AnchorPane getFCFSView() {
        try {
            FCFSView = new FXMLLoader(getClass().getResource("/FXML/FCFS.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FCFSView;
    }

    public AnchorPane getSJFView() {
        try {
            SJFView = new FXMLLoader(getClass().getResource("/FXML/SJF.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SJFView;
    }

    public AnchorPane getNonSJFView() {
        try {
            NonSJFView = new FXMLLoader(getClass().getResource("/FXML/NonSJF.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NonSJFView;
    }

    public AnchorPane getPriorityView() {
        try {
            PriorityView = new FXMLLoader(getClass().getResource("/FXML/Priority.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PriorityView;
    }

    public AnchorPane getNonPriorityView() {
        try {
            NonPriorityView = new FXMLLoader(getClass().getResource("/FXML/NonPriority.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NonPriorityView;
    }

    public AnchorPane getRoundRobinView() {
        try {
            RoundRobinView = new FXMLLoader(getClass().getResource("/FXML/RoundRobin.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RoundRobinView;
    }
}
