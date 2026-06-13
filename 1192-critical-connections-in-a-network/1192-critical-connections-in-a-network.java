import java.util.*;

class Solution {

    private List<List<Integer>> result;
    private List<Integer>[] graph;
    private int[] disc;
    private int[] low;
    private int time;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        result = new ArrayList<>();
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);

            graph[u].add(v);
            graph[v].add(u);
        }

        disc = new int[n];
        low = new int[n];
        time = 1;

        dfs(0, -1);

        return result;
    }

    private void dfs(int u, int parent) {

        disc[u] = low[u] = time++;

        for (int v : graph[u]) {

            if (v == parent) {
                continue;
            }

            if (disc[v] == 0) {

                dfs(v, u);

                low[u] = Math.min(low[u], low[v]);

                if (low[v] > disc[u]) {
                    result.add(Arrays.asList(u, v));
                }

            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}