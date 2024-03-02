package com.example.schedulingalgorithm.Algorithm;

import com.example.schedulingalgorithm.Process.PairOther;
import com.example.schedulingalgorithm.Process.ProcessObject;

import java.util.ArrayList;
import java.util.List;

public class SJFAlgorithm {

    public static List<PairOther> pairList(List<ProcessObject> processList) {
        int n = processList.size();
        List<PairOther> pairOtherList = new ArrayList<>();
        int numProcess = 0, indexMin = -1, indexMinPrev;
        double time = 0.d, timeMin = Double.MAX_VALUE;
        double[] bt = new double[n];
        for (int i = 0; i < n; ++i) {
            bt[i] = processList.get(i).getBurstTime();
        }
        while (numProcess < n) {
            indexMinPrev = indexMin;
            for (int i = 0; i < n; ++i) {
                if (bt[i] < timeMin && processList.get(i).getArrivalTime() <= time && bt[i] > 0) {
                    timeMin = bt[i];
                    indexMin = processList.get(i).getId() - 1;
                }
            }
            if (indexMinPrev != indexMin) {
                pairOtherList.add(new PairOther(indexMin + 1, time, 1.d));
            } else {
                pairOtherList.get(pairOtherList.size() - 1).setExecuteTime(pairOtherList.get(pairOtherList.size() - 1).getExecuteTime() + 1);
            }
            bt[indexMin]--;
            timeMin = bt[indexMin];
            if (bt[indexMin] == 0) {
                numProcess++;
                timeMin = Double.MAX_VALUE;
            }
            time++;
        }
        return pairOtherList;
    }

    public static double[] waitingTime(List<ProcessObject> processList) {
        int n = processList.size();
        double[] wt = new double[n];
        double[] ft = new double[n];
        for (int i = 0; i < n; ++i) {
            wt[i] = 0.d;
            ft[i] = processList.get(i).getArrivalTime();
        }
        List<PairOther> pairOtherList = pairList(processList);
        for (int i = 0; i < pairOtherList.size(); ++i) {
            int j = pairOtherList.get(i).getId() - 1;
            wt[j] += (pairOtherList.get(i).getStartTime() - ft[j]);
            ft[j] += (wt[j] + pairOtherList.get(i).getExecuteTime());
        }
        return wt;
    }

    public static double[] respondTime(List<ProcessObject> processList) {
        int n = processList.size();
        double[] rt = new double[n];
        boolean[] marked = new boolean[n];
        for (int i = 0; i < n; ++i) {
            rt[i] = 0.d;
            marked[i] = false;
        }
        List<PairOther> pairOtherList = pairList(processList);
        for (int i = 0; i < pairOtherList.size(); ++i) {
            int j = pairOtherList.get(i).getId() - 1;
            if (!marked[j]) {
                marked[j] = true;
                rt[j] = pairOtherList.get(i).getStartTime() - processList.get(j).getArrivalTime();
            }
        }
        return rt;
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
