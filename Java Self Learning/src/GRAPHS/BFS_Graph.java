package GRAPHS;// BFS algorithm in Java

import java.util.LinkedList;
import java.util.Queue;

public class BFS_Graph {
    private final int V;
    private final LinkedList<Integer>[] adj;

    // Create a graph
    BFS_Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    // Add edges to the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // BFS algorithm
    void BFS(int s) {

        boolean[] visited = new boolean[V];

        Queue<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        //System.out.println(Arrays.toString(adj));//[[1, 2, 3], [0, 3], [0, 4], [0, 1, 4], [2, 3]]
        while (queue.size() != 0) {
            //System.out.println(queue);
            //System.out.println(Arrays.toString(visited));
            s = queue.poll();
            System.out.print(s + " ");
            for (int n : adj[s]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
            //normal for-loop
//            for (int i = 0;i<adj[s].size();i++) {
//                System.out.println(adj[s]);
//                if (!visited[adj[s].get(i)]) {
//                    visited[adj[s].get(i)] = true;
//                    queue.add(adj[s].get(i));
//                }
//            }
        }
    }


    public static void main(String[] args) {
        BFS_Graph g = new BFS_Graph(5);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 0);
        g.addEdge(1, 3);
        g.addEdge(2, 0);
        g.addEdge(2, 4);
        g.addEdge(3, 0);
        g.addEdge(3, 1);
        g.addEdge(3, 4);
        g.addEdge(4, 2);
        g.addEdge(4, 3);

        System.out.println("Following is Breadth First Traversal ");

        g.BFS(0);
    }
}