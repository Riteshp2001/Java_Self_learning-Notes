package Trees;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import static Trees.Tree_Visual.createTree;

public class Top_view_BinaryTree {
    static void topView(Node root) {
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, root));

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            if (!map.containsKey(curr.num)) {
                map.put(curr.num, curr.node.data);
            }
            /*for BOTTOM VIEW
            simply put:
            map.put(curr.num, curr.node.data);
            removing all if condition
             */
            if (curr.node.left != null) {
                q.add(new Pair(curr.num - 1, curr.node.left));
            }
            if (curr.node.right != null) {
                q.add(new Pair(curr.num + 1, curr.node.right));
            }
        }
        map.forEach((key, value) -> {
            System.out.print(key + " : ");
            System.out.print(value + " ");
            System.out.println();
        });
    }

    public static void main(String[] args) {
        Node root = createTree();
        topView(root);

    }


}


