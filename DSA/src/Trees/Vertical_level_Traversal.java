package Trees;

import java.util.*;

import static Trees.Tree_Visual.createTree;

class Pair {
    Node node;
    int num;

    Pair(int d, Node node) {
        this.num = d;
        this.node = node;
    }
}


public class Vertical_level_Traversal {
    //     1(0)
    //(-1)2  1(1)
    static void verticalTraversal(Node root) {
        Map<Integer, List<Integer>> map = new TreeMap<>();//sorted order
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, root));

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            map.computeIfAbsent(curr.num, k -> new ArrayList<>());
            map.get(curr.num).add(curr.node.data);
            if (curr.node.left != null) {
                q.add(new Pair(curr.num - 1, curr.node.left));
            }
            if (curr.node.right != null) {
                q.add(new Pair(curr.num + 1, curr.node.right));
            }
        }
        map.forEach((key, value1) -> {
            System.out.print(key + " : ");
            value1.forEach(value -> System.out.print(value + " "));
            System.out.println();
        });
    }


    public static void main(String[] args) {
        Node root = createTree();
        verticalTraversal(root);
    }
}
