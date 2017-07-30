import java.text.ParseException;
import java.util.*;

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

    static void permute(char[] val, int l, int h) {
        if (l == h) {
            System.out.println(val);
            return;
        }
        for (int i = l; i < h; i++) {
            char x = val[l];
            val[l] = val[i];
            val[i] = x;
            permute(val, l + 1, h);
            x = val[l];
            val[l] = val[i];
            val[i] = x;
        }
    }

    static void permuteLowerCase(char[] val, int l, int h) {
        if (l == h) {
            System.out.println(val);
            return;
        }
        for (int i = l; i < h; i++) {
            val[i] = Character.toLowerCase(val[i]);
            permuteLowerCase(val, l + 1, h);
            val[i] = Character.toUpperCase(val[i]);
        }
    }

    static int minInsertion(String str) {
        int[][] table = new int[str.length() + 1][str.length() + 1];
        for (int gap = 1; gap < str.length(); gap++) {
            for (int i = 0; i < str.length(); i++) {
                int j = i + gap;
                if (j >= str.length()) {
                    continue;
                }
                if (str.charAt(i) == str.charAt(j)) {
                    table[i][j] = table[i + 1][j - 1];
                } else {
                    table[i][j] = Math.min(table[i + 1][j], table[i][j - 1]) + 1;
                }
            }
        }
        return table[0][str.length() - 1];
    }

    static int getLongestPalindromicSubSequence(String str) {
        int LPS[][] = new int[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            LPS[i][i] = 1;
        }
        for (int s = 2; s < str.length(); s++) {
            for (int i = 0; i < str.length() - s; i++) {
                int j = i + s;
                if (str.charAt(i) == str.charAt(j)) {
                    LPS[i][j] = LPS[i + 1][j - 1] + 2;
                } else {
                    LPS[i][j] = Math.max(LPS[i + 1][j], LPS[i][j - 1]);
                }
            }
        }
        return LPS[0][str.length() - 1];
    }

    static float atof(String s) {
        if (s.length() == 0) {
            return -1;
        }
        int dotSize = 0;
        float firstComponent = 0, secondComponent = 0;
        boolean isNegative = false, floating = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c < '9') {
                if (floating) {
                    dotSize *= 10;
                    secondComponent = secondComponent * 10 + (c - 48);
                } else {
                    firstComponent = firstComponent * 10 + (c - 48);
                }
            } else if (c == '.' && !floating) {
                floating = true;
                dotSize = 1;
            } else if (c == '-' && i == 0) {
                isNegative = true;
            } else {
                return -1;
            }
        }
        if (dotSize > 0) {
            secondComponent /= dotSize;
        }
        return (isNegative) ? firstComponent + secondComponent - 2 * (firstComponent + secondComponent) : firstComponent + secondComponent;
    }
}
