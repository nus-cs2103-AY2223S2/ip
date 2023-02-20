package voile.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;
import java.util.function.Supplier;

import voile.model.command.Keyword;

/**
 * Utilities that convert one data type to another for the application.
 * <p>
 * The utilities is provided using {@code static} methods.
 */
public class Conversions {

    private static final DateTimeFormatter DATE_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_OUTPUT_FORTMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter DAY_INPUT_FORMAT = DateTimeFormatter.ofPattern("EEE");

    private static <T> Optional<T> optionallyParse(Supplier<T> parser) {
        T result;
        try {
            result = parser.get();
        } catch (RuntimeException ex) {
            result = null;
        }
        return Optional.ofNullable(result);
    }

    /**
     * Converts a {@code LocalDate} instance to a string, using {@code MMM dd yyyy} format.
     *
     * @param date the {@code LocalDate} instance to be converted
     * @return a string, representing the {@code LocalDate} instance
     */
    public static String showDate(LocalDate date) {
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
        return optionallyParse(() -> LocalDate.from(DATE_INPUT_FORMAT.parse(input)));
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
        return optionallyParse(() -> LocalDate
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.from(DAY_INPUT_FORMAT.parse(input)))));
    }

    /**
     * Converts an input string to an integer. If the conversion fails, an empty {@code Optional} is
     * returned.
     *
     * @param input the input string
     * @return an {@code Optional} instance holding conversion result
     */
    public static Optional<Integer> parseInt(String input) {
        return optionallyParse(() -> Integer.parseInt(input));
    }

    /**
     * Converts an input string into the a keyword (of the application).
     *
     * @param value the input string that possibly represents a keyword
     * @return an {@code Optional} instance holding conversion result
     */
    public static Optional<Keyword> parseKeyword(String value) {
        return optionallyParse(() -> Keyword.valueOf(value.toUpperCase()));
    }
}
