package com.example.schedulingalgorithm.Process;

public class ProcessObject implements Comparable<ProcessObject>{
    protected int id;
    protected double burstTime;
    protected double arrivalTime;
    protected int priority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(double burstTime) {
        this.burstTime = burstTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ProcessObject(int id, double burstTime) {
        this.setId(id);
        this.setBurstTime(burstTime);
    }

    public ProcessObject(int id, double burstTime, double arrivalTime) {
        this.setId(id);
        this.setBurstTime(burstTime);
        this.setArrivalTime(arrivalTime);
    }

    public ProcessObject(int id, double burstTime, double arrivalTime, int priority) {
        this.setId(id);
        this.setBurstTime(burstTime);
        this.setArrivalTime(arrivalTime);
        this.setPriority(priority);
    }

    @Override
    public int compareTo(ProcessObject o) {
        if (this.getArrivalTime() != o.getArrivalTime()) {
            return Double.compare(this.getArrivalTime(), o.getArrivalTime());
        } else if (this.getPriority() != o.getPriority()) {
            return Integer.compare(this.getPriority(), o.getPriority());
        } else
            return Integer.compare(this.getId(), o.getId());
    }
}
