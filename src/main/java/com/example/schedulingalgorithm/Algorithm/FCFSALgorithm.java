package com.example.schedulingalgorithm.Algorithm;

import com.example.schedulingalgorithm.Process.ProcessObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FCFSALgorithm {

    public double[] waitingTime(List<ProcessObject> processList) {
        double[] waiting = new double[processList.size()];
        double[] wt = new double[processList.size()];
        waiting[0] = 0.d;
        for (int i = 1; i < processList.size(); ++i) {
            waiting[i] = processList.get(i - 1).getBurstTime() + processList.get(i - 1).getArrivalTime() + waiting[i - 1] - processList.get(i).getArrivalTime();
            wt[processList.get(i).getId() - 1] = waiting[i];
        }
        return wt;
    }

    public double[] turnAroundTime(List<ProcessObject> processList) {
        double[] wt = waitingTime(processList);
        double[] ta = new double[processList.size()];
        for (int i = 0; i < processList.size(); ++i) {
            ta[processList.get(i).getId() - 1] = wt[processList.get(i).getId() - 1] + processList.get(i).getBurstTime();
        }
        return ta;
    }
}
