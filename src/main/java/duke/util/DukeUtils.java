package duke.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Optional;

/**
 * Utilities for the application.
 * <p>
 * The utilities is provided using {@code static} methods.
 */
public class DukeUtils {

    private static final DateTimeFormatter DATE_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_OUTPUT_FORTMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter DAY_INPUT_FORMAT = DateTimeFormatter.ofPattern("EEE");

    /**
     * Converts a {@code LocalDate} instance to a string, using {@code MMM dd yyyy} format.
     *
     * @param date the {@code LocalDate} instance to be converted
     * @return a string, representing the {@code LocalDate} instance
     */
    public static String showDate(LocalDate date) {
        assert date != null;
        return date.format(DATE_OUTPUT_FORTMAT);
    }

    /**
     * Converts an input string to a {@code LocalDate} instance, using {@code yyyy-MM-dd} format. If
     * the conversion fails, an empty {@code Optional} is returned.
     *
     * @param input the input string
     * @return an {@code Optional} instance holding the conversion result
     */
    public static Optional<LocalDate> parseDate(String input) {
        assert input != null;
        try {
            LocalDate date = LocalDate.from(DATE_INPUT_FORMAT.parse(input));
            return Optional.of(date);
        } catch (DateTimeParseException ex) {
            return Optional.empty();
        }
    }

    /**
     * Converts an input string to a {@code LocalDate} instance, using {@code EEE} format. The
     * returned date will be the nearest day of week (that is represented by the input string), in
     * the future. If the conversion fails, an empty {@code Optional} is returned.
     *
     * @param input the input string
     * @return an {@code Optional} instance holding the conversion result
     */
    public static Optional<LocalDate> parseDay(String input) {
        assert input != null;
        try {
            DayOfWeek day = DayOfWeek.from(DAY_INPUT_FORMAT.parse(input));
            LocalDate date = LocalDate.now().with(TemporalAdjusters.next(day));
            return Optional.of(date);
        } catch (DateTimeParseException ex) {
            return Optional.empty();
        }
    }

    /**
     * Converts an input string to an integer. If the conversion fails, an empty {@code Optional} is
     * returned.
     *
     * @param input the input string
     * @return an {@code Optional} holding the result of the conversion
     */
    public static Optional<Integer> parseInt(String input) {
        assert input != null;
        try {
            return Optional.of(Integer.parseInt(input));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }

    /**
     * Chooses between many {@code Optional} instances.
     *
     * @param <T> the type of the objects contained inside the {@code Optional} instances
     * @param opts an array of {@code Optional} instances
     * @return the first {@code Optional} instance that contains an object inside it
     */
    @SafeVarargs
    public static <T> Optional<T> choice(Optional<T>... opts) {
        return Arrays.stream(opts).filter(Optional::isPresent).findFirst().orElse(Optional.empty());
    }
}
