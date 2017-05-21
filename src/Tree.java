import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by G on 10/04/17.
 */
class Tree {
    private class Node {
        Integer data;
        Node left;
        Node right;

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

    void insertBST(Integer key) {
        Node head = this.root;
        this.root = insertBST(key, head);
    }

    Node minValueNode(Node start) {
        while (start.left != null) {
            start = start.left;
        }
        return start;
    }

    Node deleteBST(Integer key, Node start) {
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

    void DFS(Node node) {
        if (node == null) {
            return;
        }

        DFS(node.left);
        DFS(node.right);
        System.out.print(node.data + ", ");
    }

    void leftView(Node node, int currHeight) {

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

    void inOrder(Node node) {
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

    Integer LCA(int n1, int n2, Node node) {
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

    void populateBST(Integer arr[]) {
        for (Integer element : arr)
            insertBST(element);
    }

    Node getRoot() {
        return root;
    }
}
