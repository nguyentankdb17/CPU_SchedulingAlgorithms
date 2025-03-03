package com.example.schedulingalgorithm.Controller;

import com.example.schedulingalgorithm.Algorithm.RoundRobinAlgorithm;
import com.example.schedulingalgorithm.Process.GeneralMethods;
import com.example.schedulingalgorithm.Process.PairOther;
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

public class RoundRobinController implements Initializable {

    public TableColumn processColumn;
    public TableColumn burstTimeColumn;
    public TableColumn arrivalTimeColumn;
    public TextField roundQuantumField;
    public TextField burstTimeField;
    public TextField arrivalTimeField;
    public Button addProcessButton;
    public Label errorLabel;
    public Label errorFullLabel;
    public Label errorEmptyLabel;
    public Button removeProcessButton;
    public Button removeAllButton;
    public Button executeButton;
    public TableView table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        roundQuantumField.setOnKeyTyped(keyEvent -> {
            String input = roundQuantumField.getText();
            try {
                timeQuantum = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        });

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
        addProcessButton.setOnAction(event -> {
            if (burstTimeField.getText().isEmpty() || arrivalTimeField.getText().isEmpty() || numProcesses == 6) {
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
                if (isAdd) {
                    if (numProcesses < 7) {
                        ProcessObject processObject = new ProcessObject(numProcesses + 1, Double.parseDouble(burstTimeField.getText()),
                                Double.parseDouble(arrivalTimeField.getText()));
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
                double[] wt = RoundRobinAlgorithm.waitingTime(processList, timeQuantum);
                double[] ta = RoundRobinAlgorithm.turnAroundTime(processList, timeQuantum);
                double[] rt = RoundRobinAlgorithm.respondTime(processList, timeQuantum);

                List<PairOther> pairList = RoundRobinAlgorithm.pairList(processList, timeQuantum);
                for (int i = 0; i < pairList.size(); ++i) {
                    int index = pairList.get(i).getId();
                    Label label = new Label("P" + Integer.toString(index));
                    label.setStyle("-fx-border-color: black;"
                            + "-fx-border-width: 2px;"
                            + "-fx-font-weight: bold;");
                    GeneralMethods.setColor(label, index);
                    label.setPrefHeight(30);
                    label.setPrefWidth(500*pairList.get(i).getExecuteTime()/amountTime);
                    label.setLayoutY(30);
                    if (i == 0) {
                        currentPosition = 50;
                    }
                    else {
                        currentPosition += 500*pairList.get(i - 1).getExecuteTime()/amountTime;
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
                label3.setLayoutY(pairList.size() % 2 == 0 ? 0 : 70);
                label3.setLayoutX(545);
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
                    currentPosition = 50;
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
                    tableIn.getItems().add(new ProcessOutput(Integer.toString(i + 1), wt[i], ta[i], rt[i]));
                }
                double avgWaitingTime = 0.d;
                double avgTurnAroundTime = 0.d;
                double avgRespondTime = 0.d;
                for (int i = 0; i < numProcesses; ++i) {
                    avgWaitingTime += wt[i];
                    avgTurnAroundTime += ta[i];
                    avgRespondTime += rt[i];
                }
                avgWaitingTime /= numProcesses;
                avgTurnAroundTime /= numProcesses;
                avgRespondTime /= numProcesses;
                tableIn.getItems().add(new ProcessOutput("Average",
                        Double.parseDouble(String.format("%.2f",avgWaitingTime)),
                        Double.parseDouble(String.format("%.2f", avgTurnAroundTime)),
                        Double.parseDouble(String.format("%.2f", avgRespondTime))));

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
    }

    private int numProcesses;
    private int timeQuantum;
    private List<ProcessObject> processList;
    private Stage stage;
    private double currentPosition = 50;
}
