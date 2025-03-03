package com.example.schedulingalgorithm.Algorithm;

import com.example.schedulingalgorithm.Process.ProcessObject;

import java.util.List;

public class NonSJFAlgorithm {

    public static double[] waitingTime(List<ProcessObject> processList) {
        int n = processList.size();
        double[] waiting = new double[n];
        int numProcess = 0, indexMin = 0;
        double time = 0.d, timeMin;
        boolean[] marked = new boolean[n];
        while (numProcess < n) {
            timeMin = Double.MAX_VALUE;
            for (int i = 0; i < n; ++i) {
                if (processList.get(i).getArrivalTime() <= time && processList.get(i).getBurstTime() < timeMin && !marked[i]) {
                    timeMin = processList.get(i).getBurstTime();
                    indexMin = processList.get(i).getId() - 1;
                }
            }
            marked[indexMin] = true;
            waiting[indexMin] = time - processList.get(indexMin).getArrivalTime();
            time += processList.get(indexMin).getBurstTime();
            numProcess++;
        }
        return waiting;
    }

    public static double[] turnAroundTime(List<ProcessObject> processList) {
        double[] waiting = waitingTime(processList);
        double[] turn_around = new double[processList.size()];
        for (int i = 0; i < processList.size(); ++i) {
            turn_around[i] = waiting[i] + processList.get(i).getBurstTime();
        }
        return turn_around;
    }
}
