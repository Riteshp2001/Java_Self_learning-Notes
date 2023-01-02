package GRAPHS;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class Graph<T> {
    //Graph is a Data Structure which contains nodes and edges
    //now graphs are of two types
    //1) Directed Graph : this graph specifies the direction of next node which it is pointing one node can have multiple input nodes
    //i) InDegree : how many nodes are pointing towards the particular node
    //ii) OutDegree : how many edges are pointing outward with respect to particular node
    //2) Undirected Graph : these graph have edges that do not have a direction multiple nodes can have multiple inward as well as outward nodes
    //i) Degree(v) : number of nodes edges connected to particular node

    static int v, u;


    void addEdges(Map<T, ArrayList<T>> graph, T u, T v, int type) {
//        type = 0 -> Undirected Graph
//        type = 1 -> Directed Graph
        graph.get(u).add(v);
        if (type == 0) {
            graph.get(v).add(u);
        }
    }

    void printAdjacencyGraph(Map<T, ArrayList<T>> graph) {
        for (Map.Entry<T, ArrayList<T>> entry : graph.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    ////////////////Adjacency Matrix/////////////////////////////

    int col;
    private final int[][] arrMatrix;

    public Graph(int col) {
        this.col = col;
        arrMatrix = new int[col][col];
    }

    public void addInGraphMatrix(int[][] arr, int u, int v, int type) {
        arr[u][v] = 1;
        if (type == 0) {
            arr[u][v] = 1;
        }
    }

    public void removeFromGraphMatrix(int[][] arr, int u, int v, int type) {
        arr[u][v] = 0;
        if (type == 0) {
            arr[u][v] = 0;
        }
    }

    public void printGraphMatrix(int[][] arrMatrix) {
        for (int i = 0; i < arrMatrix.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < arrMatrix[i].length; j++) {
                System.out.print(arrMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Adjacency Graphs

        LinkedHashMap<Integer, ArrayList<Integer>> graph = new LinkedHashMap<>();
        System.out.print("Print Number of nodes:");
        u = sc.nextInt();

        System.out.print("Print Number of Edges:");
        v = sc.nextInt();

        Graph<Integer> g = new Graph<>(6);
        for (int i = 0; i < v; i++) {
            graph.put(i, new ArrayList<>());
        }
        g.addEdges(graph, 0, 1, 0);
        g.addEdges(graph, 0, 2, 0);
        g.addEdges(graph, 0, 3, 0);
        g.addEdges(graph, 2, 3, 0);

        System.out.println("Adjacency List :");
        g.printAdjacencyGraph(graph);

        //////////////////////////////////////////////////////////////////////////

        //Adjacency Matrix

        Graph<Integer> g1 = new Graph<>(6);

        g1.addInGraphMatrix(g.arrMatrix, 0, 1, 0);
        g1.addInGraphMatrix(g.arrMatrix, 0, 2, 0);
        g1.addInGraphMatrix(g.arrMatrix, 0, 3, 0);
        g1.addInGraphMatrix(g.arrMatrix, 2, 3, 0);

        System.out.println("\nAdjacency Matrix :");
        g1.printGraphMatrix(g.arrMatrix);
    }
}
