import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class Event extends Task {
    private final static Pattern OUTPUT_DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$"); //In yyyy-MM-dd
    private final static Pattern STORAGE_DATE_PATTERN = Pattern.compile("^\\w{3} \\d{2} \\d{4}$"); //In MMM dd yyyy
    private final static String OUTPUT_DATE_FORMAT = "yyyy-MM-dd" ;
    private final static String STORAGE_DATE_FORMAT = "MMM dd yyyy" ;
    protected String dateStart;
    protected String dateEnd;

    public Event(String description, String dateStart, String dateEnd) {
        super(description);
        if (OUTPUT_DATE_PATTERN.matcher(dateStart).matches()) {
            this.dateStart = LocalDateTime.parse(dateStart, DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)).format(DateTimeFormatter.ofPattern(STORAGE_DATE_FORMAT));
            this.dateEnd = LocalDateTime.parse(dateEnd, DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)).format(DateTimeFormatter.ofPattern(STORAGE_DATE_FORMAT));
        } else {
            this.dateStart = LocalDateTime.parse(dateStart, DateTimeFormatter.ofPattern(STORAGE_DATE_FORMAT)).format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT));
            this.dateEnd = LocalDateTime.parse(dateEnd, DateTimeFormatter.ofPattern(STORAGE_DATE_FORMAT)).format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT));
        }
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "]" + " Event | " + this.description + " | " + this.dateStart +
                " - " + this.dateEnd;
    }
}
