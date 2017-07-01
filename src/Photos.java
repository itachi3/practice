import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by G on 01/07/17.
 */
public class Photos {
    String city;
    String newName;
    int index;
    long epochTime;
    String extension;

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public Photos(String[] chunks, int index) throws ParseException {
        this.city = chunks[1];
        this.index = index;
        this.extension = chunks[0].split("\\.")[1];
        this.epochTime = dateFormat.parse(chunks[2]).getTime() / 1000;
    }

    @Override
    public String toString() {
        return "Photos{" +
                "city='" + city + '\'' +
                ", newName='" + newName + '\'' +
                ", index=" + index +
                ", epochTime=" + epochTime +
                ", extension='" + extension + '\'' +
                '}';
    }

    public void setNewName(int maxDegit, int i) {
        String num = String.format("%0" + String.valueOf(maxDegit - String.valueOf(i).length() + 1) + "d", i);
        newName = city + num + "." + extension;
    }

    public long getEpochTime() {
        return epochTime;
    }

    public int getIndex() {
        return index;
    }

    public String getNewName() {
        return newName;
    }
}
