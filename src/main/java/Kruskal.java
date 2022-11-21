import java.util.*;

class Grafo {
    int size;
    ArrayList<LinkedList<Integer>> list = new ArrayList<>();

    public Grafo(int V) {
        this.size = V;
        for (int i = 0; i < this.size; i++) {
            list.add(new LinkedList<>());

        }
    }

    public void addBorder(int first, int second) {
        list.get(first).add(second);
        list.get(second).add(first);
    }

    public boolean hasCycle() {
        boolean visited[] = new boolean[this.size];
        Arrays.fill(visited, false);
        for (int k = 0; k < this.size; k++)
            if (!visited[k])
                if (isCycle(k, visited, -1))
                    return true;
        return false;
    }

    public void removeBorder(int first, int second) {
        list.get(first).remove((Integer) second);
        list.get(second).remove((Integer) first);
    }

    public boolean isCycle(int v, boolean visited[], int parent) {

        visited[v] = true;
        Integer w;

        Iterator<Integer> it = list.get(v).iterator();
        while (it.hasNext()) {
            w = it.next();

            if (!visited[w]) {
                if (isCycle(w, visited, v))
                    return true;
            }

            else if (w != parent)
                return true;
        }
        return false;
    }



}

public class Kruskal {

    static void SortByColumn(int array[][], int col) {
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(final int[] entry1, final int[] entry2) {
                if (entry1[col] > entry2[col])
                    return 1;
                else
                    return -1;
            }
        });
    }


    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        int x = sc.nextInt();

        int array[][] = new int[x][3];

        for (int i = 0; i < x; i ++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            array[i][0] = u - 1;
            array[i][1] = v - 1;
            array[i][2] = weight;
        }

        SortByColumn(array, 2);
        int totalWeight = 0;
        Grafo graph = new Grafo(y);

        for (int i = 0; i < x; i++) {
            int st = array[i][0];
            int en = array[i][1];
            int weight = array[i][2];
            graph.addBorder(st, en);

            if (graph.hasCycle()) {
                graph.removeBorder(st, en);
            } else {
                totalWeight += weight;
            }
        }

        System.out.println(totalWeight);
    }
}
