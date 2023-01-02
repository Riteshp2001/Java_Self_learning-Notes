package Scheduling_Algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FCFS$First_Come_First_Serve$_SortedBurst {
    public static void main(String[] args) {
        List<Process> q = new ArrayList<>();
        System.out.print("Enter the number of processes ");
        Scanner sc = new Scanner(System.in);
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
        q.sort(Comparator.comparingInt(Process::getArrivalTime));
        Process p1 = new Process();
        System.out.println("\n" + "PID | " + "AT | " + "BT | " + "CT | " + "TAT | WT | STATUS");
        while (!q.isEmpty()) {
            Process p = q.remove(0);
            p1.completionTime += p.getBurstTime();
            if (p1.completionTime < 0) p1.completionTime = 0;
            p1.turnAroundTime = p1.completionTime - p.getArrivalTime();
            if (p1.turnAroundTime < 0) p1.turnAroundTime = 0;
            p1.waitingTime = p1.turnAroundTime - p.getBurstTime();
            if (p1.waitingTime < 0) p1.waitingTime = 0;
            p1.averageTime += p1.waitingTime;
            System.out.println(p.getPid() + " | " + p.getArrivalTime() + " | " + p.getBurstTime() + " | " + p1.completionTime + " | " + p1.getTurnAroundTime() + " | " + p1.waitingTime + " | Completed");
        }
        System.out.println("Average Waiting Time : " + Math.abs(((float) p1.averageTime / process)));
    }
}
