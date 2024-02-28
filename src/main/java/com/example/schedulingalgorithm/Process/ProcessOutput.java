package com.example.schedulingalgorithm.Process;

public class ProcessOutput {
    private String id;
    private double waitingTime;
    private double turnAroundTime;
    private double respondTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    public double getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(double turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public double getRespondTime() {
        return respondTime;
    }

    public void setRespondTime(double respondTime) {
        this.respondTime = respondTime;
    }

    public ProcessOutput(String id, double waitingTime, double turnAroundTime, double respondTime) {
        this.setId(id);
        this.setWaitingTime(waitingTime);
        this.setTurnAroundTime(turnAroundTime);
        this.setRespondTime(respondTime);
    }
}
