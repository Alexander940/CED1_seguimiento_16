import java.util.*;

class Kruskal {
    int size;
    ArrayList<LinkedList<Integer>> adjacenList = new ArrayList<>();

    public Kruskal(int V) {
        this.size = V;
        for (int i = 0; i < this.size; i++) {
            adjacenList.add(new LinkedList<>());

        }
    }

    public void addEdge(int first, int second) {
        adjacenList.get(first).add(second);
        adjacenList.get(second).add(first);
    }

    public boolean hasCycle() {
        boolean visited[] = new boolean[this.size];
        Arrays.fill(visited, false);
        for (int k = 0; k < this.size; k++)
            if (!visited[k])
                if (isCycleUtil(k, visited, -1))
                    return true;
        return false;
    }
    
    public void removeEdge(int first, int second) {
        adjacenList.get(first).remove((Integer) second);
        adjacenList.get(second).remove((Integer) first);
    }

    public boolean isCycleUtil(int v, boolean visited[], int parent) {

        visited[v] = true;
        Integer w;

        Iterator<Integer> it = adjacenList.get(v).iterator();
        while (it.hasNext()) {
            w = it.next();

            if (!visited[w]) {
                if (isCycleUtil(w, visited, v))
                    return true;
            }

            else if (w != parent)
                return true;
        }
        return false;
    }



}

public class Solution {

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
        int TotalWeight = 0;
        Kruskal graph = new Kruskal(y);

        for (int i = 0; i < x; i++) {
            int st = array[i][0];
            int en = array[i][1];
            int weight = array[i][2];
            graph.addEdge(st, en);

            if (graph.hasCycle()) {
                graph.removeEdge(st, en);
            }
            else {
                TotalWeight += weight;
            }
        }

        System.out.println(TotalWeight);
    }
}
