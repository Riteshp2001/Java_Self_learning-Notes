package GRAPHS;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class DFS_Graph {
    private final int v;
    private final List<Integer>[] adj;
    private final boolean[] visited;

    DFS_Graph(int v) {
        this.v = v;
        visited = new boolean[v];
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public void DFS(int s) {
        //Iterative way

        Vector<Boolean> visited = new Vector<>(v);
        for (int i = 0; i < v; i++)
            visited.add(false);

        // Create a stack for DFS
        Stack<Integer> stack = new Stack<>();

        // Push the current source node
        stack.push(s);

        while (!stack.empty()) {
            // Pop a vertex from stack and print it
            s = stack.peek();
            stack.pop();

            // Stack may contain same vertex twice. So we need to print the popped item only if it is not visited.
            if (!visited.get(s)) {
                System.out.print(s + " ");
                visited.set(s, true);
            }

            // Get all adjacent vertices of the popped vertex s If a adjacent has not been visited, then push it to the stack.

            for (int v : adj[s]) {
                if (!visited.get(v))
                    stack.push(v);
            }

        }
        //Recursive Way

//        visited[s]=true;
//        System.out.print(s+" ");
//        Iterator<Integer> i =  adj[s].listIterator();
//        while(i.hasNext()){
//            while (i.hasNext()) {
//                int adj = i.next();
//                if (!visited[adj]) {
//                    DFS(adj);
//                }
//            }
//        }
    }

    public static void main(String[] args) {
        DFS_Graph g = new DFS_Graph(5);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 0);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 1);
        g.addEdge(2, 4);
        g.addEdge(3, 0);
        g.addEdge(4, 2);

        System.out.println("Following is Depth First Traversal ");

        g.DFS(0);//0,1,2,4,3
    }
}
