import java.util.HashMap;
import java.util.Map;

/**
 * Created by G on 25/05/17.
 */
class LRU {

    private static final int MAX_SIZE = 5;

    static class Node {
        Integer data;
        Node left;
        Node right;

        public Node(Integer data) {
            this.data = data;
            this.left = this.right = null;
        }

        @Override
        public String toString() {
            return "Node(" + data + ')';
        }
    }

    static Map<Integer, Node> locationMap = new HashMap<>();

    static Node head = null;

    static Node tail = null;

    private static void deque() {
        //Tail
        if(tail == head) {
          tail = null;
        }
        //Map
        locationMap.remove(head.data);
        //Head
        Node node = head;
        head = head.right;
        head.left = null;
        node.right = null;
    }

    private static void enque(Node node) {
        //Tail
        if(tail == null) {
            tail = node;
        } else {
            node.left = tail;
            tail.right = node;
            tail = node;
        }
        //Head
        if(head == null) {
            head = node;
        }
        //Map
        locationMap.put(node.data, node);
    }

    private static Node delete(Node location) {
        Node prev = location.left, next = location.right;
        if(prev != null) {
            prev.right = next;
        }
        if(next != null) {
            next.left = prev;
        }
        location.left = location.right = null;
        //Head
        if(head == location) {
            head = next;
        }
        //Tail
        if(tail == location) {
            tail = prev;
        }
        //Map
        locationMap.remove(location.data);
        return location;
    }

    static Node visit(Integer data) {
        Node nodeLocation = null, newNode;
        if(locationMap.containsKey(data)) {
            nodeLocation = locationMap.get(data);
        }
        if(nodeLocation == null) {
            if(locationMap.size() >= MAX_SIZE) {
                deque();
            }
            newNode = new Node(data);
        } else {
            newNode = delete(nodeLocation);
        }
        enque(newNode);
        return newNode;
    }

    static void print() {
        Node itr = head;
        while (itr != null) {
            System.out.print(itr.data + ",");
            itr = itr.right;
        }
        System.out.println();
    }
}
