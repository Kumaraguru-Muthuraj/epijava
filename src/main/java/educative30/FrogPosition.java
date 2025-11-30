package educative30;

import java.util.*;

/**
 * Statement
 * You are given an undirected tree with n vertices labeled from
 * 1 to n. A frog starts at vertex 1, at time 0, and makes one move per second.
 *
 * At each step, the frog follows these rules:
 * 1. Move to an unvisited neighbor:
 * If the frog has unvisited neighbors, it jumps to one of them, chosen uniformly at random (equal probability for each choice).
 *
 * 2. No revisiting:
 * The frog can not jump back to a vertex it has already visited.
 *
 * 3. Stay when stuck
 * The frog will keep jumping at its current vertex if there are no unvisited neighbors.
 *
 * The tree is represented as an array of edges, where edges[i] = [ai, bi] means an edge connecting the vertices ai and bi. Your task is to return the probability that, after t seconds, the frog is on the vertex target.
 *
 * Note: Your answer will be accepted if its absolute difference from the actual value is at most
 * 10 power −5
 *
 * Constraints:
 * 1 ≤ n ≤100
 * edges.length == n−1
 * edges[i].length == 2
 * 1 ≤ ai, bi≤ n
 * 1 ≤ t ≤50
 * 1 ≤ target ≤n
 */
public class FrogPosition {
    class VertexStatus {
        public VertexStatus(Integer vertex, double prob) {
            this.vertex = vertex;
            this.prob = prob;
        }
        Integer vertex;
        double prob;
        @Override
        public String toString() {
            return "<Vertex - " + this.vertex + ", " + "Prob - " + prob + ">";
        }
    }
    class Path {
        Path(int size) {
            vertexStatusList = new ArrayList<>(size);
        }
        public int size() {
            return vertexStatusList.size();
        }
        public void add(VertexStatus vertexStatus) {
            vertexStatusList.add(vertexStatus);
            cumProb *= vertexStatus.prob;
        }
        public void copyAll(Path path) {
            this.cumProb = path.cumProb;
            for (VertexStatus v : path.vertexStatusList) {
                this.vertexStatusList.add(new VertexStatus(v.vertex, v.prob));
            }
        }
        public void removeLast() {
            cumProb /= vertexStatusList.get(vertexStatusList.size() - 1).prob;
            vertexStatusList.remove(vertexStatusList.size() - 1);
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (VertexStatus v : vertexStatusList) {
                sb.append(v).append(", ");
            }
            sb.append("Cumulative Prob - ").append(cumProb);
            return sb.toString();
        }
        double cumProb = 1.0;
        List<VertexStatus> vertexStatusList = null;
    }
    public boolean[] visited;
    List<List<Integer>> graph = new LinkedList<>();
    List<Path> paths = new LinkedList<>();
    int target = 0;
    int sec = 0;

    public double frogPosition(int n, int[][] edges, int t, int targ) {
        sec = t;
        target = targ;
        visited = new boolean[n+1];
        Arrays.fill(visited, false);

        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<Integer>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        Path path = new Path(0);
        dfs(path, 1, 1, 0);
        printPaths();

        return 0.0;
    }

    public void printPaths() {
        System.out.println();
        for (Path path : paths) {
            System.out.println(path);
        }
    }

    public void dfs(Path path, int strVertex, double prob, int tSec) {
        tSec += 1;
        System.out.println("Visited - " + strVertex);
        visited[strVertex] = true;
        path.add(new VertexStatus(strVertex, prob));
        List<Integer> toVerts = graph.get(strVertex);
        double p = 1.0 / toVerts.size();
        if (!toVerts.isEmpty()) {
            for (Integer toVert : toVerts) {
                //if (!visited[toVert]) {
                    dfs(path, toVert, p, tSec);
                //}
            }
        } else {
            Path completedPath = new Path(path.size());
            completedPath.copyAll(path);
            paths.add(completedPath);
        }
        path.removeLast();
    }


    public static void main(String[] args) {
        //int[][] edg = {{1, 2}, {1, 3}, {1, 4}, {3, 5}, {3, 6}};
        int[][] edg = {{1, 5}, {1, 2}, {1, 4}, {2, 3}, {2, 6}, {2, 7}, {4, 7}, {7, 8}};
        FrogPosition fp = new FrogPosition();
        fp.frogPosition(8, edg, 4, 4);
    }
}
