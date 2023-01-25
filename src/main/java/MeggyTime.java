import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Time that either comply to {@link LocalDateTime} format or user-customized.
 */
public class MeggyTime {
    /**
     * Time that comply to {@link LocalDateTime} format or null if time is user-customized.
     */
    final LocalDateTime formatted;
    /**
     * Unparsable user-customized time or null if can be parsed.
     */
    final String customized;

    /**
     * Constructr of the dummy {@code NA} value
     */
    private MeggyTime() {
        formatted = null;
        customized = Util.noFound;
    }

    /**
     * @param time Non-null. The trimmed time value to be interpreted.
     */
    private MeggyTime(String time) {
        this.formatted = parseTime(time);
        this.customized = this.formatted == null ? time : null;
    }

    /**
     * Factory method that trims none-null strings. It also accepts {@code null} value, in which case it returns the
     * cached {@code NA} value.
     *
     * @param time Untrimmed time value to be interpreted or {@code null} if {@code NA} value is intended.
     */
    public static MeggyTime of(String time) {
        return time == null ? NA : new MeggyTime(time.trim());
    }

    /**
     * All acceptable date-time formats. Singapore's convention (date-month) is prioritized.
     */
    private static final DateTimeFormatter[] formats;
    /**
     * Output format.
     */
    private static final DateTimeFormatter outFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    /**
     * Encode format.
     */
    private static final DateTimeFormatter encodeFmt = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
    /**
     * Cached dummy NA value.
     */
    public static final MeggyTime NA = new MeggyTime();

    static { //initialize date-time formats
        final String[] timeSeps = {":", ""};
        final String[] dateSeps = {"/", "-", null};
        //day-month takes precedence before month-day
        final String[] dateFmts = {"dMy", "ydM", "Mdy", "yMd"};
        final String[] datePartLong = new String[dateFmts.length];
        Arrays.setAll(datePartLong, i -> dateFmts[i].replace("d", "dd")
                .replace("M", "MM").replace("y", "yyyy"));
        formats = new DateTimeFormatter[timeSeps.length * dateSeps.length * dateFmts.length * 2];
        int i = 0;
        for (int j = 0; j < dateFmts.length; j++) {
            final String dateFmt = dateFmts[j];
            for (String dateSep : dateSeps) {
                final String datePart = dateSep == null ? datePartLong[j] :
                        dateFmt.charAt(0) + dateSep + dateFmt.charAt(1) + dateSep + dateFmt.charAt(2);
                for (String timeSep : timeSeps) {
                    final String timePart = "HH" + timeSep + "mm";
                    formats[i++] = DateTimeFormatter.ofPattern(datePart + ' ' + timePart);
                    formats[i++] = DateTimeFormatter.ofPattern(timePart + ' ' + dateFmt);
                }
            }
        }
    }

    /**
     * @return parsed date-time or null if no formatter can parse correctly.
     */
    public static LocalDateTime parseTime(String time) {
        for (DateTimeFormatter format : formats) {
            try {
                return LocalDateTime.parse(time, format);
            } catch (DateTimeException ignored) {
            } // try next formatter if unsuccessful
        }
        return null;
    }

    /**
     * @return User-customized time string in square brackets or formatted date-time.
     */
    public String toString() {
        return formatted == null ? '[' + customized + ']' : formatted.format(outFmt);
    }

    /**
     * @return String representation used in data file. User-customized time is unchanged.
     */
    public String encode() {
        return formatted == null ? customized : formatted.format(encodeFmt);
    }
}
