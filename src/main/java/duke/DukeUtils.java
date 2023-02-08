package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Utilities for the application.
 * <p>
 * The utilities is provided using {@code static} methods.
 */
public class DukeUtils {

    private static final String INPUT_FORMAT_STRING = "yyyy-MM-dd";
    private static final String OUTPUT_FORMAT_STRING = "MMM dd yyyy";

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern(INPUT_FORMAT_STRING);
    private static final DateTimeFormatter OUTPUT_FORTMAT =
            DateTimeFormatter.ofPattern(OUTPUT_FORMAT_STRING);

    /**
     * Converts a {@code LocalDate} instance to a string, using {@code MMM dd yyyy} format.
     *
     * @param date the {@code LocalDate} instance to be converted
     * @return a string, representing the {@code LocalDate} instance
     */
    public static String convertDateToString(LocalDate date) {
        assert date != null;
        return date.format(OUTPUT_FORTMAT);
    }

    /**
     * Converts an input string to a {@code LocalDate} instance. If the conversion fails, an empty
     * {@code Optional} is returned.
     *
     * @param input the input string
     * @return an {@code Optional} instance holding the conversion result
     */
    public static Optional<LocalDate> convertStringToDate(String input) {
        assert input != null;
        try {
            return Optional.of(LocalDate.parse(input, INPUT_FORMAT));
        } catch (DateTimeParseException ex) {
            return Optional.empty();
        }
    }

    /**
     * Converts an input string to an integer. If the conversion fails, an empty {@code OptionalInt}
     * is returned.
     *
     * @param input the input string
     * @return an {@code OptionalInt} holding the result of the conversion
     */
    public static OptionalInt convertStringToInt(String input) {
        assert input != null;
        try {
            return OptionalInt.of(Integer.parseInt(input));
        } catch (NumberFormatException ex) {
            return OptionalInt.empty();
        }
    }
}
