package com.example.schedulingalgorithm.Controller;

import com.example.schedulingalgorithm.Algorithm.NonPriorityAlgorithm;
import com.example.schedulingalgorithm.Process.GeneralMethods;
import com.example.schedulingalgorithm.Process.Pair;
import com.example.schedulingalgorithm.Process.ProcessObject;
import com.example.schedulingalgorithm.Process.ProcessOutput;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class NonPriorityController implements Initializable {
    public TableColumn<ProcessObject, Integer> processColumn;
    public TableColumn<ProcessObject, Double> burstTimeColumn;
    public TableColumn<ProcessObject, Double> arrivalTimeColumn;
    public TableColumn<ProcessObject, Integer> priorityColumn;
    public TextField burstTimeField;
    public TextField arrivalTimeField;
    public TextField priorityField;
    public Button removeProcessButton;
    public Button addProcessButton;
    public Button removeAllButton;
    public Button executeButton;
    public Label errorLabel;
    public TableView<ProcessObject> table;
    public Label errorFullLabel;
    public Label errorEmptyLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        burstTimeField.setOnKeyTyped(keyEvent -> {
            errorFullLabel.setVisible(false);
            errorEmptyLabel.setVisible(false);
            errorLabel.setVisible(false);
        });
        arrivalTimeField.setOnKeyTyped(keyEvent -> {
            errorFullLabel.setVisible(false);
            errorEmptyLabel.setVisible(false);
            errorLabel.setVisible(false);
        });
        priorityField.setOnKeyTyped(keyEvent -> {
            errorFullLabel.setVisible(false);
            errorEmptyLabel.setVisible(false);
            errorLabel.setVisible(false);
        });
        addProcessButton.setOnAction(event -> {
            if (burstTimeField.getText().isEmpty() || arrivalTimeField.getText().isEmpty() ||
                    priorityField.getText().isEmpty() || numProcesses == 6) {
                errorLabel.setVisible(true);
            } else {
                boolean isAdd = true;
                String burstTime = burstTimeField.getText().trim();
                for (int i = 0; i < burstTime.length(); ++i) {
                    if (burstTime.charAt(i) == 47 || burstTime.charAt(i) < 46 || burstTime.charAt(i) > 57) {
                        errorLabel.setVisible(true);
                        isAdd = false;
                    }
                }
                String arrivalTime = arrivalTimeField.getText().trim();
                for (int i = 0; i < arrivalTime.length(); ++i) {
                    if (arrivalTime.charAt(i) == 47 || arrivalTime.charAt(i) < 46 || arrivalTime.charAt(i) > 57) {
                        errorLabel.setVisible(true);
                        isAdd = false;
                    }
                }
                String priority = priorityField.getText().trim();
                for (int i = 0; i < priority.length(); ++i) {
                    if (priority.charAt(i) == 47 || priority.charAt(i) < 46 || priority.charAt(i) > 57) {
                        errorLabel.setVisible(true);
                        isAdd = false;
                    }
                }
                if (isAdd) {
                    if (numProcesses < 7) {
                        ProcessObject processObject = new ProcessObject(numProcesses + 1, Double.parseDouble(burstTimeField.getText()),
                                Double.parseDouble(arrivalTimeField.getText()), Integer.parseInt(priorityField.getText()));
                        processList.add(processObject);
                        table.getItems().add(processObject);
                        numProcesses = table.getItems().size();
                    } else {
                        errorFullLabel.setVisible(true);
                    }
                }
            }
            burstTimeField.clear();
            arrivalTimeField.clear();
            priorityField.clear();
        });

        removeProcessButton.setOnAction(event -> {
            int removeIndex = table.getSelectionModel().getSelectedIndex();
            ObservableList<ProcessObject> processObjectObservableList = table.getItems();
            for (int i = removeIndex + 1; i < processObjectObservableList.size(); ++i) {
                processObjectObservableList.get(i).setId(processObjectObservableList.get(i).getId() - 1);
            }
            for (int i = removeIndex + 1; i < processList.size(); ++i) {
                processList.get(i).setId(processList.get(i).getId() - 1);
            }
            table.getItems().remove(removeIndex);
            numProcesses--;
        });

        removeAllButton.setOnAction(event -> {
            table.getItems().clear();
            processList.clear();
            numProcesses = 0;
        });

        executeButton.setOnAction(event -> {
            if (numProcesses > 0) {
                if (stage != null) {
                    stage.close();
                }
                stage = new Stage();
                AnchorPane anchorPane = new AnchorPane();
                Scene scene = new Scene(anchorPane, 600, 400);

                double amountTime = GeneralMethods.amountExecuteTime(processList);
                double[] wt = NonPriorityAlgorithm.waitingTime(processList);
                double[] ta = NonPriorityAlgorithm.turnAroundTime(processList);

                List<Pair> pairList = new ArrayList<>();
                for (int i = 0; i < numProcesses; ++i) {
                    pairList.add(new Pair(i + 1, wt[i] + processList.get(i).getArrivalTime()));
                }
                Collections.sort(pairList);
                for (int i = 0; i < numProcesses; ++i) {
                    int index = pairList.get(i).getId();
                    Label label = new Label("P" + Integer.toString(index));
                    label.setStyle("-fx-border-color: black;"
                            + "-fx-border-width: 2px;"
                            + "-fx-font-weight: bold;");
                    GeneralMethods.setColor(label, index);
                    label.setPrefHeight(30);
                    label.setPrefWidth(500*processList.get(index - 1).getBurstTime()/amountTime);
                    label.setLayoutY(30);
                    if (i == 0) {
                        currentPosition = 50;
                    }
                    else {
                        currentPosition += 500*processList.get(pairList.get(i - 1).getId() - 1).getBurstTime()/amountTime;
                    }
                    label.setLayoutX(currentPosition);
                    Label label2 = new Label(Double.toString(pairList.get(i).getStartTime()));
                    label2.setLayoutX(label.getLayoutX() - 5);
                    if (i % 2 == 0)
                        label2.setLayoutY(70);
                    else
                        label2.setLayoutY(0);
                    label2.setStyle("-fx-background-color: transparent");
                    anchorPane.getChildren().addAll(label, label2);
                }
                Label label3 = new Label(Double.toString(amountTime));
                label3.setStyle("-fx-background-color: transparent");
                label3.setLayoutY(numProcesses % 2 == 0 ? 0 : 70);
                label3.setLayoutX(495);
                anchorPane.getChildren().add(label3);

                Button closeButton = new Button("Close");
                closeButton.setPrefWidth(60);
                closeButton.setPrefHeight(26);
                closeButton.setLayoutX(270);
                closeButton.setLayoutY(350);

                closeButton.setOnAction(event2 -> {
                    table.getItems().clear();
                    processList.clear();
                    numProcesses = 0;
                    stage.close();
                });

                TableView tableIn = new TableView<ProcessOutput>();
                TableColumn idColumn = new TableColumn<ProcessOutput, Integer>("Process");
                idColumn.setCellValueFactory(new PropertyValueFactory<ProcessOutput, Integer>("id"));
                TableColumn waitingTimeColumn = new TableColumn<ProcessOutput, Double>("Waiting Time");
                waitingTimeColumn.setCellValueFactory(new PropertyValueFactory<ProcessOutput, Double>("waitingTime"));
                TableColumn turnAroundTimeColumn = new TableColumn<ProcessOutput, Double>("Turn Around Time");
                turnAroundTimeColumn.setCellValueFactory(new PropertyValueFactory<ProcessOutput, Double>("turnAroundTime"));
                TableColumn respondTimeColumn = new TableColumn<ProcessOutput, Double>("Respond Time");
                respondTimeColumn.setCellValueFactory(new PropertyValueFactory<ProcessOutput, Double>("respondTime"));
                tableIn.getColumns().addAll(idColumn, waitingTimeColumn, turnAroundTimeColumn, respondTimeColumn);

                for (int i = 0; i < numProcesses; ++i) {
                    tableIn.getItems().add(new ProcessOutput(Integer.toString(i + 1), wt[i], ta[i], wt[i]));
                }
                double avgWaitingTime = 0.d;
                double avgTurnAroundTime = 0.d;
                for (int i = 0; i < numProcesses; ++i) {
                    avgWaitingTime += wt[i];
                    avgTurnAroundTime += ta[i];
                }
                avgWaitingTime /= numProcesses;
                avgTurnAroundTime /= numProcesses;
                tableIn.getItems().add(new ProcessOutput("Average",
                        Double.parseDouble(String.format("%.2f",avgWaitingTime)),
                        Double.parseDouble(String.format("%.2f", avgTurnAroundTime)),
                        Double.parseDouble(String.format("%.2f", avgWaitingTime))));

                tableIn.setLayoutX(100);
                tableIn.setLayoutY(120);
                tableIn.setPrefWidth(400);
                tableIn.setPrefHeight(200);
                tableIn.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

                anchorPane.getChildren().addAll(closeButton, tableIn);

                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setY(260);
                stage.setX(470);
                stage.show();
            } else {
                errorEmptyLabel.setVisible(true);
            }
        });
    }

    private void init() {
        numProcesses = 0;
        processList = new ArrayList<>();
        errorLabel.setVisible(false);
        errorFullLabel.setVisible(false);
        errorEmptyLabel.setVisible(false);
        processColumn.setCellValueFactory(new PropertyValueFactory<ProcessObject, Integer>("id"));
        burstTimeColumn.setCellValueFactory(new PropertyValueFactory<ProcessObject, Double>("burstTime"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<ProcessObject, Double>("arrivalTime"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<ProcessObject, Integer>("priority"));
    }

    private int numProcesses;
    private List<ProcessObject> processList;
    private Stage stage;
    private double currentPosition = 50;
}
