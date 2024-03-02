package com.example.schedulingalgorithm.Process;

public class PairOther extends Pair{
    private double executeTime;

    public void setExecuteTime(double executeTime) {
        this.executeTime = executeTime;
    }

    public double getExecuteTime() {
        return executeTime;
    }

    public PairOther(int id, double startTime, double executeTime) {
        this.id = id;
        this.startTime = startTime;
        this.executeTime = executeTime;
    }

}
