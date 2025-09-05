package epi;

public class BinarySearchTrees {
    /* 15.4 - Compute LCA for BST
     */
    public static void testLCA(int s, int b) {
        // s < t
        BinarySearchTree bst = BinaryTrees.getCustomBST();
        Node2 lca = getLCA(bst.root, s, b);
    }
    public static Node2 getLCA(Node2 cur, int s, int b) {
        Node2 parent = null;
        while (cur != null && (cur.value < s || b < cur.value)) {
            while (b < cur.value) {
                parent = cur;
                cur = cur.left;
            }
            while (cur.value < s) {
                parent = cur;
                cur = cur.right;
            }
        }
        //Check if s & b exist in the respective subtrees.
        if (s == b) {
            return parent;
        }
        if (cur != null && (s <= cur.value && cur.value <= b)) {
            return cur;
        }
        return null;
    }

    public static void main(String[] args) {
        //15.4 - LCA
        testLCA(60, 60);

        if (true) {
            return;
        }
    }

}
