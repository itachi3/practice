import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by G on 24/04/17.
 */
class Heap {
    private static int SIZE = 10;

    private int currSize = 0;

    private int[] data = new int[SIZE];

    void insert(int key) {
        currSize++;
        if (currSize == data.length) {
            int newItems[] = new int[data.length + data.length / 2];
            System.arraycopy(data, 0, newItems, 0, SIZE);
            SIZE = data.length + data.length / 2;
            data = newItems;
        }
        data[currSize] = key;
        //Heapify
        int parent = currSize / 2;
        int child = currSize;
        int temp;
        while (parent > 0) {
            if (data[parent] <= data[child]) {
                break;
            }

            //Swap value
            temp = data[parent];
            data[parent] = data[child];
            data[child] = temp;
            //Step up to the root
            child = parent;
            parent = child / 2;
        }
    }

    void populate(Integer arr[]) {
        for (Integer element : arr)
            insert(element);
    }

    void print() {
        System.out.println("Heap : " + Arrays.toString(data));
    }

    void printTree() {
        ArrayList<Integer> visited = new ArrayList<>();
        for (int i = 1; i < data.length; i++) {
            if (data[i] > 0 && !visited.contains(i)) {
                System.out.println("Parent : " + data[i] + " Left : " + data[2 * i] + " Right : " + data[2 * i + 1]);
                visited.add(i);
                visited.add(2 * i);
                visited.add(2 * i + 1);
            }
        }
    }

}
