package Scheduling_Algorithms;

public class Process {
    private int pid;
    private static int priority;
    private int burstTime;
    private final int arrivalTime;

    public int getPriority() {
        return priority;
    }

    int waitingTime;
    int turnAroundTime;
    int completionTime;
    int averageTime;

    public int getPid() {
        return pid;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    Process(int pid, int burstTime, int arrivalTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }

    Process(int pid, int priority, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
    }

    public Process() {
        this.arrivalTime = 0;
        this.burstTime = 0;
        this.completionTime = 0;
        this.turnAroundTime = 0;
        this.waitingTime = 0;
    }

}
