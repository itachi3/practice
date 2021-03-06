import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by G on 10/04/17.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Integer arr[] = {13, 3, 14, 1, 4, 18, 2, 12, 10, 4, 4, 5, 11, 8, 7, 9, 6};
        //modulesBST(arr);
        //moduleList(arr);
        //moduleMinHeap(arr);
        //moduleArray(arr);
        //moduleDP(arr);
        //moduleLRUCache(arr);
        //Integer graphMatrix[][] = {{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}};
        //moduleGraph(graphMatrix);
        moduleString();
        //moduleRecursion();
        //moduleGeneral();
    }

    static void moduleGeneral() {
        System.out.println("Quantity after K units : " + General.afterUnits(5, 0, 0, 2, 0));
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

        list.reverseSize(3);
        list.print();

        list.reverse();
        list.print();

        list.reverse(list.getHead());
        list.print();

        list.delete(13);
        list.print();

        System.out.print("\nMiddle : " + list.findMiddle());
        System.out.println("\nPalindrome : " + list.palindrome(list.getHead()));

        list.pairSwap();
        list.print();

        LinkedList l1 = new LinkedList();
        l1.populate(new Integer[]{1, 3, 5, 7, 8, 9, 10});

        LinkedList l2 = new LinkedList();
        l2.populate(new Integer[]{2, 4, 6});

        l1.head = LinkedList.merge(l1.head, l2.head);
        l1.print();

        LinkedList l3 = new LinkedList();
        l3.populate(new Integer[]{1,2,3,4,5,6});
        l3.head = l3.reOrderList(l3.head);
        l3.print();

        /*Circular List
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
        tree.bottomView();
        tree.verticalOrder();
        tree.serialize();
        System.out.print("DFS :");
        Tree.DFS(tree.getRoot());
        System.out.print("\nInOrder : ");
        Tree.inOrder(tree.getRoot());
        System.out.println("\nHeight : " + tree.height(tree.getRoot()));
        System.out.println("Diameter : " + tree.diameter(tree.getRoot(), new Tree.Height(0)));
        System.out.println("Max path sum : " + tree.pathSum(tree.getRoot(), 0));
        System.out.println("Search : " + tree.searchBST(18, tree.getRoot()));
        System.out.print("Left View : ");
        Tree.leftView(tree.getRoot(), 1);
        System.out.print("\nLCA : " + Tree.LCA(5, 11, tree.getRoot()));
        System.out.print("\nDelete : ");
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

        Map<String, List<Integer>> movieMap = new HashMap<>();
        movieMap.put("Shining", Arrays.asList(14, 15, 16));
        movieMap.put("Kill bill", Arrays.asList(14, 15));
        movieMap.put("Pulp fiction", Arrays.asList(14, 15));
        Array.schedule(movieMap);
    }

    static void moduleString() {
        System.out.println("Sum string :" + Strings.sum("23", "45"));
        System.out.println("Lottery possible : " + Strings.isLotteryTicket("1122334", new HashSet<>()));
        System.out.println("Min value of expr : " + Strings.minValueOfTheExpression("1+2*3+4*5"));
        String[] values = {"54", "546", "548", "60"};
        List<String> biggestNumber = Arrays.stream(values).sorted((e1, e2) -> (e2 + e1).compareTo(e1 + e2)).collect(Collectors.toList());
        System.out.println("Biggest number : " + biggestNumber);
        System.out.println("AtoI : " + Strings.atof("-.42"));
        System.out.println("Longest string : " + Strings.longestSubString("abcabdcbb"));
    }

    static void moduleDP(Integer arr[]) {
        System.out.println("LIS : " + DP.longestIncreasingSubsequence(arr));
        System.out.println("Minimum jumps : " + DP.minJumps(arr));
        System.out.println("Min coin needed : " + DP.minCoinChange(arr, 13));
        System.out.println("Sub-array of given sum : " + Arrays.toString(DP.subArrayOfGivenSum(arr, 23)));
        Integer[] tickets = new Integer[]{1, 2, 4, 5, 7, 29, 30};
        System.out.println("Ticket price : " + DP.getTicketPrice(tickets));
        System.out.println("Min insertion palindrome : " + Strings.minInsertion("abcd"));
        System.out.println("LPS : " + Strings.getLongestPalindromicSubSequence("BBABCBCAB"));
        Integer start[] = {1,3,0,5,8,5};
        Integer end[] = {2,4,6,7,9,9};
        System.out.println("Max work : " + DP.maxWorkPossible(start, end));
        Integer partition[] = {1,6,5,11};
        int totalSum = Arrays.stream(partition).mapToInt(Integer::intValue).sum();
        System.out.println("Min partition sum : " + DP.minPartitionDifference(partition, 0, totalSum,0));
    }

    static void moduleRecursion() {
        char[] val = "ABC".toCharArray();
        System.out.println("Permutation");
        Strings.permute(val, 0, val.length);
        System.out.println("Permutation LowerCase");
        Strings.permuteLowerCase(val, 0, val.length);
        Array.getMaxAs(0, 0, 3, 0);
        System.out.println("Max char count : " + Array.maxLength);
        int arr[] = new int[]{10, 1, 2, 7, 6, 1, 5};
        Array.diffPaths(arr, 8, 0, 0, new ArrayList<>());
    }

    static void moduleGraph(Integer[][] graphMatrix) {
        Graph.insert(graphMatrix);
        Graph.print();
        Graph.dfs();
        Graph.spiralTravel(graphMatrix);
    }
}
