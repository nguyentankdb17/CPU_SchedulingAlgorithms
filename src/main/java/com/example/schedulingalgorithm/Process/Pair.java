package com.example.schedulingalgorithm.Process;


public class Pair implements Comparable<Pair>{

    private int id;
    private double startTime;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getStartTime() {
        return startTime;
    }

    public Pair(int id, double startTime) {
        this.id = id;
        this.startTime = startTime;
    }


    @Override
    public int compareTo(Pair o) {
        return Double.compare(this.startTime, o.startTime);
    }
}
