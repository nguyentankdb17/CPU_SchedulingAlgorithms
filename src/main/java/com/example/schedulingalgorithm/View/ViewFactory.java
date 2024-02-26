package com.example.schedulingalgorithm.View;

import com.example.schedulingalgorithm.Controller.AppUIController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewFactory {

    private StringProperty currentViewString = new SimpleStringProperty();

    private AnchorPane menuView;

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
        stage.setResizable(false);
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
}
