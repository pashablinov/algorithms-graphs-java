import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    // Задача 1.4. Распространение новости
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt();
        int t = scanner.nextInt();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.get(u).add(v);
        }

        int[] distance = new int[n];
        int[] parent = new int[n];
        Arrays.fill(distance, -1);
        Arrays.fill(parent, -1);

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        distance[s] = 0;
        queue.add(s);

        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int to : graph.get(v)) {
                if (distance[to] == -1) {
                    distance[to] = distance[v] + 1;
                    parent[to] = v;
                    queue.add(to);
                }
            }
        }

        StringBuilder distances = new StringBuilder("distances=");
        for (int i = 0; i < n; i++) {
            if (i > 0) distances.append(' ');
            distances.append(distance[i]);
        }
        System.out.println(distances);

        if (distance[t] == -1) {
            System.out.println("distance=-1 path=-1");
            return;
        }

        ArrayList<Integer> path = new ArrayList<>();
        for (int v = t; v != -1; v = parent[v]) {
            path.add(v);
        }
        Collections.reverse(path);

        StringBuilder result = new StringBuilder("distance=" + distance[t] + " path=");
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) result.append(' ');
            result.append(path.get(i));
        }
        System.out.println(result);
    }
}
