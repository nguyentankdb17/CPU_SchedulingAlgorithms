package com.example.schedulingalgorithm.Algorithm;

import com.example.schedulingalgorithm.Process.ProcessObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NonSJFAlgorithm {
    public static double[] waitingTime(List<ProcessObject> processList) {
        double[] wt = new double[processList.size()];
        int n = processList.size();
        int time = 0, numProcess = 0, indexMin = 0;
        double timeMin = Double.MAX_VALUE;
        boolean[] marked = new boolean[n];
        for (int i = 0; i < n; ++i) {
            marked[i] = false;
        }
        while (numProcess < n) {
            timeMin = Double.MAX_VALUE;
            for (int i = 0; i < n; ++i) {
                if (processList.get(i).getArrivalTime() <= time && processList.get(i).getBurstTime() < timeMin && !marked[i]) {
                    timeMin = processList.get(i).getBurstTime();
                    indexMin = processList.get(i).getId() - 1;
                }
            }
            marked[indexMin] = true;
            wt[indexMin] = time - processList.get(indexMin).getArrivalTime();
            time += processList.get(indexMin).getBurstTime();
            numProcess++;
        }
        return wt;
    }

    public static double[] turnAroundTime(List<ProcessObject> processList) {
        double[] wt = waitingTime(processList);
        double[] ta = new double[processList.size()];
        for (int i = 0; i < processList.size(); ++i) {
            ta[i] = wt[i] + processList.get(i).getBurstTime();
        }
        return ta;
    }
}
