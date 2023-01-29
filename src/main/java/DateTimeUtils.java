import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeUtils {
    public static String dateFormatter(String date) {
        SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy");
        try {
            return String.valueOf(format.parse(date));
        } catch (ParseException e) {
            System.out.println("Wrong Date Format!");
        }

        return date;
    }

    public static String timeFormatter(String time) {
        SimpleDateFormat format = new SimpleDateFormat("HHmm");
        try {
            return String.valueOf(format.parse(time));
        } catch (ParseException e) {
            System.out.println("Wrong Time Format!");
        }

        return time;
    }
}
