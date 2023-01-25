import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    protected static LocalDateTime parseDate(String s) throws DukeException {
        if (s.length() == 10) {
            s = s + " 2359";
        }
        try {
            return LocalDateTime.parse(s, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date input.");
        }
    }

    public static void main(String[] args) {
        try {
            parseDate("01-12-2019");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
