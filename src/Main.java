import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by G on 10/04/17.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Integer arr[] = {13, 3, 14, 1, 4, 18, 2, 12, 10, 4, 4, 4, 5, 11, 8, 7, 9, 6};
        //modulesBST(arr);
        //moduleList(arr);
        //moduleMinHeap(arr);
        //moduleArray(arr);
        //moduleDP(arr);
        //moduleLRUCache(arr);
        //Integer graphMatrix[][] = {{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}};
        //moduleGraph(graphMatrix);
        moduleString();
    }

    static void moduleLRUCache(Integer arr[]) {
        for (Integer page : arr) {
            System.out.println(page);
            System.out.println(LRU.visit(page));
            LRU.print();
        }
    }

    static void moduleList(Integer arr[]) {
        LinkedList list = new LinkedList();
        list.populate(arr);
        list.print();
        list.swap(4, 5);
        list.print();

        /*list.reverse();
        list.print();
        list.reverse(list.getHead());
        list.print();
        list.delete(13);
        list.print();
        System.out.print("\nMiddle : " + list.findMiddle());
        System.out.println("\nPalindrome : " + list.palindrome(list.getHead()));

        LinkedList circular = new LinkedList();
        circular.circularInsert(2);
        circular.circularPrint();
        circular.circularInsert(3);
        circular.circularPrint();
        circular.circularDelete(3);
        circular.circularPrint();
        circular.circularDelete(2);
        circular.circularPrint();*/
    }

    static void modulesBST(Integer arr[]) {
        Tree tree = new Tree();
        tree.populateBST(arr);
        tree.BFS();
        tree.verticalOrder();
        System.out.print("\nDFS :");
        Tree.DFS(tree.getRoot());
        System.out.print("\nInOrder : ");
        Tree.inOrder(tree.getRoot());
        System.out.println("\nHeight : " + tree.height(tree.getRoot()));
        System.out.print("\nSearch : " + tree.searchBST(18, tree.getRoot()));
        System.out.print("\nLeft View : ");
        Tree.leftView(tree.getRoot(), 1);
        System.out.print("\n LCA : " + Tree.LCA(5, 11, tree.getRoot()));
        System.out.print("\n Delete : ");
        tree.deleteBST(14);
        System.out.print("\nInOrder : ");
        Tree.inOrder(tree.getRoot());
        Tree.isBalanced(tree.getRoot(), 0);
        System.out.print("\nBalanced : " + Tree.balanced);

        Integer ar[] = {1, 2, 3, 4, 5, 6, 7, 8};
        Tree tree2 = new Tree();
        Tree.Node root = tree2.populateBalanced(ar, 0, ar.length - 1);
        System.out.print("\nDFS :");
        Tree.DFS(root);
    }

    static void moduleMinHeap(Integer arr[]) {
        Heap tree = new Heap();
        tree.populate(arr);
        tree.printTree();
        tree.print();
    }

    static void moduleArray(Integer arr[]) {
        System.out.println("Deflection point : " + Array.getDeflectionPoint(new Integer[]{4, 3, 2, 7, 8, 9}));
        Array.subArraysOfSameAverage(arr);
        Array.duplicateWithinK(arr, 3);
        System.out.println("Minimum pages : " + Array.minimumSumWithMCuts(arr, 2));
        System.out.println("Arithmetic profression: " + Array.isArithmeticProgression(arr));
        System.out.println("Subarray size : " + Array.subArraySizeLessThenK(arr, 14));
        System.out.println("Max zeros between ones : " + Array.maxZerosBetweenOnes(1030));
        System.out.println("Searching : " + Array.binarySearchItr(arr, 4));
        System.out.println("Position [start, end] : " + Arrays.toString(Array.positionToSwapForSorting(arr)));
        System.out.println("QuickSort : " + Arrays.toString(Array.quickSort(arr, 0, arr.length - 1)));
        arr = Array.quickSort(arr, 0, arr.length - 1);
        System.out.println("Searching : " + Array.binarySearch(arr, 4, 0, arr.length - 1));
        System.out.println("Occurrence : " + Array.countOccurrence(arr, 4));
        int[] ar1 = {2, 3, 7, 10, 12};
        int[] ar2 = {1, 5, 7, 8};
        System.out.println("Max path sum : " + Array.maxSum(ar1, ar2));
        System.out.println("Min coin needed : " + Array.minCoinChange(ar1, 13));

        Map<String, List<Integer>> movieMap = new HashMap<>();
        movieMap.put("Shining", Arrays.asList(14, 15, 16));
        movieMap.put("Kill bill", Arrays.asList(14, 15));
        movieMap.put("Pulp fiction", Arrays.asList(14, 15));
        Array.schedule(movieMap);
    }

    static void moduleString() {
        char[] val = "ABC".toCharArray();
        System.out.println("Permutation");
        Strings.permute(val, 0, val.length);
        System.out.println("Permutation LowerCase");
        Strings.permuteLowerCase(val, 0, val.length);
        System.out.println("Lottery possible : " + Strings.isLotteryTicket("1122334", new HashSet<>()));
        System.out.println("Min value of expr : " + Strings.minValueOfTheExpression("1+2*3+4*5"));
        Strings.Solution("photo.jpg, Warsaw, 2013-09-05 14:08:15\n" +
                "john.png, London, 2015-06-20 15:13:22\n" +
                "myFriends.png, Warsaw, 2013-09-05 14:07:13\n" +
                "Eiffel.jpg, Paris, 2015-07-23 08:03:02\n" +
                "pisatower.jpg, Paris, 2015-07-22 23:59:59\n" +
                "BOB.jpg, London, 2015-08-05 00:02:03\n" +
                "notredame.png, Paris, 2015-09-01 12:00:00\n" +
                "me.jpg, Warsaw, 2013-09-06 15:40:22\n" +
                "a.png, Warsaw, 2016-02-13 13:33:50\n" +
                "b.jpg, Warsaw, 2016-01-02 15:12:22\n" +
                "c.jpg, Warsaw, 2016-01-02 14:34:30\n" +
                "d.jpg, Warsaw, 2016-01-02 15:15:01\n" +
                "e.png, Warsaw, 2016-01-02 09:49:09\n" +
                "f.png, Warsaw, 2016-01-02 10:55:32\n" +
                "g.jpg, Warsaw, 2016-02-29 22:13:11");
        String[] values = {"54", "546", "548", "60"};
        List<String> biggestNumber = Arrays.stream(values).sorted((e1, e2) -> (e2 + e1).compareTo(e1 + e2)).collect(Collectors.toList());
        System.out.println("Biggest number : " + biggestNumber);
        System.out.println("Min insertion palindrome : " + Strings.minInsertion("abcd"));
        System.out.println("LPS : " + Strings.getLongestPalindromicSubSequence("BBABCBCAB"));
        System.out.println("AtoI : " + Strings.atoi("-.42"));
    }

    static void moduleDP(Integer arr[]) {
        System.out.println("LIS : " + DP.longestIncreasingSubsequence(arr));
        System.out.println("Minimum jumps : " + DP.minJumps(arr));
        System.out.println("Sub-array of given sum : " + Arrays.toString(DP.subArrayOfGivenSum(arr, 23)));
        Integer[] tickets = new Integer[]{1, 2, 4, 5, 7, 29, 30};
        System.out.println("Ticket price : " + DP.getTicketPrice(tickets));
    }

    static void moduleGraph(Integer[][] graphMatrix) {
        Graph.insert(graphMatrix);
        Graph.print();
        Graph.dfs();
        Graph.spiralTravel(graphMatrix);
    }
}
