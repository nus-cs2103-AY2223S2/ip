package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

//@@author {salty-flower}-reused
//Taken from [https://stackoverflow.com/questions/3389348/parse-any-date-in-java]
// with minor modifications

/**
 * A helper class to parse date string.
 */
public class DateTimeParser {
    private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<>() {{
                put("^\\d{8}$", "yyyyMMdd");
                put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
                put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
                put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
                put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
                put("^\\d{1,2}\\s[A-Za-z]{3}\\s\\d{4}$", "dd MMM yyyy");
                put("^[A-Za-z]{3}\\s\\d{1,2}\\s\\d{4}$", "MMM dd yyyy");
                put("^\\d{1,2}\\s[A-Za-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
                put("^\\d{12}$", "yyyyMMddHHmm");
                put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
                put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
                put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
                put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
                put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
                put("^\\d{1,2}\\s[A-Za-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
                put("^\\d{1,2}\\s[A-Za-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
                put("^\\d{14}$", "yyyyMMddHHmmss");
                put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
                put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
                put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
                put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
                put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
                put("^\\d{1,2}\\s[A-Za-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
                put("^\\d{1,2}\\s[A-Za-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
        }};

    /**
     * Determine SimpleDateFormat pattern matching with the given date string. Throws exception if
     * format is unknown. You can simply extend DateUtil with more formats if needed.
     *
     * @param dateString The date string to determine the SimpleDateFormat pattern for.
     * @return The parsed LocalDateTime object.
     * @throws IllegalArgumentException If it cannot parse given string.
     * @see SimpleDateFormat
     */
    public static LocalDateTime parse(String dateString) {
        for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
            if (dateString.toLowerCase().matches(regexp)) {
                try {
                    return (new SimpleDateFormat(DATE_FORMAT_REGEXPS.get(regexp)).parse(dateString)).toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();
                } catch (ParseException e) {
                    throw new IllegalArgumentException(String.format("Matched by cannot parse: %s\n", dateString));
                }
            }
        }
        throw new IllegalArgumentException(String.format("Unknown format: %s\n", dateString));
    }
}
