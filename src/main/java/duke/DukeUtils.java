package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.OptionalInt;

public class DukeUtils {

    private static final String INPUT_FORMAT_STRING = "yyyy-MM-dd";
    private static final String OUTPUT_FORMAT_STRING = "MMM dd yyyy";

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern(INPUT_FORMAT_STRING);
    private static final DateTimeFormatter OUTPUT_FORTMAT =
            DateTimeFormatter.ofPattern(OUTPUT_FORMAT_STRING);

    public static String convertDateToString(LocalDate date) {
        return date.format(OUTPUT_FORTMAT);
    }

    public static Optional<LocalDate> convertStringToDate(String input) {
        try {
            return Optional.of(LocalDate.parse(input, INPUT_FORMAT));
        } catch (DateTimeParseException ex) {
            return Optional.empty();
        }
    }

    public static OptionalInt convertStringToInt(String input) {
        try {
            return OptionalInt.of(Integer.parseInt(input));
        } catch (NumberFormatException ex) {
            return OptionalInt.empty();
        }
    }
}
