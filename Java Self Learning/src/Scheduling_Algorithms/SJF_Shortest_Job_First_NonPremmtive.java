package Scheduling_Algorithms;

import java.util.*;

public class SJF_Shortest_Job_First_NonPremmtive {
    //criteria AT+BT
    public static void main(String[] args) {
        List<Process> q = new ArrayList<>();
        List<Process> readyQueue = new LinkedList<>();
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
        Process p1 = new Process();

        q.sort((o1, o2) -> {
                    if (o1.getArrivalTime() < o2.getArrivalTime()) {
                        return -1;
                    } else if (o1.getArrivalTime() > o2.getArrivalTime()) {
                        return 1;
                    } else {
                        if (o1.getBurstTime() < o2.getBurstTime()) {
                            return -1;
                        } else if (o1.getBurstTime() > o2.getBurstTime()) {
                            return 1;
                        }
                    }
                    return 0;
                }
        );//sorting process according to arrival time first
//        Handeling the condition for the very first process
        Process firstJob = q.remove(0);
        p1.completionTime += (p1.completionTime < 0) ? 0 : firstJob.getBurstTime();
        p1.turnAroundTime = (p1.turnAroundTime < 0) ? 0 : p1.completionTime - firstJob.getArrivalTime();
        p1.waitingTime += (p1.waitingTime < 0) ? 0 : p1.turnAroundTime - firstJob.getBurstTime();
        p1.averageTime += p1.waitingTime;
        for (int i = 0; i < q.size(); i++) {
            if (q.get(i).getArrivalTime() <= p1.completionTime) {
                readyQueue.add(q.remove(i));
                i--;
            }
        }
        System.out.println("\n" + "PID | " + "AT | " + "BT | " + "CT | " + "TAT | WT | STATUS");
        System.out.println(firstJob.getPid() + " | " + firstJob.getArrivalTime() + " | " + firstJob.getBurstTime() + " | " + p1.completionTime + " | " + p1.getTurnAroundTime() + " | " + p1.waitingTime + " | Completed");


        q.sort(Comparator.comparingInt(Process::getBurstTime));//sorting process according to burst time now
        while (!q.isEmpty() || !readyQueue.isEmpty()) {
            Process p = readyQueue.remove(0);
            p1.completionTime += (p1.completionTime < 0) ? 0 : p.getBurstTime();
            p1.turnAroundTime = (p1.turnAroundTime < 0) ? 0 : p1.completionTime - p.getArrivalTime();
            p1.waitingTime += (p1.waitingTime < 0) ? 0 : p1.turnAroundTime - p.getBurstTime();
            p1.averageTime += p1.waitingTime;
            if (!q.isEmpty()) {
                for (int i = 0; i < q.size(); i++) {
                    if (q.get(i).getArrivalTime() <= p1.completionTime) {
                        readyQueue.add(q.remove(i));
                        i--;
                    }
                }
            }
            readyQueue.sort((o1, o2) -> {
                        if (o1.getBurstTime() < o2.getBurstTime()) {
                            return -1;
                        } else if (o1.getBurstTime() > o2.getBurstTime()) {
                            return 1;
                        } else {
                            if (o1.getArrivalTime() < o2.getArrivalTime()) {
                                return -1;
                            } else if (o1.getArrivalTime() > o2.getArrivalTime()) {
                                return 1;
                            }
                        }
                        return 0;
                    }
            );
            System.out.println(p.getPid() + " | " + p.getArrivalTime() + " | " + p.getBurstTime() + " | " + p1.completionTime + " | " + p1.getTurnAroundTime() + " | " + p1.waitingTime + " | Completed");
        }
        System.out.println("Average Waiting Time : " + Math.abs(((float) p1.averageTime / process)));
    }
}
