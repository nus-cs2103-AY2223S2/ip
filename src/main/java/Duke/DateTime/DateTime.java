package Duke.DateTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public DateTime() {}

    public static LocalDateTime parseDateTimeString(String dateTimeString) throws DateTimeParseException {
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTime.formatter);
        return dateTime;
    }

    public static String getDateTimeString(LocalDateTime dateTime) {
        return dateTime.format(DateTime.formatter);
    }

//    public static void main(String[] args) {
//        String dateTime = "2020-01-20 13:44";
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime ldt = LocalDateTime.parse(dateTime, dtf);
//        System.out.println(ldt.format(dtf));
//        System.out.println(ldt.getMinute());
//        System.out.println(ldt.getHour());
//        System.out.println(ldt.getDayOfMonth());
//        System.out.println(ldt.getMonth());
//        System.out.println(ldt.getYear());
//    }
}
