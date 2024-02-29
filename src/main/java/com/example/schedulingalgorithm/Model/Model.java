package com.example.schedulingalgorithm.Model;

import com.example.schedulingalgorithm.View.ViewFactory;

public class Model {
    public static Model model;
    private final ViewFactory viewFactory;

    private Model() {
        viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
