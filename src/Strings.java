import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by G on 17/06/17.
 */
public class Strings {
    // https://www.careercup.com/question?id=5708966056165376
    // Valid if 7 cuts and each number between 1 to 59
    static boolean isLotteryTicket(String s, Set<Integer> cuts) {
        if (null == s || "".equals(s)) {
            return cuts.size() == 7;
        }
        Boolean twoCut = false, singleCut;
        if (s.length() >= 2) {
            Integer twoDigit = Integer.parseInt(s.substring(0, 2));
            if (twoDigit < 59) {
                cuts.add(twoDigit);
                twoCut = (s.length() == 2) ? isLotteryTicket("", cuts) :
                        isLotteryTicket(s.substring(2, s.length()), cuts);
            }
        }
        Integer singleDigit = Character.getNumericValue(s.charAt(0));
        cuts.add(singleDigit);
        singleCut = (s.length() == 1) ? isLotteryTicket("", cuts) :
                isLotteryTicket(s.substring(1, s.length()), cuts);
        return singleCut || twoCut;
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
}