package Scheduling_Algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SJF_Shortest_Job_First_Premmtive {
    public static void main(String[] args) {
        List<Process> q = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Time Quantum : ");
        int timeQuantum = sc.nextInt();
        System.out.print("Enter the number of processes ");
        int process = sc.nextInt();
        int n = 1;
        while (n <= process) {
            System.out.print("Enter the arrival time of process-" + n + " : ");
            int arrival = sc.nextInt();
            System.out.print("Enter the burst time of process-" + n + " : ");
            int burst = sc.nextInt();
            q.add(new Process(n, burst, arrival));
            System.out.println();
            n++;
        }
        Process p1 = new Process();

        q.sort(Comparator.comparingInt(Process::getArrivalTime));//sorting process according to arrival time first
        //Handling the condition for the very first process
        Process firstJob = q.remove(0);
        Process secondJob = q.get(0);
        int processNo = firstJob.getPid();
        int saveBurstValue = firstJob.getBurstTime();
        if (firstJob.getBurstTime() <= timeQuantum || firstJob.getBurstTime() < secondJob.getBurstTime()) {
            p1.completionTime += firstJob.getBurstTime();
            p1.turnAroundTime = p1.completionTime - firstJob.getArrivalTime();
            p1.waitingTime = p1.turnAroundTime - firstJob.getBurstTime();
            if (p1.waitingTime < 0) p1.waitingTime = 0;
            p1.averageTime += p1.waitingTime;
            System.out.println("\n" + "PID | " + "AT | " + "BT | " + "CT | " + "TAT | WT | STATUS");
            System.out.println(firstJob.getPid() + " | " + firstJob.getArrivalTime() + " | " + firstJob.getBurstTime() + " | " + p1.completionTime + " | " + p1.getTurnAroundTime() + " | " + p1.waitingTime + " | Completed");
        } else {
            p1.completionTime += timeQuantum;
            p1.turnAroundTime = p1.completionTime - firstJob.getArrivalTime();
            p1.waitingTime = 0;
            System.out.println("\n" + "PID | " + "AT | " + "BT | " + "CT | " + "TAT | WT | STATUS");
            System.out.println(firstJob.getPid() + " | " + firstJob.getArrivalTime() + " | " + firstJob.getBurstTime() + " | " + p1.completionTime + " | " + p1.getTurnAroundTime() + " | " + p1.waitingTime + " | Running");
            firstJob.setBurstTime(firstJob.getBurstTime() - timeQuantum);
            q.add(firstJob);
        }

        q.sort(Comparator.comparingInt(Process::getBurstTime));//sorting process according to burst time now

        while (!q.isEmpty()) {
            Process p = q.remove(0);
            p1.completionTime += p.getBurstTime();
            if (p1.completionTime < 0) p1.completionTime = 0;
            p1.turnAroundTime = p1.completionTime - p.getArrivalTime();
            if (p1.turnAroundTime < 0) p1.turnAroundTime = 0;
            if (processNo == p.getPid()) p1.waitingTime = p1.turnAroundTime - saveBurstValue;
            else p1.waitingTime = p1.turnAroundTime - p.getBurstTime();
            if (p1.waitingTime < 0) p1.waitingTime = 0;
            p1.averageTime += p1.waitingTime;
            System.out.println(p.getPid() + " | " + p.getArrivalTime() + " | " + p.getBurstTime() + " | " + p1.completionTime + " | " + p1.getTurnAroundTime() + " | " + p1.waitingTime + " | Completed");
        }
        System.out.println("Average Waiting Time : " + Math.abs((float) p1.averageTime / process));
    }
}
