package com.example.schedulingalgorithm.Process;

import com.example.schedulingalgorithm.Model.Model;

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
}
