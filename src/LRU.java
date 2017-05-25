import java.util.HashMap;
import java.util.Map;

/**
 * Created by G on 25/05/17.
 */
class LRU {

    private static final int MAX_SIZE = 5;

    static class Node {
        Integer page;
        Node next;

        Node(Integer data) {
            page = data;
        }

        @Override
        public String toString() {
            return "Node{" + page + '}';
        }
    }

    private static Node headPtr = null;

    private static Node tailPtr = null;

    private static int listLength = 0;

    private static Map<Integer, Node> positionMap = new HashMap<>();

    static Node visit(Integer pageData) {
        Node position = null;
        if (positionMap.containsKey(pageData)) {
            position = positionMap.get(pageData);
        }
        Node newNode;
        if (position == null) {
            if (listLength >= MAX_SIZE) {
                deque();
            }
            newNode = new Node(pageData);
        } else {
            newNode = delete(position);
        }
        enque(newNode);
        positionMap.put(pageData, newNode);
        return tailPtr;
    }

    private static void deque() {
        positionMap.remove(headPtr.page);
        headPtr = headPtr.next;
        listLength--;
    }

    private static Node delete(Node position) {
        Node itr = headPtr, prev = null;
        while (!itr.equals(position)) {
            prev = itr;
            itr = itr.next;
        }
        if (itr == headPtr) {
            headPtr = headPtr.next;
        } else {
            prev.next = itr.next;
        }
        if (itr == tailPtr) {
            tailPtr = prev;
        }
        itr.next = null;
        listLength--;
        return itr;
    }

    private static void enque(Node node) {
        if (tailPtr == null) {
            tailPtr = node;
        } else {
            tailPtr.next = node;
            tailPtr = tailPtr.next;
        }
        if (headPtr == null) {
            headPtr = tailPtr;
        }
        listLength++;
    }

    static void print() {
        Node itr = headPtr;
        while (itr != null) {
            System.out.print(itr.page + ",");
            itr = itr.next;
        }
        System.out.println();
    }
}
