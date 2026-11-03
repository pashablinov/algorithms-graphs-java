import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    // Задача 4.4. Установка пакетов
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int dependency = scanner.nextInt();
            int pack = scanner.nextInt();
            graph.get(dependency).add(pack);
        }

        int[] color = new int[n];
        int[] parent = new int[n];
        int[] next = new int[n];
        Arrays.fill(parent, -1);
        ArrayList<Integer> order = new ArrayList<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int start = 0; start < n; start++) {
            if (color[start] != 0) continue;
            color[start] = 1;
            stack.push(start);

            while (!stack.isEmpty()) {
                int v = stack.peek();
                if (next[v] == graph.get(v).size()) {
                    stack.pop();
                    color[v] = 2;
                    order.add(v);
                    continue;
                }

                // Запоминаем следующее ребро, чтобы продолжить обход после возврата.
                int to = graph.get(v).get(next[v]);
                next[v]++;

                if (color[to] == 0) {
                    parent[to] = v;
                    color[to] = 1;
                    stack.push(to);
                } else if (color[to] == 1) {
                    ArrayList<Integer> cycle = new ArrayList<>();
                    for (int current = v; current != to; current = parent[current]) {
                        cycle.add(current);
                    }
                    cycle.add(to);
                    Collections.reverse(cycle);
                    cycle.add(to);

                    StringBuilder result = new StringBuilder("CYCLE");
                    for (int vertex : cycle) {
                        result.append(' ').append(vertex);
                    }
                    System.out.println(result);
                    return;
                }
            }
        }

        Collections.reverse(order);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < order.size(); i++) {
            if (i > 0) result.append(' ');
            result.append(order.get(i));
        }
        System.out.println(result);
    }
}
