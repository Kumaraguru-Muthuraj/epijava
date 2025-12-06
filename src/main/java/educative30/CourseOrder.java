package educative30;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * This uses BFS - Change to DFS and check how it works.
 */
public class CourseOrder {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        int completed = 0;
        for (int[] pre : prerequisites) {
            int a = pre[0];
            int b = pre[1];
            graph.get(b).add(a);

            inDegree[a]++;
        }

        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            completed++;
            List<Integer> toVerts = graph.get(vertex);
            for (Integer toVert : toVerts) {
                inDegree[toVert]--;
                if (inDegree[toVert] == 0) {
                    queue.add(toVert);
                }
            }
        }

        return completed == numCourses;
    }
    public static void main(String[] args) {
        int numCourses1 = 2;
        int[][] prerequisites1 = { {1, 0} };
        System.out.println(canFinish(numCourses1, prerequisites1));

        int numCourses2 = 7;
        int[][] prerequisites2 = {{2,1}, {1,0}, {3,0}, {4,0}/*, {6,5}, {5,6}*/};
        System.out.println(canFinish(numCourses2, prerequisites2));
    }
}
