package meggy;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

/** Date-time value that either complies to {@link LocalDateTime} format or user-customized. */
public class MeggyTime {
    /** Cached dummy NA value. */
    public static final MeggyTime NA = new MeggyTime();
    /** The date-time format to be pass to output. */
    public static final DateTimeFormatter OUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    /** All acceptable date-time formats. Singapore's convention (date-month) is prioritized. */
    private static final DateTimeFormatter[] FORMATTERS;

    static { //initialize date-time formats
        final String[] timeSeps = {":", ""};
        final String[] dateSeps = {"/", "-", null};
        //day-month takes precedence before month-day
        final String[] dateFmts = {"dMy", "ydM", "Mdy", "yMd"};
        final String[] datePartLong = new String[dateFmts.length];
        Arrays.setAll(datePartLong, i -> dateFmts[i].replace("d", "dd")
                .replace("M", "MM").replace("y", "yyyy"));
        FORMATTERS = new DateTimeFormatter[timeSeps.length * dateSeps.length * dateFmts.length * 2];
        int i = 0;
        for (int j = 0; j < dateFmts.length; j++) {
            final String dateFmt = dateFmts[j];
            for (String dateSep : dateSeps) {
                final String datePart = dateSep == null ? datePartLong[j]
                        : dateFmt.charAt(0) + dateSep + dateFmt.charAt(1) + dateSep + dateFmt.charAt(2);
                for (String timeSep : timeSeps) {
                    final String timePart = "HH" + timeSep + "mm";
                    FORMATTERS[i++] = DateTimeFormatter.ofPattern(datePart + ' ' + timePart);
                    FORMATTERS[i++] = DateTimeFormatter.ofPattern(timePart + ' ' + dateFmt);
                }
            }
        }
    }

    /** Time that comply to {@link LocalDateTime} format or null if time is user-customized. */
    final LocalDateTime formatted;
    /** Unparsable user-customized time or null if can be parsed. */
    final String customized;

    /** Constructr of the dummy {@code NA} value */
    private MeggyTime() {
        formatted = null;
        customized = Util.NO_FOUND;
    }

    /** @param time Non-null. The trimmed time value to be interpreted. */
    private MeggyTime(String time) {
        assert time != null;
        formatted = parseTime(time);
        customized = formatted == null ? time : null;
    }

    /**
     * Factory method. Trims string if non-null. It also accepts {@code null} value, in which case it returns the cached
     * {@code NA} value.
     *
     * @param time Untrimmed time value to be interpreted or {@code null} if {@code NA} value is intended.
     */
    public static MeggyTime of(String time) {
        final MeggyTime ans = time == null ? NA : new MeggyTime(time.trim());
        assert ans != null && (ans.customized == null ^ ans.formatted == null);
        return ans;
    }

    /**
     * @param time Non-null. The raw string to attempt to parsed.
     * @return parsed date-time or {@code null} if no formatter can parse correctly.
     */
    public static LocalDateTime parseTime(String time) {
        assert time != null;
        for (DateTimeFormatter format : FORMATTERS) {
            try {
                return LocalDateTime.parse(time, format);
            } catch (DateTimeException ignored) {
            } // Try next formatter if unsuccessful
        }
        return null;
    }

    /** @return User-customized time string in square brackets or formatted date-time. */
    public String toString() {
        return formatted == null ? '[' + customized + ']' : formatted.format(OUT_FORMAT);
    }

    /**
     * Two {@link MeggyTime} objects are equal iff they have same (equal or both null) formatted time and customized
     * time.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MeggyTime)) {
            return false;
        }
        final MeggyTime other = (MeggyTime) o;
        return Objects.equals(formatted, other.formatted) && Objects.equals(customized, other.customized);
    }
}
