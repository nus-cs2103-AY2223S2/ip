package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class Utils {
  public final static String stringJoiner(String[] args, int from, int to) {
    return String.join(" ", Arrays.copyOfRange(args, from, to));
  }

  private final static <T extends TemporalAccessor> Function<String, Optional<T>> createParser(
      Iterable<String> patterns, 
      BiFunction<String, DateTimeFormatter, T> parser
  ) {
    return (str) -> {
        for (String pattern : patterns) {
            try {
                return Optional.of(parser.apply(str, DateTimeFormatter.ofPattern(pattern)));
            } catch (DateTimeParseException r) { }
        }
        return Optional.empty();
    };
  }

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
        Optional<LocalTime> timeValue = timeParser.apply(str0);

        if (timeValue.isEmpty()) throw new DateTimeParseException("Invalid date/time string!", str0, 0);
        return LocalDateTime.of(currentTime.toLocalDate(), timeValue.get());
      }
      return LocalDateTime.of(dateValue.get().atYear(currentTime.getYear()), currentTime.toLocalTime());
    } else {
      Optional<MonthDay> date = dateParser.apply(str0);
      Optional<LocalTime> time = timeParser.apply(str1);

      if (date.isEmpty() || time.isEmpty()) {
          date = dateParser.apply(str1);
          time = timeParser.apply(str0);
      }

      if (date.isEmpty() || time.isEmpty()) {
          throw new DateTimeParseException("Invalid date/time string!", str1, 0);
      }

      LocalDate dateValue = date.get().atYear(currentTime.getYear());
      return LocalDateTime.of(dateValue, time.get());
    }
  }
}
