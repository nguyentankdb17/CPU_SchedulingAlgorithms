package com.example.schedulingalgorithm.Process;

import com.example.schedulingalgorithm.Model.Model;
import javafx.scene.control.Label;

import java.util.List;

public class GeneralMethods {
    public static void onMenu() {
        Model.getInstance().getViewFactory().getCurrentViewString().set("Menu");
    }

    public static void onFCFS() {
        Model.getInstance().getViewFactory().getCurrentViewString().set("FCFS");
    }

    public static void onPreSJF() {
        Model.getInstance().getViewFactory().getCurrentViewString().set("SJF");
    }

    public static void onNonPreSJF() {
        Model.getInstance().getViewFactory().getCurrentViewString().set("NonSJF");
    }

    public static void onPrePriority() {
        Model.getInstance().getViewFactory().getCurrentViewString().set("Priority");
    }

    public static void onNonPrePriority() {
        Model.getInstance().getViewFactory().getCurrentViewString().set("NonPriority");
    }

    public static void onRoundRobin() {
        Model.getInstance().getViewFactory().getCurrentViewString().set("RR");
    }

    public static double amountExecuteTime(List<ProcessObject> processList) {
        double amount = 0.d;
        for (int i = 0; i < processList.size(); ++i) {
            amount += processList.get(i).getBurstTime();
        }
        return amount;
    }

    public static void setColor(Label label, int i) {
        switch (i) {
            case 1 -> label.setStyle("-fx-background-color: #ff0000");
            case 2 -> label.setStyle("-fx-background-color: #ffa500");
            case 3 -> label.setStyle("-fx-background-color: #ffff00");
            case 4 -> label.setStyle("-fx-background-color: #00ffff");
            case 5 -> label.setStyle("-fx-background-color: #800080");
            case 6 -> label.setStyle("-fx-background-color: #ffc0cb");
            case 7 -> label.setStyle("-fx-background-color: #c0c0c0");
        }
    }
}
