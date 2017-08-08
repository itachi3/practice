/**
 * Created by G on 07/08/17.
 */
class General {

    static double afterUnits(double k, int curr_i, int curr_j, int i, int j) {
        if (k == 0) {
            return 0;
        }
        if (curr_i == i && curr_j == j) {
            return k > 1 ? 1 : k;
        }
        k = k > 1 ? (k - 1) / 2 : 0;
        double leftQuant = afterUnits(k, curr_i + 1, curr_j - 1, i, j);
        double rightQuant = afterUnits(k, curr_i + 1, curr_j + 1, i, j);
        return leftQuant > 0 ? leftQuant : rightQuant;
    }
}
