import java.util.ArrayList;
import java.util.List;

/**
 * Created by G on 31/05/17.
 */
class Graph {

    static class Node {
        Integer vertex;
        Node next;
        Integer edgeWeight;

        Node(Integer nodeId, Integer weight) {
            vertex = nodeId;
            edgeWeight = weight;
        }
    }

    private static List<Node> adjList = new ArrayList<>();

    static void insert(Integer[][] matrix) {
        int column = matrix[0].length, row = matrix.length;
        for (int i = 0; i < row; i++) {
            Node tail = null, head = null;
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                Node node = new Node(j, matrix[i][j]);
                if (tail == null) {
                    tail = node;
                    head = node;
                } else {
                    tail.next = node;
                }
            }
            if (head != null) {
                adjList.add(head);
            } else {
                adjList.add(new Node(-1, -1));
            }
        }
    }

    static void print() {
        System.out.println("Adjacency List : ");
        for (Node node : adjList) {
            while (node != null) {
                System.out.print(node.vertex + " ");
                node = node.next;
            }
            System.out.println();
        }
    }

    static void dfs() {
        boolean[] visited = new boolean[adjList.size()];
        System.out.print("DFS order : ");
        for (int i = 0; i < adjList.size(); i++) {
            if (!visited[i]) {
                dfsUtil(i, visited);
            }
        }
        System.out.println();
    }

    static void dfsUtil(Integer vertex, boolean[] visited) {
        if (vertex == -1 || visited[vertex]) {
            return;
        }
        visited[vertex] = true;
        Node node = adjList.get(vertex);
        while (node != null) {
            dfsUtil(node.vertex, visited);
            node = node.next;
        }
        System.out.print(vertex + ",");
    }
}
