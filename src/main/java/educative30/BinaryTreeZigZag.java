package educative30;


import java.util.*;

import static educative30.BSTFromSorted.sortedArrayToBST;
import static educative30.BSTFromSorted.traverse;

/**
 * There is a solution without tracking the level. This is done by polling exactly the number of nodes added and once done
 * flip the order of adding. Reverse is not needed. The adding can be done from first or last depending on the reverse flag.
 */
public class BinaryTreeZigZag {
    static class NodeWithLevel {
        public NodeWithLevel(TreeNode<Integer> node, Integer level) {
            this.node = node;
            this.level = level;
        }
        public TreeNode<Integer> node;
        public Integer level;
    }
    public static List<List<Integer>> zigzagLevelOrder(TreeNode<Integer> root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (root == null)
            return results;

        Queue<NodeWithLevel> queue = new LinkedList<>();
        queue.add(new NodeWithLevel(root, 1));
        Integer prevLevel = 0;
        List<Integer> row = new ArrayList<>();
        NodeWithLevel nodeWithLevel = null;
        while (!queue.isEmpty()) {
            nodeWithLevel = queue.poll();
            if (nodeWithLevel.node.left != null) {
                queue.add(new NodeWithLevel(nodeWithLevel.node.left, nodeWithLevel.level + 1));
            }
            if (nodeWithLevel.node.right != null) {
                queue.add(new NodeWithLevel(nodeWithLevel.node.right, nodeWithLevel.level + 1));
            }

            if (!prevLevel.equals(nodeWithLevel.level)) {
                if (row.size() != 0) {
                    if (nodeWithLevel.level % 2 != 0) {
                        Collections.reverse(row);
                    }
                    results.add(List.copyOf(row));
                }
                row = new ArrayList<>();
            }
            row.add(nodeWithLevel.node.data);
            prevLevel = nodeWithLevel.level;
        }
        if (nodeWithLevel.level % 2 == 0) {
            Collections.reverse(row);
            results.add(List.copyOf(row));
        } else {
            results.add(List.copyOf(row));
        }
        return results;
    }
    public static void main(String[] args) {
        int[] sort = {-25, -23, -10, 45, 50};
        TreeNode<Integer> root = sortedArrayToBST(sort);
        traverse(root);
        List<List<Integer>> levels = zigzagLevelOrder(root);
        for (List<Integer> row : levels) {
            System.out.println();
            for (Integer e : row) {
                System.out.print(e + ", ");
            }
        }
    }
}
