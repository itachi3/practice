import java.util.*;
import java.util.LinkedList;

/**
 * Created by G on 07/05/17.
 */
public class Array {

    private static int[] stack = new int[10];

    private static int top1, top2;

    static {
        top1 = -1;
        top2 = stack.length;
    }

    static Integer[] quickSort(Integer[] a, int l, int h) {
        if (l < h) {
            int p = partition(a, l, h);
            a = quickSort(a, l, p - 1);
            a = quickSort(a, p + 1, h);
        }
        return a;
    }

    static int partition(Integer[] a, int l, int h) {
        int pivot = a[h];
        int i = l, j = h - 1;
        while (i <= j) {
            if (a[i] > pivot) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                j--;
            } else {
                i++;
            }
        }
        a[h] = a[i];
        a[i] = pivot;
        return i;
    }

    static int binarySearch(Integer[] arr, int key, int l, int h) {
        if (l <= h) {
            int mid = (l + h) / 2;
            if (arr[mid] < key) {
                return binarySearch(arr, key, mid + 1, h);
            } else if (arr[mid] > key) {
                return binarySearch(arr, key, l, mid - 1);
            } else {
                return mid;
            }
        }
        return -1;
    }

    static int first(Integer[] arr, int key, int l, int h) {
        if (l <= h) {
            int mid = (l + h) / 2;
            if (arr[mid] > key) {
                return first(arr, key, l, mid - 1);
            } else if (arr[mid] < key) {
                return first(arr, key, mid + 1, h);
            } else {
                if (mid - 1 >= l && arr[mid - 1] == key) {
                    return first(arr, key, l, mid - 1);
                }
                return mid;
            }
        }
        return -1;
    }

    static int last(Integer[] arr, int key, int l, int h) {
        if (l <= h) {
            int mid = (l + h) / 2;
            if (arr[mid] > key) {
                return last(arr, key, l, mid - 1);
            } else if (arr[mid] < key) {
                return last(arr, key, mid + 1, h);
            } else {
                if (mid + 1 <= h && arr[mid + 1] == key) {
                    return last(arr, key, mid + 1, h);
                }
                return mid;
            }
        }
        return -1;
    }

    static int countOccurrence(Integer[] arr, int key) {
        int firstOccurance = first(arr, key, 0, arr.length - 1);
        int lastOccurance = -1;
        if (firstOccurance > -1) {
            lastOccurance = last(arr, key, 0, arr.length - 1);
            return lastOccurance - firstOccurance + 1;
        }
        return 0;
    }

    static boolean cutPossible(Integer[] arr, int curr_min, int M) {
        int students = 1;
        int curr_sum = 0;
        for (int pages : arr) {
            if (pages > curr_min) {
                return false;
            }
            if (curr_sum + pages > curr_min) {
                students++;
                curr_sum = pages;
                if (students > M) {
                    return false;
                }
            } else {
                curr_sum += pages;
            }
        }
        return true;
    }

    static int minimumSumWithMCuts(Integer[] arr, int M) {
        int N = arr.length;
        if (N < M) {
            return -1;
        }
        int start = 0, end = 0, mid, result = Integer.MAX_VALUE;
        for (int pages : arr) {
            end += pages;
        }
        mid = (start + end) / 2;
        while (start <= end) {
            if (cutPossible(arr, mid, M)) {
                end = mid - 1;
                result = (mid <= result) ? mid : result;
            } else {
                start = mid + 1;
            }
            mid = (start + end) / 2;
        }
        return result;
    }

    static boolean duplicateWithinK(Integer[] arr, int k) {
        Map<Integer, Integer> kMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (kMap.containsKey(arr[i])) {
                System.out.println("Duplicate element : " + arr[i] + " Index : " + i);
                return true;
            }
            if (i + 1 >= k) {
                kMap.remove(arr[i + 1 - k]);
            }
            kMap.put(arr[i], i);
        }
        return false;
    }

    static boolean isArithmeticProgression(Integer[] arr) {
        if (arr.length < 2) {
            return false;
        }
        int first = arr[0], diff = Math.abs(arr[0] - arr[1]);
        for (int i = 2; i < arr.length; i++) {
            if (Math.abs(first - arr[i]) % diff != 0) {
                return false;
            }
        }
        return true;
    }

    //Hint average of whole array is same as sub array average
    static void subArraysOfSameAverage(Integer[] arr) {
        int totalAverage = 0;
        int subArrayAverage = 0, count = 0, start = 0;
        for (int element : arr) {
            totalAverage += element;
        }
        totalAverage /= arr.length;
        System.out.print("Sub arrays of same average (" + totalAverage + ") are : ");
        for (int i = 0; i < arr.length; i++) {
            subArrayAverage += arr[i];
            count++;
            if (subArrayAverage / count == totalAverage) {
                System.out.print("(" + start + "," + i + "),");
                subArrayAverage = 0;
                count = 0;
                start = i + 1;
            }
        }
        System.out.println();
    }

    static int[] positionToSwapForSorting(Integer[] arr) {
        int[] pos = new int[]{0, arr.length - 1};
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                pos[0] = i - 1;
            } else if (arr[i] > arr[i - 1]) {
                pos[1] = i - 1;
            }
        }
        return pos;
    }

    static int binarySearchItr(Integer[] arr, int key) {
        int l = 0, h = arr.length;
        while (l < h) {
            int mid = (l + h) / 2;
            if (arr[mid] < key) {
                l = mid + 1;
            } else if (arr[mid] > key) {
                h = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    static int maxZerosBetweenOnes(int n) {
        int prev = 0, curr = 0;
        while (n > 0) {
            if (n % 2 == 0) {
                curr += 1;
            } else {
                if (curr > prev) {
                    prev = curr;
                }
                curr = 0;
            }
            n /= 2;
        }
        return prev;
    }

    static int subArraySizeLessThenK(Integer[] arr, int k) {
        List<Integer> arraySizes = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= k) {
                return -1;
            }
            arraySizes.add(1);
        }
        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                sum += arr[j];
                if (sum < k) {
                    arraySizes.set(i, arraySizes.get(i) + 1);
                }
            }
        }
        int[] maxElements = new int[arraySizes.size()];
        for (int i = 0; i < arraySizes.size(); i++) {
            maxElements[arraySizes.get(i)] += 1;
        }
        int max = 0;
        int subArraySize = 0;
        for (int i = 0; i < maxElements.length; i++) {
            if (maxElements[i] > max) {
                max = maxElements[i];
                subArraySize = i;
            }
        }
        return subArraySize;
    }

    static void push(int key, boolean stack1) {
        if (top2 - top1 - 1 < 0) {
            System.out.println("Stack is full!!");
            return;
        }
        if (stack1) {
            top1++;
            stack[top1] = key;
        } else {
            top2--;
            stack[top2] = key;
        }
    }

    static int pop(boolean stack1) {
        int val = -1;
        if (stack1) {
            if (top1 < 0) {
                System.out.println("Stack 1 is empty!!");
            } else {
                val = stack[top1];
                top1--;
            }
        } else {
            if (top2 > stack.length - 1) {
                System.out.println("Stack 2 is empty!!");
            } else {
                val = stack[top2];
                top2++;
            }
        }
        return val;
    }

    static int maxSum(int[] arr1, int[] arr2) {
        int sum1 = 0, sum2 = 0, j = 0, sum = 0;

        for (int i = 0; i < arr1.length; i++) {
            sum1 += arr1[i];
            while (j < arr2.length && arr2[j] <= arr1[i]) {
                sum2 += arr2[j];
                if (arr2[j] == arr1[i]) {
                    sum += sum1 > sum2 ? sum1 : sum2;
                    sum1 = sum2 = 0;
                }
                j++;
            }
        }

        while (j < arr2.length) {
            sum2 += arr2[j];
            j++;
        }
        sum += sum1 > sum2 ? sum1 : sum2;
        return sum;
    }


    /*
        Given a flight between cities and set of queries
        Find the minimum number of ops to reach the destination
     */
    void constructGraph(List<String> input) {
        int i = 0;
        int routes = Integer.parseInt(input.get(i));
        i = i + 1;
        Map<String, List<String>> routeMap = new HashMap<>();
        for (int j = 0; j < routes; j++) {
            String[] cities = input.get(i).split(" ");
            List<String> connectedCities = new ArrayList<>();
            if (routeMap.containsKey(cities[0])) {
                connectedCities = routeMap.get(cities[0]);
            }
            connectedCities.add(cities[1]);
            routeMap.put(cities[0], connectedCities);
            i++;
        }
        queries(i, input, routeMap);
    }

    void queries(int i, List<String> input, Map<String, List<String>> routeMap) {
        int queries = Integer.parseInt(input.get(i));
        i = i + 1;
        for (int j = 0; j < queries; j++) {
            String[] cities = input.get(i).split(" ");
            System.out.println(bfs(cities[0], cities[1], routeMap));
            i++;
        }
    }

    int bfs(String key, String value, Map<String, List<String>> routeMap) {
        if (!routeMap.containsKey(key)) {
            System.out.println(-1);
        }

        Queue<String> queue = new LinkedList<String>() {{
            addAll(routeMap.get(key));
        }};

        Set<String> visited = new HashSet<>();
        int hop = 1;
        while (!queue.isEmpty()) {
            String currentCity = queue.remove();
            if (!visited.contains(currentCity)) {
                if (queue.contains(value)) {
                    return hop;
                }
                if (routeMap.containsKey(currentCity)) {
                    queue.addAll(routeMap.get(currentCity));
                    hop++;
                }
                visited.add(currentCity);
            }
        }

        return -1;
    }

    static Set<Integer> getSchedule(Object[] keys, Set<Integer> visited, Map<String, List<Integer>> map, int i) {
        if (i >= map.size()) {
            return visited;
        }

        String key = keys[i].toString();
        for (Integer elem : map.get(key)) {
            if (!visited.contains(elem)) {
                visited.add(elem);
                visited.addAll(getSchedule(keys, visited, map, i + 1));
                if (visited.size() == map.size()) {
                    return visited;
                } else {
                    visited.clear();
                }
            }
        }
        return visited;
    }

    static void schedule(Map<String, List<Integer>> map) {
        Object[] keys = map.keySet().toArray();
        Set<Integer> visited = new HashSet<>();
        visited = getSchedule(keys, visited, map, 0);
        System.out.println("Final schedule :" + visited);
    }

    static int getDeflectionPoint(Integer[] arr) {
        Integer []minFromLeft = Arrays.copyOf(arr, arr.length);
        Integer []minFromRight = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < arr.length; i++) {
            if(i-1>0) {
                minFromLeft[i] = (arr[i] < minFromLeft[i-1]) ? arr[i] : minFromLeft[i-1];
            }
            int rightIndex = arr.length - (i + 1);
            if(rightIndex < arr.length - 1) {
                minFromRight[rightIndex] = (arr[rightIndex] < minFromRight[rightIndex + 1]) ? arr[rightIndex] : minFromRight[rightIndex + 1];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            //Extremes are not considered deflection point
            if(i - 1 > 0 && i < arr.length - 1) {
                if(minFromLeft[i-1] < arr[i] && minFromRight[i+1] > arr[i]) {
                    return arr[i];
                }
            }
        }
        return -1;
    }
}
