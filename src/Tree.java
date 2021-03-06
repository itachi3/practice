import java.util.*;
import java.util.LinkedList;

/**
 * Created by G on 10/04/17.
 */
class Tree {
    class Node {
        Integer data;
        Node left;
        Node right;
        Integer level;

        Node(Integer data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "DataNode{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private Node root;

    static int h = 0;

    static boolean balanced = true;

    static int pathSum = 0;

    private Node insertBST(Integer key, Node head) {
        if (null == head) {
            head = new Node(key);
            return head;
        }
        if (key <= head.data) {
            head.left = insertBST(key, head.left);
        } else {
            head.right = insertBST(key, head.right);
        }
        return head;
    }

    private void insertBST(Integer key) {
        Node head = this.root;
        this.root = insertBST(key, head);
    }


    private Node minValueNode(Node start) {
        while (start.left != null) {
            start = start.left;
        }
        return start;
    }

    private Node deleteBST(Integer key, Node start) {
        if (start == null) {
            return null;
        }
        if (key < start.data) {
            start.left = deleteBST(key, start.left);
        } else if (key > start.data) {
            start.right = deleteBST(key, start.right);
        }

        if (start.left == null) {
            return start.right;
        } else if (start.right == null) {
            return start.left;
        }

        Node node = minValueNode(start.right);
        start.data = node.data;
        start.right = deleteBST(node.data, start.right);
        return start;
    }

    void deleteBST(Integer key) {
        Node head = this.root;
        deleteBST(key, head);
    }

    void BFS() {
        final Node head = root;
        Queue<Node> traverseQueue = new LinkedList<Node>() {{
            add(head);
        }};
        System.out.print("BFS : ");
        while (!traverseQueue.isEmpty()) {
            Node node = traverseQueue.remove();
            System.out.print(node.data + ", ");
            if (null != node.left) {
                traverseQueue.add(node.left);
            }
            if (null != node.right) {
                traverseQueue.add(node.right);
            }
        }
    }

    static void DFS(Node node) {
        if (node == null) {
            return;
        }

        DFS(node.left);
        DFS(node.right);
        System.out.print(node.data + ", ");
    }

    static void leftView(Node node, int currHeight) {

        if (node == null) {
            return;
        }
        if (h < currHeight) {
            System.out.print(node.data + ", ");
            h = currHeight;
        }

        leftView(node.left, currHeight + 1);
        leftView(node.right, currHeight + 1);
    }

    int height(Node start) {
        if (start == null) {
            return 0;
        }
        return Math.max(height(start.left), height(start.right)) + 1;
    }

    static void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + ", ");
        inOrder(node.right);
    }

    boolean searchBST(Integer key, Node node) {
        if (node == null) {
            return false;
        }
        if (key < node.data) {
            return searchBST(key, node.left);
        } else if (key > node.data) {
            return searchBST(key, node.right);
        }
        return true;
    }

    static Integer LCA(int n1, int n2, Node node) {
        if (node == null) {
            return -1;
        }
        if (n1 == node.data || n2 == node.data) {
            return node.data;
        }

        int left = LCA(n1, n2, node.left);
        int right = LCA(n1, n2, node.right);

        if (left != -1 && right != -1) {
            return node.data;
        }

        return (left != -1) ? left : right;
    }

    void verticalOrder() {
        Map<Integer, List<Integer>> verticalOrderMap = new TreeMap<>();
        verticalOrderMap = modifiedDFS(verticalOrderMap, root, 0);
        System.out.println("\nVertical order : ");
        for (Map.Entry<Integer, List<Integer>> entry : verticalOrderMap.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

    void bottomView() {
        Queue<Node> traverseQueue = new LinkedList<>();
        root.level = 0;
        traverseQueue.add(root);
        Map<Integer, Integer> sortedMap = new TreeMap<>();
        while (!traverseQueue.isEmpty()) {
            Node curr = traverseQueue.remove();
            sortedMap.put(curr.level, curr.data);
            if (curr.left != null) {
                curr.left.level = curr.level - 1;
                traverseQueue.add(curr.left);
            }
            if (curr.right != null) {
                curr.right.level = curr.level + 1;
                traverseQueue.add(curr.right);
            }
        }
        System.out.print("\nBottom View : ");
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            System.out.print(entry.getValue() + ", ");
        }
    }

    private Map modifiedDFS(Map<Integer, List<Integer>> verticalOrderMap, Node node, int level) {
        if (node == null) {
            return verticalOrderMap;
        }
        List<Integer> valueList = new ArrayList<>();
        if (verticalOrderMap.containsKey(level)) {
            valueList = verticalOrderMap.get(level);
        }
        valueList.add(node.data);
        verticalOrderMap.put(level, valueList);
        verticalOrderMap = modifiedDFS(verticalOrderMap, node.left, level - 1);
        verticalOrderMap = modifiedDFS(verticalOrderMap, node.right, level + 1);
        return verticalOrderMap;
    }

    static int isBalanced(Node root, int h) {
        if (balanced) {
            if (root == null) {
                return h;
            }
            int lh = isBalanced(root.left, h + 1);
            int rh = isBalanced(root.right, h + 1);
            if (Math.abs(lh - rh) > 1) {
                balanced = false;
            }
            return Math.max(lh, rh);
        }
        return -1;
    }

    void populateBST(Integer arr[]) {
        for (Integer element : arr)
            insertBST(element);
    }

    Node populateBalanced(Integer arr[], int l, int h) {
        if (l > h) {
            return null;
        }
        int mid = (l + h) / 2;
        Node root = new Node(arr[mid]);
        root.left = populateBalanced(arr, l, mid - 1);
        root.right = populateBalanced(arr, mid + 1, h);
        return root;
    }

    int pathSum(Node root, int sum) {
        if (root == null) {
            if (pathSum < sum) {
                pathSum = sum;
            }
            return pathSum;
        }
        pathSum(root.left, sum + root.data);
        pathSum(root.right, sum + root.data);
        return pathSum;
    }

    static class Height {
        int h;

        Height(int i) {
            h = i;
        }
    }

    int diameter(Node root, Height height) {
        Height lh = new Height(0), rh = new Height(0);
        if (root == null) {
            height.h = 0;
            return 0;
        }

        int ldia = diameter(root.left, lh);
        int rdia = diameter(root.right, rh);

        height.h = Math.max(lh.h, rh.h) + 1;
        return Math.max(lh.h + rh.h + 1, Math.max(ldia, rdia));
    }

    void serialize() {
        List<Integer> serialized = new ArrayList<>();
        List<Integer> position = new ArrayList<>();
        serialize(root, serialized, position, 0);
        System.out.println("Serialization : ");
        System.out.println(serialized);
        System.out.println(position);
    }

    private void serialize(Node root, List<Integer> val, List<Integer> position, int i) {
        if (root == null) {
            return;
        }
        val.add(root.data);
        position.add(i);
        serialize(root.left, val, position, 2 * i + 1);
        serialize(root.right, val, position, 2 * i + 2);
    }

    Node getRoot() {
        return root;
    }
}
