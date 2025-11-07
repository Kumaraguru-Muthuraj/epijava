package educative30;
import java.util.*;

class TreeNode<T> {
     T data;
     TreeNode<T> left;
     TreeNode<T> right;

     TreeNode(T data) {
         this.data = data;
         this.left = null;
         this.right = null;
     }
}

public class BSTFromSorted {
    public static TreeNode<Integer> bst(int[] nums, int s, int e) {
        if (s > e ) {
            return null;
        }
        int mid = s + (e - s) / 2;
        TreeNode<Integer> root = new TreeNode<>(nums[mid]);
        root.left = bst(nums, s, mid - 1);
        root.right = bst(nums, mid + 1, e);
        return root;
    }
    public static TreeNode<Integer> sortedArrayToBST(int[] nums) {
        return bst(nums, 0, nums.length - 1);
    }
    public static void traverse(TreeNode<Integer> root) {
        if (root != null) {
            traverse(root.left);
            System.out.print(root.data + ", ");
            traverse(root.right);
        }
    }
    public static void main(String[] args) {
        int[] sort = {}; //{2, 4, 6, 8, 10, 12, 13, 15 ,17};
        TreeNode<Integer> root = sortedArrayToBST(sort);
        traverse(root);
    }
}