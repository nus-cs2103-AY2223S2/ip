import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Demo {
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate res = LocalDate.parse("2000-11-21", dateTimeFormatter);
//            System.out.println(res.format(DateTimeFormatter.ofPattern("MMM")));
            System.out.println(res);
        } catch (DateTimeParseException e) {
            System.out.println("String cannot be parsed");
        }
    }
}
