package educative30;

import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static educative30.BSTFromSorted.sortedArrayToBST;
import static educative30.BSTFromSorted.traverse;

/**
 * Statement
 * You are given the root of a binary tree. Cameras can be installed on any node, and each camera can monitor itself,
 * its parent, and its immediate children.
 * Your task is to determine the minimum number of cameras required to monitor every node in the tree.
 * Constraints: The number of nodes in the tree is in the range [1,1000].
 * Node.data == 0
 */
public class BTCameras {
    public static List<List<TreeNode<Integer>>> getLevels(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        List<List<TreeNode<Integer>>> lists = new LinkedList<>();

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        int lastAdded = 1;
        while (!queue.isEmpty()) {
            int curAdded = 0;
            List<TreeNode<Integer>> levelNodes = new LinkedList<>();
            while (lastAdded > 0 && !queue.isEmpty()) {
                TreeNode<Integer> node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                    curAdded++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    curAdded++;
                }
                levelNodes.add(node);
                lastAdded--;
            }
            lists.add(levelNodes);
            lastAdded = curAdded;
        }
        return lists;
    }
    public static int getAndPrintLevels(TreeNode<Integer> root) {
        List<List<TreeNode<Integer>>> levels = getLevels(root);
        int l = 0;
        System.out.println();
        for (List<TreeNode<Integer>> level : levels) {
            System.out.print(l + " - ");
            for (TreeNode<Integer> node : level) {
                System.out.print(node.data + ", ");
            }
            System.out.println();
            l++;
        }
        return -1;
    }

    /** This works. You can call the status as Installed, Monitored and Dark.
     * Leaf nodes don't have cameras.
    */
    enum CamStatus {
        CAM_INSTALLED,
        MONITORED,
        NOT_MONITORED
    }

    public static int cameraCnt = 0;

    public static int minCameraCover(TreeNode<Integer> root) {
        cameraCnt = 0;
        if (root.left == null && root.right == null) {
            return 1;
        } else if (dfs(root) == CamStatus.NOT_MONITORED) {
            cameraCnt++;
        }
        return cameraCnt;
    }


    public static CamStatus dfs(TreeNode<Integer> node) {
        if (node == null) {
            return CamStatus.MONITORED;
        }
        CamStatus left = dfs(node.left);
        CamStatus right = dfs(node.right);
        if (left == CamStatus.NOT_MONITORED || right == CamStatus.NOT_MONITORED) {
            cameraCnt++;
            return CamStatus.CAM_INSTALLED;
        }
        if (left == CamStatus.CAM_INSTALLED || right == CamStatus.CAM_INSTALLED) {
            return CamStatus.MONITORED;
        }

        return CamStatus.NOT_MONITORED;
    }

    public static void main(String[] args) {
        int[] sort = {50, 25, 75, 10, 45, 55, 85};
        //int[] sort = {50, 25, 75, 10, 20, 15, 45, 30, 55, 60, 70, 85, 100, 120};
        BSTFromSorted bst = new BSTFromSorted();
        for (int i : sort) {
            bst.add(i);
        }
        traverse(bst.root);
        getAndPrintLevels(bst.root);
        System.out.println("Cameras - " + minCameraCover(bst.root));
    }
}
