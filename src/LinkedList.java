import java.util.*;

/**
 * Created by G on 10/04/17.
 */
class LinkedList {
    static class Node {
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

    class SearchNode {
        Node prev;
        Node found;

        SearchNode() {
            prev = found = null;
        }

        public SearchNode(Node prev, Node found) {
            this.prev = prev;
            this.found = found;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SearchNode)) return false;

            SearchNode that = (SearchNode) o;

            return found.data.equals(that.found.data);
        }

        @Override
        public int hashCode() {
            return found.data;
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

    void swap(Integer a, Integer b) {
        Set<SearchNode> foundNodes = new HashSet<>();
        Node prev = null, curr = head;
        while (curr != null) {
            if (curr.data.equals(a) || curr.data.equals(b)) {
                foundNodes.add(new SearchNode(prev, curr));
            }
            prev = curr;
            curr = curr.next;
        }
        Object[] result = foundNodes.toArray();
        if (result.length == 2) {
            SearchNode n1 = (SearchNode) result[0];
            SearchNode n2 = (SearchNode) result[1];

            if (n1.prev == null) {
                head = n2.found;
            }
            if (n2.prev == null) {
                head = n1.found;
            }
            if (n1.prev != null) {
                n1.prev.next = n2.found;
            }
            if (n2.prev != null) {
                n2.prev.next = n1.found;
            }

            if (n2.prev == n1.found) {
                Node n2Next = n2.found.next;
                n2.found.next = n1.found;
                n1.found.next = n2Next;
            } else {
                Node n1Next = n1.found.next;
                n1.found.next = n2.found.next;
                n2.found.next = n1Next;
            }
        }
    }

    void print() {
        Node node = head;
        System.out.print("\nLinked List : ");
        while (node != null) {
            System.out.print(node.data + ", ");
            node = node.next;
        }
    }

    Node getHead() {
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

    void reverseSize(int k) {
        boolean first = true;
        Node prev = null, curr = head, prevHead = null, currHead;
        int itr = 1;
        while (curr != null) {
            currHead = curr;
            while (curr != null && itr <= k) {
                Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                itr++;
            }
            if (first) {
                head = prev;
                first = false;
            }
            if (prevHead != null) {
                prevHead.next = prev;
                currHead.next = null;
            }
            prevHead = currHead;
            itr = 1;
        }
    }

    void pairSwap() {
        Node curr = head, next;
        while (curr != null && curr.next != null) {
            next = curr.next;
            Integer temp = curr.data;
            curr.data = next.data;
            next.data = temp;
            curr = next.next;
        }
    }

    static Node merge(Node head1, Node head2) {
        Node head = new Node(-1), curr = head;
        while (head1 != null && head2 != null) {
            Node nextHead2 = head2.next;
            Node nextHead1 = head1.next;
            Node listNext = curr.next;
            if (head1.data < head2.data) {
                curr.next = head1;
                curr = head1;
                head1 = nextHead1;
            } else {
                curr.next = head2;
                curr = head2;
                head2 = nextHead2;
            }
            curr.next = listNext;
        }
        if (head1 != null) {
            curr.next = head1;
        } else if (head2 != null) {
            curr.next = head2;
        }
        return head.next;
    }

    private static boolean stopExchange = false;

    Node reOrderList(Node curr) {
        if (curr == null) {
            return null;
        }

        Node last = reOrderList(curr.next);
        if(head == last || head.next == last) {
            stopExchange = true;
        }
        if (last != null && !stopExchange) {
            Node temp = head.next;
            if(head.next != null) {
                head.next = last;
            }
            last.next = temp;
            head = temp;
            curr.next = null;
        }
        return curr;
    }
}
