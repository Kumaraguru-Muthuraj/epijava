package educative30;

import java.util.ArrayList;
import java.util.Arrays;

/** You are given:
 * n people, labeled 0 to n - 1. richer[i] = [x, y] means person x is richer than person y. quiet[i] gives how quiet person i is (smaller = quieter).
 * You need to return an array res where:
 * res[i] is the index of the quietest person among all people who are at least as rich as i (including i themselves).
 */
public class LoudAndRich {
    public static int[] res;
    public static ArrayList<ArrayList<Integer>> graph;
    public static int[] quiet;
    public static int[] loudAndRich(int[][] richer, int[] tempQuiet) {
        quiet = tempQuiet;

        res = new int[quiet.length];
        Arrays.fill(res, -1);

        graph = new ArrayList<>(quiet.length);
        for (int i = 0; i < quiet.length; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int[] rich : richer) {
            graph.get(rich[1]).add(rich[0]);
        }

        for (ArrayList<Integer> poorer : graph) {
            epi.Arrays.print(poorer);
        }

        for (int i = 0; i < quiet.length; i++) {
            dfsGetQuietestPersonIdx(i);
        }

        return res;
    }

    public static int dfsGetQuietestPersonIdx(int personIdx) {
        if (res[personIdx] != -1) {
            return res[personIdx];
        }
        ArrayList<Integer> richers = graph.get(personIdx);
        Integer quietest = personIdx;
        for (Integer rich : richers) { // This does an exhaustive search in the graph
            int candidate = dfsGetQuietestPersonIdx(rich);
            if (quiet[candidate] < quiet[quietest]) {
                quietest = candidate;
            }
        }
        res[personIdx] = quietest;
        return quietest;
    }

    public static void main(String[] args) {
        int[][] richer = {{1,0}, {2,1}, {3,1}, {3,7}, {4,3}, {5,3}, {6,3}};
        int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};
        int[] quieterPeople = loudAndRich(richer, quiet);
        epi.Arrays.print(quieterPeople);
    }
}
