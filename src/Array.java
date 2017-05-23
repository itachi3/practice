import java.util.ArrayList;
import java.util.List;

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

    static int minValueOfTheExpression(String expr) {
        List<Integer> num = new ArrayList<Integer>();
        List<String> opr = new ArrayList<String>();
        int i = 0;
        for (i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '+' || expr.charAt(i) == '*') {
                opr.add("" + expr.charAt(i));
            } else {
                num.add(Integer.parseInt("" + expr.charAt(i)));
            }
        }
        int L = num.size();
        int[][] minVal = new int[L][L];
        for (i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                minVal[i][j] = Integer.MAX_VALUE;
                if (i == j) {
                    minVal[i][j] = num.get(i);
                }
            }
        }

        int minTemp = 0;
        for (int k = 2; k <= L; k++) {
            for (i = 0; i < L - k + 1; i++) {
                int j = i + k - 1;
                for (int s = i; s < j; s++) {
                    if (opr.get(s).equals("+")) {
                        minTemp = minVal[i][s] + minVal[s + 1][j];
                    } else {
                        minTemp = minVal[i][s] * minVal[s + 1][j];
                    }
                    if (minTemp < minVal[i][j]) {
                        minVal[i][j] = minTemp;
                    }
                }
            }
        }
        return minVal[0][L - 1];
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

    static int maxWordsInSentences(String s) {
        String[] words = s.split(" ");
        int maxSoFar = 0, maxEndingHere = 0;
        for (String word : words) {
            maxEndingHere++;
            if (word.contains("?")) {
                if (maxEndingHere > maxSoFar) {
                    maxSoFar = maxEndingHere;
                }
                maxEndingHere = 0;
            }
        }
        return maxSoFar;
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
}
