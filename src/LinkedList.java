/**
 * Created by G on 10/04/17.
 */
class LinkedList {
    class Node {
        Integer data;
        Node next;

        Node(Integer data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "DataNode{" +
                    "data=" + data +
                    '}';
        }
    }

    Node head;

    Node tail;

    static Node staticHead;

    private Node insert(Integer key, Node start) {
        if (start == null) {
            start = new Node(key);
            return start;
        }

        start.next = insert(key, start.next);
        return start;
    }

    void insert(Integer key) {
        Node start = head;
        head = insert(key, start);
        staticHead = head;
    }

    Node reverse() {
        Node prev = null, curr = head;
        while (curr != null) {
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        head = prev;
        return prev;
    }

    Node reverse(Node start) {
        if (start == null) {
            return null;
        } else if (start.next == null) {
            head = start;
            return start;
        }
        Node node = reverse(start.next);
        node.next = start;
        start.next = null;
        return start;
    }

    void delete(Integer key) {
        Node prev = null, curr = head;
        while (curr != null) {
            if (key.equals(curr.data)) {
                if (prev == null)
                    head = curr.next;
                else
                    prev.next = curr.next;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    void populate(Integer arr[]) {
        for (Integer elem : arr)
            insert(elem);
    }

    Node findMiddle() {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    boolean palindrome(Node right) {
        if (right == null)
            return true;
        boolean chk = palindrome(right.next);
        if (chk && staticHead.data.equals(right.data))
            staticHead = staticHead.next;
        else
            chk = false;
        return chk;
    }

    void print() {
        Node node = head;
        System.out.print("\nLinked List : ");
        while (node != null) {
            System.out.print(node.data + ", ");
            node = node.next;
        }
    }

    public Node getHead() {
        return head;
    }

    void circularInsert(Integer key) {
        if (tail == null) {
            tail = new Node(key);
            tail.next = tail;
            head = tail;
        } else {
            Node newNode = new Node(key);
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }

    void circularPrint() {
        Node itr = head;
        if (itr == null) {
            System.out.println("None");
            return;
        }
        while (itr.next != head) {
            System.out.print(itr.data + ", ");
            itr = itr.next;
        }
        System.out.print(itr.data);
        System.out.println();
    }

    Node circularDelete(Integer key) {
        Node prev = null, curr = head;
        if (curr == null) {
            return null;
        }
        while (curr.next != head) {
            if (curr.data.equals(key)) {
                if (prev == null) {
                    head = curr.next;
                    tail.next = head;
                } else {
                    prev.next = curr.next;
                }
                curr.next = null;
                return curr;
            }
            prev = curr;
            curr = curr.next;
        }
        if (curr.data.equals(key)) {
            if (head == tail) {
                head = tail = null;
            } else {
                tail = prev;
                tail.next = head;
            }
            curr.next = null;
            return curr;
        }
        return null;
    }
}
