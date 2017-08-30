/**
 * Created by G on 07/05/17.
 */
class DP {
    static int longestIncreasingSubsequence(Integer arr[]) {
        int max = 0;
        int LIS[] = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && LIS[i] < LIS[j] + 1) {
                    LIS[i] = LIS[j] + 1;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (LIS[i] > max) {
                max = LIS[i];
            }
        }
        return max;
    }

    static int minJumps(Integer[] arr) {
        Integer[] jumps = new Integer[arr.length];
        jumps[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            jumps[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (arr[j] >= i - j) {
                    jumps[i] = Math.min(jumps[j] + 1, jumps[i]);
                }
            }
        }
        return jumps[arr.length - 1];
    }

    static int[] subArrayOfGivenSum(Integer[] arr, int k) {
        int sum = 0, start = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            while (sum > k) {
                sum -= arr[start];
                start++;
            }
            if (sum == k) {
                return new int[]{start, i};
            }
        }
        return new int[]{-1, -1};
    }

    static double getTicketPrice(Integer[] arr) {
        if (arr.length > 25) {
            return 25;
        } else if (arr.length < 3) {
            return 2 * arr.length;
        }

        double[] daysCanBeServed = new double[arr.length];
        double[] minTicketPrice = new double[arr.length];

        minTicketPrice[0] = 2;
        minTicketPrice[1] = 4;
        minTicketPrice[2] = 6;

        double continuousDays, oneDayCost, sevenDayCost, totalDays;
        for (int i = 3; i < arr.length; i++) {
            totalDays = i + 1;
            continuousDays = arr[i] - arr[0];
            oneDayCost = totalDays * 2;
            sevenDayCost = Math.ceil(continuousDays / 7) * 7;
            if (oneDayCost < sevenDayCost) {
                minTicketPrice[i] = oneDayCost;
            } else {
                minTicketPrice[i] = sevenDayCost;
                daysCanBeServed[i] = sevenDayCost - continuousDays;
            }
        }

        for (int i = 3; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                totalDays = i - j;
                continuousDays = arr[i] - arr[j] + 1;
                if (daysCanBeServed[j] >= continuousDays) {
                    if (minTicketPrice[j] < minTicketPrice[i]) {
                        minTicketPrice[i] = minTicketPrice[j];
                        daysCanBeServed[i] = daysCanBeServed[j] - continuousDays;
                    }
                } else {
                    oneDayCost = totalDays * 2;
                    sevenDayCost = Math.ceil(continuousDays / 7) * 7;
                    double minVal = Math.min(oneDayCost, sevenDayCost);
                    if (minTicketPrice[j] + minVal < minTicketPrice[i]) {
                        minTicketPrice[i] = minTicketPrice[j] + minVal;
                        daysCanBeServed[i] = (oneDayCost < sevenDayCost) ? 0 : sevenDayCost - continuousDays;
                    }
                }
            }
        }
        return minTicketPrice[arr.length - 1];
    }

    static int minCoinChange(Integer[] coins, int sum) {
        int[] minChange = new int[sum + 1];
        for (int i = 0; i < sum; i++) {
            minChange[i] = Integer.MAX_VALUE;
        }
        minChange[0] = 0;
        int min, afterChange;
        for (int i = 1; i <= sum; i++) {
            min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                afterChange = i - coins[j];
                if (afterChange >= 0 && min > minChange[afterChange]) {
                    min = minChange[afterChange] + 1;
                }
            }
            minChange[i] = min;
        }
        return minChange[sum];
    }

    static int minPartitionDifference(Integer[] partition, int currIndex, int totalSum , int currSum) {
        if(currIndex == partition.length) {
            return Math.abs(totalSum - 2 * currSum);
        }
        int inc = minPartitionDifference(partition, currIndex + 1, totalSum, currSum + partition[currIndex]);
        int exc = minPartitionDifference(partition, currIndex + 1, totalSum, currSum);
        return Math.min(inc, exc);
    }

    /*
        Activity selection problem greedy preferred - here we have DP
     */
    static class CustomPair {
        int start;
        int end;
        int tasks;

        CustomPair(int i, int j, int length) {
            this.start = i;
            this.end = j;
            this.tasks = length;
        }
    }

    static int maxWorkPossible(Integer[] start, Integer[] end) {
        CustomPair table[][] = new CustomPair[start.length][2];

        for (int i = 0; i < start.length; i++) {
            table[i][0] = new CustomPair(0,0,0);
            table[i][1] = new CustomPair(0,0,0);
        }

        table[0][1].start = start[0];
        table[0][1].end = end[0];
        table[0][1].tasks = 1;
        for (int i = 1; i < start.length; i++) {
            //N excluded
            table[i][0] = (table[i-1][0].tasks > table[i-1][1].tasks) ? table[i-1][0] : table[i-1][1];

            //N included
            int includedTasks = 0, excludedTasks = 0;
            if(start[i] >= table[i-1][1].end || end[i] <= table[i-1][1].start) {
                includedTasks = table[i-1][1].tasks + 1;
            } else {
                excludedTasks = table[i-1][0].tasks + 1;
            }
            if(includedTasks > excludedTasks) {
                table[i][1].tasks = includedTasks;
                table[i][1].start = (start[i] < table[i-1][1].start) ? start[i] : table[i-1][1].start;
                table[i][1].end = (end[i] > table[i-1][1].end) ? end[i] : table[i-1][1].end;
            } else {
                table[i][1].tasks = excludedTasks;
                table[i][1].start = (start[i] < table[i-1][0].start) ? start[i] : table[i-1][0].start;
                table[i][1].end = (end[i] > table[i-1][0].end) ? end[i] : table[i-1][0].end;
            }
        }

        return Math.max(table[start.length -1][0].tasks, table[start.length - 1][1].tasks);
    }
}
