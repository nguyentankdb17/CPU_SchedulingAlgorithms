package com.example.schedulingalgorithm.Process;


public class Pair implements Comparable<Pair>{
    protected int id;
    protected double startTime;

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

    public Pair() {
        this.id = 1;
        this.startTime = 0.d;
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
