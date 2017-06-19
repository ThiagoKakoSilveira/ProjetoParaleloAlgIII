package algorithms;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

class Pair<T1, T2> {

    public T1 first;
    public T2 second;

    public Pair(T1 t1, T2 t2) {
        first = t1;
        second = t2;
    }

    public boolean equals(Object cmp) {
        if (this == cmp) {
            return true;
        }
        if (!(cmp instanceof Pair<?, ?>)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Pair<T1, T2> p = (Pair<T1, T2>) cmp;
        return first.equals(p.first) && second.equals(p.second);
    }

    @Override
    public int hashCode() {
        return (first.toString() + second.toString()).hashCode();
    }

    public String toString() {
        return "(" + first + "," + second + ")";
    }
}

class PriorityComparator implements Comparator<Pair<Integer, Double>> {

    @Override
    public int compare(Pair<Integer, Double> a, Pair<Integer, Double> b) {
        if (a.second > b.second) {
            return 1;
        }
        if (a.second < b.second) {
            return -1;
        }
        return 0;
    }
}

public class Graph {

    private ArrayList<Pair<Double, Double>> vertices;
    private LinkedList<Pair<Integer, Double>>[] adjacencyList;

    public Graph(String filename) {
        vertices = new ArrayList<Pair<Double, Double>>();
        adjacencyList = null;
        loadGraphFromFile(filename);
    }

    @SuppressWarnings("unchecked") // for a LinkedList array.
    private void loadGraphFromFile(String filename) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filename));
            sc.useLocale(Locale.FRENCH);

            if (!sc.next().equals("vertices")) {
                throw new Exception("Invalid file (vertices).");
            }

            while (sc.hasNextInt()) {
                sc.nextInt();
                sc.next();
                double lat = sc.nextDouble();
                double lon = sc.nextDouble();
                vertices.add(new Pair<Double, Double>(lat, lon));
            }
            if (!sc.next().equals("arestas")) {
                throw new Exception("Invalid file (arestas).");
            }

            adjacencyList = new LinkedList[vertices.size()];
            for (int i = 0; i < vertices.size(); ++i) {
                adjacencyList[i] = new LinkedList<Pair<Integer, Double>>();
            }

            while (sc.hasNextInt()) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                double weight = sc.nextDouble();
                Pair<Double, Double> v0 = vertices.get(to);
                Pair<Double, Double> v1 = vertices.get(from);
                double xd = v1.first - v0.first;
                double yd = v1.second - v0.second;
                double d = weight * Math.sqrt(xd * xd + yd * yd);

                adjacencyList[from].add(new Pair<Integer, Double>(to, d));
            }

            if (sc.hasNext()) {
                throw new Exception("Data left.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    public Pair<double[], int[]> computeDijkstra(int origin) {
        int count = vertices.size();
        Pair<double[], int[]> result;
        double[] cost = new double[count];
        int[] previous = new int[count];
        result = new Pair<double[], int[]>(cost, previous);

        boolean[] visited = new boolean[count];

        for (int i = 0; i < count; i++) {
            result.first[i] = Double.POSITIVE_INFINITY;
            result.second[i] = -1;
            visited[i] = false;
        }
        result.first[origin] = 0;

        while (true) {
            int n = getNextNode(cost, visited);
            if (n < 0) {
                break;
            }
            LinkedList<Pair<Integer, Double>> line = adjacencyList[n];
            for (Pair<Integer, Double> v : line) {
                double d = cost[n] + v.second;
                if (d < cost[v.first]) {
                    cost[v.first] = d;
                    previous[v.first] = n;
                }
            }
            visited[n] = true;
        }

        return result;
    }

    /**
     * Selects the vertex which has not yet been visited and has the lest cost
     * to be reached so far. If using a priority queue for the cost, the
     * algorithm runs faster (O(E lg V) vs. O(v^2)).
     *
     * @param r The current cost for each vertex.
     * @param v The visited vertices.
     * @return
     */
    private int getNextNode(double[] cost, boolean[] visited) {
        double min = Double.POSITIVE_INFINITY;
        int n = -1;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                if (cost[i] < min) {
                    min = cost[i];
                    n = i;
                }
            }
        }
        return n;
    }

    public static void main(String[] args) {
        long st, et;
        st = System.currentTimeMillis();
        System.out.print("Loading Graph...");
        Graph g = new Graph("data");
        System.out.println(" done.");
        et = System.currentTimeMillis();
        System.out.println("Time: " + (et - st) / 1000.0 + " seconds");

        Scanner kbd = new Scanner(System.in);

        System.out.print("Digite o nodo origem (-1 para sair):");
        int origin = kbd.nextInt();
        kbd.nextLine(); // nextInt bug.

        while (origin >= 0) {
            System.out.print("Computing path... ");
            st = System.currentTimeMillis();
            Pair<double[], int[]> shortestPath = g.computeDijkstra(origin);
            et = System.currentTimeMillis();
            System.out.println("done.");
            System.out.println("Time: " + (et - st) / 1000.0 + "seconds");

            double[] cost = shortestPath.first;
            int[] previous = shortestPath.second;

            System.out.print("Digite o nodo destino (-1 para sair):");
            int destiny = kbd.nextInt();
            kbd.nextLine(); // nextInt bug.

            while (destiny >= 0) {
                if (previous[destiny] < 0) {
                    System.out.println("No path to node!");
                } else {
                    showRoute(destiny, cost, previous);
                }
                // next destiny
                System.out.print("Digite o nodo destino (-1 para sair):");
                destiny = kbd.nextInt();
                kbd.nextLine(); // nextInt bug.
            }
            // next origin
            System.out.print("Digite o nodo origem (-1 para sair):");
            origin = kbd.nextInt();
            kbd.nextLine(); // nextInt bug.
        }
        kbd.close();
    }

    private static void showRoute(int destiny, double[] cost, int[] previous) {
        int p = destiny;
        int n = previous[p];

        Deque<Integer> stack = new LinkedList<Integer>();
        while (n != -1) {
            stack.push(p);
            p = n;
            n = previous[p];
        }
        stack.push(p);

        int count = 1;
        int last = stack.pop();
        double total = cost[last];
        System.out.print(last);
        while (!stack.isEmpty()) {
            last = stack.pop();
            total = cost[last];
            System.out.print(" -> " + last);
            if ((++count) % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println(String.format("Total cost (m): %8.2f", total));
    }
}
