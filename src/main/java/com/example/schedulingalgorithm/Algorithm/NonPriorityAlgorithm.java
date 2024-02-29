package com.example.schedulingalgorithm.Algorithm;

import com.example.schedulingalgorithm.Process.ProcessObject;

import java.util.List;

public class NonPriorityAlgorithm {

    public static double[] waitingTime(List<ProcessObject> processList) {
        int n = processList.size();
        double[] wt = new double[n];
        int numProcess = 0, indexMin = 0, priorityMin;
        double time = 0.d;
        boolean[] marked = new boolean[n];
        while (numProcess < n) {
            priorityMin = Integer.MAX_VALUE;
            for (int i = 0; i < n; ++i) {
                if (processList.get(i).getArrivalTime() <= time && processList.get(i).getPriority() < priorityMin && !marked[i]) {
                    priorityMin = processList.get(i).getPriority();
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
        double[] ta = new double[processList.size()];
        double[] wt = waitingTime(processList);
        for (int i = 0; i < processList.size(); ++i) {
            ta[i] = wt[i] + processList.get(i).getBurstTime();
        }
        return ta;
    }
}
