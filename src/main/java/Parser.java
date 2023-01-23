import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Parser {
    public static Optional<LocalDate> parseDate(String timeInput) {
        try {
            timeInput = timeInput.replaceAll("/","-");
            return Optional.of(LocalDate.parse(timeInput));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    public static int parseMarkUnmarkDelete(String lineInput) {
        return Integer.parseInt(lineInput.split(" ")[1]) - 1;
    }

}
