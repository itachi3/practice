import java.util.*;

/**
 * Created by G on 10/04/17.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Integer arr[] = {13, 3, 14, 1, 4, 18, 2, 12, 10, 4, 4, 4, 5, 11, 8, 7, 9, 6};
        //modulesBST(arr);
        //moduleList(arr);
        //moduleMinHeap(arr);
        moduleArray(arr);
        //moduleDP(arr);
    }

    static void moduleList(Integer arr[]) {
        LinkedList list = new LinkedList();
        list.populate(arr);
        list.print();
        list.reverse();
        list.print();
        list.reverse(list.getHead());
        list.print();
        list.delete(13);
        list.print();
        System.out.print("\nMiddle : " + list.findMiddle());
        System.out.print("\nPalindrome : " + list.palindrome(list.getHead()));
    }

    static void modulesBST(Integer arr[]) {
        Tree tree = new Tree();
        tree.populateBST(arr);
        tree.BFS();
        System.out.print("\nDFS :");
        tree.DFS(tree.getRoot());
        System.out.print("\nInOrder : ");
        tree.inOrder(tree.getRoot());
        System.out.println("\nHeight : " + tree.height(tree.getRoot()));
        System.out.print("\nSearch : " + tree.searchBST(18, tree.getRoot()));
        System.out.print("\nLeft View : ");
        tree.leftView(tree.getRoot(), 1);
        System.out.print("\n LCA : " + tree.LCA(5, 11, tree.getRoot()));
        System.out.print("\n Delete : ");
        tree.deleteBST(14);
        System.out.print("\nInOrder : ");
        tree.inOrder(tree.getRoot());
    }

    static void moduleMinHeap(Integer arr[]) {
        Heap tree = new Heap();
        tree.populate(arr);
        tree.printTree();
        tree.print();
    }

    static void moduleArray(Integer arr[]) {
        Array.subArraysOfSameAverage(arr);
        System.out.println("Minimum pages : " + Array.minimumSumWithMCuts(arr, 2));
        System.out.println("Arithmetic profression: " + Array.isArithmeticProgression(arr));
        System.out.println("Subarray size : " + Array.subArraySizeLessThenK(arr, 14));
        System.out.println("Max zeros between ones : " + Array.maxZerosBetweenOnes(1030));
        System.out.println("Min value of expr : " + Array.minValueOfTheExpression("1+2*3+4*5"));
        System.out.println("Searching : " + Array.binarySearchItr(arr, 4));
        System.out.println("Position [start, end] : " + Arrays.toString(Array.positionToSwapForSorting(arr)));
        System.out.println("QuickSort : " + Arrays.toString(Array.quickSort(arr, 0, arr.length - 1)));
        arr = Array.quickSort(arr, 0, arr.length - 1);
        System.out.println("Searching : " + Array.binarySearch(arr, 4, 0, arr.length - 1));
        System.out.println("Occurrence : " + Array.countOccurrence(arr, 4));
    }

    static void moduleDP(Integer arr[]) {
        System.out.println("LIS : " + DP.longestIncreasingSubsequence(arr));
        System.out.println("Minimum jumps : " + DP.minJumps(arr));
        System.out.println("Sub-array of given sum : " + Arrays.toString(DP.subArrayOfGivenSum(arr, 23)));
        Integer[] tickets = new Integer[]{1, 2, 4, 5, 7, 29, 30};
        System.out.println("Ticket price : " + DP.getTicketPrice(tickets));
    }
}
