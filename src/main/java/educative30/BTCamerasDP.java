package educative30;

import java.util.Arrays;
import java.util.List;

public class BTCamerasDP {
    // Recursive function that returns a list of three integers representing
    // the minimum number of cameras needed for each state
    public List<Integer> recurse(TreeNode<Integer> node) {
        if (node == null)
            return Arrays.asList(0, 0, Integer.MAX_VALUE);

        List<Integer> L = recurse(node.left);
        List<Integer> R = recurse(node.right);

        int leftCost = Math.min(L.get(1), L.get(2));
        int rightCost = Math.min(R.get(1), R.get(2));

        long dp0 = (long) L.get(1) + R.get(1);
        long dp1 = Math.min((long) L.get(2) + rightCost, (long) R.get(2) + leftCost);
        long dp2 = 1 + Math.min((long) L.get(0), leftCost) + Math.min((long) R.get(0), rightCost);

        dp0 = Math.min(dp0, Integer.MAX_VALUE);
        dp1 = Math.min(dp1, Integer.MAX_VALUE);
        dp2 = Math.min(dp2, Integer.MAX_VALUE);

        return Arrays.asList((int) dp0, (int) dp1, (int) dp2);
    }

    public int minCameraCover(TreeNode<Integer> root) {
        List<Integer> res = recurse(root);
        return Math.min(res.get(1), res.get(2));
    }
}
