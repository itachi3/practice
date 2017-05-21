import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by G on 20/04/17.
 */
public class Booking {

    private static int minValue(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int elem : arr) {
            if (elem < min) {
                min = elem;
            }
        }
        return min;
    }

    static void polygon(Scanner in) {
        Integer[] polygons = {0, 0, 0};

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] sides = line.split(" ");
            if (sides.length < 4) {
                System.out.println("Invalid length");
                continue;
            }
            int[] sidesInt = Arrays.stream(sides).mapToInt(Integer::parseInt).toArray();
            if (minValue(sidesInt) < 0) {
                polygons[2]++;
                continue;
            }

            if (sidesInt[0] == sidesInt[1] && sidesInt[2] == sidesInt[3]) {
                polygons[0]++;
            } else if (sidesInt[0] == sidesInt[2] && sidesInt[1] == sidesInt[3]) {
                polygons[1]++;
            } else {
                polygons[2]++;
            }
        }
        System.out.println(Arrays.toString(polygons));
    }

    static void deltaEncoding(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split(" ");
        Integer prev = null;
        List<Integer> deltas = new ArrayList<>();
        for (String token : tokens) {
            Integer current = Integer.parseInt(token);
            if (prev != null) {
                Integer diff = current - prev;
                if (Math.abs(diff) > 127) {
                    deltas.add(-128);
                }
                deltas.add(diff);
            } else {
                deltas.add(current);
            }
            prev = current;
        }
        System.out.println(deltas);
    }

    //count_array equivalent of PHP
    private static String getCountString(String word) {
        int[] countArray = new int[256];
        for (int i = 0; i < word.length(); i++) {
            int ascii = (int) word.charAt(i);
            countArray[ascii]++;
        }
        return Arrays.toString(countArray);
    }

    static void anagramsLexicographic(Scanner in) {
        HashMap<String, List<String>> anagramCollector = new HashMap<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.equals("")) {
                break;
            }
            String key = getCountString(line.replaceAll(" ", ""));
            List<String> newBucket = new ArrayList<String>() {{
                add(line);
            }};
            if (anagramCollector.containsKey(key)) {
                List<String> bucket = anagramCollector.get(key);
                newBucket.addAll(bucket);
            }
            anagramCollector.put(key, newBucket);
        }
        for (Map.Entry<String, List<String>> entry : anagramCollector.entrySet()) {
            List<String> printables = entry.getValue();
            Collections.sort(printables);
            System.out.println(printables);
        }
    }

    static boolean pairExists(Scanner in) {
        int k = in.nextInt();
        in.nextLine();
        int n = in.nextInt();
        in.nextLine();
        HashMap<Integer, Boolean> existenceMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int element = in.nextInt();
            in.nextLine();
            int diff = k - element;
            if (existenceMap.containsKey(diff)) {
                return true;
            }
            existenceMap.put(element, true);
        }
        return false;
    }

    static void hotelWordFrequencySorted(Scanner in) {
        String[] keys = in.nextLine().split(" ");
        HashMap<String, Boolean> keyMap = new HashMap<>();
        for (String key : keys) {
            keyMap.put(key.toLowerCase(), true);
        }
        int n = in.nextInt();
        in.nextLine();
        HashMap<Integer, Integer> frequencyCount = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int hotelId = in.nextInt();
            in.nextLine();
            String[] tokens = in.nextLine().replaceAll("[^a-zA-Z0-9\\s]", "").split(" ");
            for (String token : tokens) {
                if (keyMap.containsKey(token.toLowerCase())) {
                    frequencyCount.put(hotelId, frequencyCount.containsKey(hotelId) ? frequencyCount.get(hotelId) + 1 : 1);
                }
            }
        }
        List<Integer> keySortedList = frequencyCount.entrySet().stream().sorted((e1, e2) ->
                e2.getValue().compareTo(e1.getValue())).map(Map.Entry::getKey)
                .collect(Collectors.toList());
        for (Integer key : keySortedList) {
            System.out.print(key + " ");
        }
    }

    static int getExtraCustomerExecutive(Scanner in) {
        class DataNode {
            int timeEpoch;
            boolean start;

            DataNode(String timeEpoch, boolean start) {
                this.timeEpoch = Integer.parseInt(timeEpoch);
                this.start = start;
            }
        }
        int totalExecutives = in.nextInt();
        in.nextLine();
        int dataPoints = in.nextInt();
        in.nextLine();
        int maxExecutives = 0;
        int currParallelCustomers = 0;
        List<DataNode> dataNodeList = new ArrayList<DataNode>();
        for (int i = 0; i < dataPoints; i++) {
            String[] data = in.nextLine().split(" ");
            dataNodeList.add(new DataNode(data[0], true));
            dataNodeList.add(new DataNode(data[1], false));
        }
        dataNodeList = dataNodeList.stream().sorted(Comparator.comparingInt(e -> e.timeEpoch)).collect(Collectors.toList());
        for (DataNode node : dataNodeList) {
            currParallelCustomers += (node.start) ? 1 : -1;
            if (maxExecutives < currParallelCustomers) {
                maxExecutives = currParallelCustomers;
            }
        }
        return (maxExecutives > totalExecutives) ? maxExecutives - totalExecutives : 0;
    }

    private static boolean ashleyLikes(String word) {
        int[] countArray = new int[256];
        for (int i = 0; i < word.length(); i++) {
            int ascii = (int) word.charAt(i);
            if (countArray[ascii] > 0) {
                return false;
            }
            countArray[ascii]++;
        }
        return true;
    }

    static void countNumbers(int[][] arr) {
        int n = arr.length;
        int start = 0, end = 0, total = 0;
        for (int i = 0; i < n; i++) {
            start = arr[i][0];
            end = arr[i][1];
            total = end - start + 1;
            for (int j = start; j <= end; j++) {
                if (!ashleyLikes(Integer.toString(j))) {
                    total--;
                }
            }
            System.out.println(total);
        }
    }

    static int minimumPriceForTrip(Scanner br) throws Exception {
        int totalPrice = 0, min = Integer.MAX_VALUE;
        String[] tokens = br.nextLine().split(" ");
        List<Integer> priceList = Arrays.stream(tokens).map(e1 -> Integer.parseInt(e1.trim())).collect(Collectors.toList());
        tokens = br.nextLine().split(" ");
        List<Integer> petrolList = Arrays.stream(tokens).map(e1 -> Integer.parseInt(e1.trim())).collect(Collectors.toList());
        for (int j = 0; j < petrolList.size(); j++) {
            if (min > priceList.get(j)) {
                min = priceList.get(j);
            }
            totalPrice += min * petrolList.get(j);
        }
        return totalPrice;
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        //polygon(in);
        //deltaEncoding(in);
        //anagramsLexicographic(in);
        //System.out.println(pairExists(in));
        //hotelWordFrequencySorted(in);
        //System.out.print(getExtraCustomerExecutive(in));
        in.nextLine();
        in.nextLine();
        System.out.println(minimumPriceForTrip(in));
        in.close();
        System.out.println("Exit");
    }
}
