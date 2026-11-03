import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static class Edge {
        int to;
        long weight;

        Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class State {
        int vertex;
        long distance;

        State(int vertex, long distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    // Задача 2.10. Большие расстояния
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt();
        int t = scanner.nextInt();

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            long weight = scanner.nextLong();
            graph.get(u).add(new Edge(v, weight));
            graph.get(v).add(new Edge(u, weight));
        }

        long[] distance = new long[n];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[s] = 0;

        PriorityQueue<State> queue = new PriorityQueue<>(
                Comparator.comparingLong(state -> state.distance));
        queue.add(new State(s, 0));

        while (!queue.isEmpty()) {
            State current = queue.remove();
            int v = current.vertex;
            if (current.distance != distance[v]) continue;
            if (v == t) break;

            for (Edge edge : graph.get(v)) {
                long newDistance = distance[v] + edge.weight;
                if (newDistance < distance[edge.to]) {
                    distance[edge.to] = newDistance;
                    queue.add(new State(edge.to, newDistance));
                }
            }
        }

        if (distance[t] == Long.MAX_VALUE) {
            System.out.println("distance=-1");
        } else {
            System.out.println("distance=" + distance[t]);
        }
    }
}
