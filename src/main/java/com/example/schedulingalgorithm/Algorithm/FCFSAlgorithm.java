package com.example.schedulingalgorithm.Algorithm;

import com.example.schedulingalgorithm.Process.ProcessObject;

import java.util.List;

public class FCFSAlgorithm {

    // Hàm tính thời gian chờ
    public static double[] waitingTime(List<ProcessObject> processList) {
        int n = processList.size();
        double[] waiting = new double[n];

        for (int i = 1; i < n; ++i) {
            waiting[processList.get(i).getId() - 1] = waiting[processList.get(i - 1).getId() - 1] +
                    processList.get(i - 1).getBurstTime() -
                    (processList.get(i).getArrivalTime() - processList.get(i - 1).getArrivalTime());
        }
        return waiting;
    }

    public static double[] responseTime(List<ProcessObject> processList) {
        double[] waiting = waitingTime(processList);
        double[] response = new double[processList.size()];
        for (ProcessObject processObject : processList) {
            response[processObject.getId() - 1] = waiting[processObject.getId() - 1] + processObject.getBurstTime();
        }
        return response;
    }


    // Hàm tính thời gian hoàn thành
    public static double[] turnAroundTime(List<ProcessObject> processList) {
        double[] waiting = waitingTime(processList);
        double[] turn_around = new double[processList.size()];
        for (ProcessObject processObject : processList) {
            turn_around[processObject.getId() - 1] = waiting[processObject.getId() - 1] + processObject.getBurstTime();
        }
        return turn_around;
    }
}
