import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static class Edge {
        int u;
        int v;
        long weight;

        Edge(int u, int v, long weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static int[] parent;
    static int[] size;

    static int find(int v) {
        if (parent[v] != v) {
            parent[v] = find(parent[v]);
        }
        return parent[v];
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;

        if (size[a] < size[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        parent[b] = a;
        size[a] += size[b];
        return true;
    }

    // Задача 3.3. Электросеть
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            long weight = scanner.nextLong();
            edges.add(new Edge(u, v, weight));
        }
        edges.sort(Comparator.comparingLong(edge -> edge.weight));

        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        long cost = 0;
        int count = 0;
        for (Edge edge : edges) {
            if (union(edge.u, edge.v)) {
                cost += edge.weight;
                count++;
                if (count == n - 1) break;
            }
        }

        if (count == n - 1) {
            System.out.println("cost=" + cost + " edges=" + count);
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }
}
