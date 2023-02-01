import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    protected boolean hasTime = true;
    private static final String[] DATE_FORMATS = {
            "dd-MM-yyyy",
            "dd/MM/yyyy",
            "yyyy-MM-dd",
            "yyyy/MM/dd"
    };
    private static final String[] DATE_TIME_FORMATS = {
            "dd-MM-yyyy HH:mm",
            "dd/MM/yyyy HH:mm",
            "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd HH:mm"
    };
    private static final DateTimeFormatter DISPLAY_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static final DateTimeFormatter DISPLAY_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy h a");

    public Deadline(String description, String by) {
        super(description);
        for (String format : DATE_FORMATS) {
            try {
                this.by = LocalDateTime.of(LocalDate.parse(by, DateTimeFormatter.ofPattern(format)),
                        LocalDateTime.now().toLocalTime());
                hasTime = false;

            } catch (DateTimeException e) {
                //Try next format
                continue;
            }
            break;
        }
        if (hasTime) {
            for (String format : DATE_TIME_FORMATS) {
                try {
                    this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(format));
                } catch (DateTimeException e) {
                    //Try next format
                    continue;
                }
                break;
            }
        }
    }

    public String getBy() {
        if (hasTime) {
            return by.format(DISPLAY_DATE_TIME_FORMAT);
        } else {
            return by.toLocalDate().format(DISPLAY_DATE_FORMAT);
        }
    }

    @Override
    public String toString() {
        if (hasTime) {
            return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_DATE_TIME_FORMAT) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by.toLocalDate().format(DISPLAY_DATE_FORMAT) + ")";
        }
    }

}
