package duke;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.fxml.FXMLLoader;

public final class Utils {
    /**
     * Recombine an array of strings into a singular string, starting from the provided
     * start index to the end index exclusive.
     * @param args Array containing strings to join
     * @param from From index, inclusive
     * @param to To index, exclusive
     * @return Recombined string
     */
    public final static String stringJoiner(String[] args, int from, int to) {
        return String.join(" ", Arrays.copyOfRange(args, from, to));
    }

    public final static String stringJoiner(String[] args, int from) {
        return stringJoiner(args, from, args.length);
    }

    private final static <T extends TemporalAccessor> Function<String, Optional<T>> createParser(
        Iterable<String> patterns, 
        BiFunction<String, DateTimeFormatter, T> parser
    ) {
        return str -> {
            for (String pattern : patterns) {
                try {
                    return Optional.of(parser.apply(str, DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH)));
                } catch (DateTimeParseException r) {
                //   System.out.format("Couldn't match %s with %s\n", str, pattern);
                }
            }
            return Optional.empty();
        };
    }

    /**
     * Parse the strings for date and time information. Passing null for one of the arguments indicates
     * that only either date or time information was available. Passing both will create a LocalDateTime
     * with both time and date information. If there is a missing component, it will be filled with LocalDateTime.now().
     * @param str0 Null or string to parse for either date or time
     * @param str1 Null or string to parse for either date or time
     * @return LocalDateTime containing either the parsed date and time or the parsed arguments
     * combined with the current time.
     */
    public final static LocalDateTime parseDateTime(String str0, String str1) {
        List<String> dateFormats = List.of(
            "dd/MM" 
        );

        List<String> timeFormats = List.of(
            "hh:mma",
            "kk:mm",
            "kkmm"
        );

        Function<String, Optional<MonthDay>> dateParser = createParser(dateFormats, MonthDay::parse);
        Function<String, Optional<LocalTime>> timeParser = createParser(timeFormats, LocalTime::parse);

        if (str0 == null && str1 == null) {
            throw new IllegalArgumentException("Both str0 and str1 cannot be null!", null);
        } else if (str0 == null && str1 != null) {
            return parseDateTime(str1, null);
        } 
        
        LocalDateTime currentTime = LocalDateTime.now();

        if (str0 != null && str1 == null) {
            Optional<MonthDay> dateValue = dateParser.apply(str0);
            if (dateValue.isEmpty()) {
                Optional<LocalTime> timeValue = timeParser.apply(str0.toUpperCase());

                if (timeValue.isEmpty()) { 
                    throw new DateTimeParseException("Invalid date/time string!", str0, 0);
                }
                return LocalDateTime.of(currentTime.toLocalDate(), timeValue.get());
            }
            return LocalDateTime.of(dateValue.get().atYear(currentTime.getYear()), currentTime.toLocalTime());
        } else {
            Optional<MonthDay> date = dateParser.apply(str0);
            Optional<LocalTime> time = timeParser.apply(str1.toUpperCase());

            if (date.isEmpty() || time.isEmpty()) {
                date = dateParser.apply(str1);
                time = timeParser.apply(str0.toUpperCase());
            }

            if (date.isEmpty() || time.isEmpty()) {
                throw new DateTimeParseException("Invalid date/time string!", str1, 0);
            }

            LocalDate dateValue = date.get().atYear(currentTime.getYear());
            return LocalDateTime.of(dateValue, time.get());
        }
    }

    public static String flattenIterableWithIndex(Iterable<?> it, int start) {
        StringJoiner joiner = new StringJoiner("\n");
        int count = start - 1;
        for (Object each : it) {
            count++;
            joiner.add(String.format("%d. %s", count, each.toString()));
        }

        return joiner.toString();
    }

    public static <T, U> T loadFxmlFile(URL path, U controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(path);
        loader.setController(controller);
        T loaded = loader.<T>load();
        return loaded;
    }
}
