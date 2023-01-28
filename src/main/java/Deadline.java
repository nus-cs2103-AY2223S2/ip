import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private final static Pattern OUTPUT_DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$"); //In yyyy-MM-dd
    private final static Pattern STORAGE_DATE_PATTERN = Pattern.compile("^\\w{3} \\d{2} \\d{4}$"); //In MMM dd yyyy
    private final static String OUTPUT_DATE_FORMAT = "yyyy-MM-dd" ;
    private final static String STORAGE_DATE_FORMAT = "MMM dd yyyy" ;

    protected String date;

    public Deadline(String description, String endDate) {
        super(description);
        if (OUTPUT_DATE_PATTERN.matcher(endDate).matches()) {
            this.date = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)).format(DateTimeFormatter.ofPattern(STORAGE_DATE_FORMAT));
        } else {
            this.date = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern(STORAGE_DATE_FORMAT)).format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT));
        }
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "]" + " Deadline | " + this.description + " | " + this.date;
    }
}
