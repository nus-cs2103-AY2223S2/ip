package duke.task;

import duke.exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    protected LocalDateTime by;
    protected String stringBy;
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

    public Deadline(String description, String by) throws DukeException {
        super(description);
        stringBy = by;
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
        if (this.by == null) {
            throw new DukeException("Invalid format for /by field!");
        }
    }

    public String getBy() {
        return stringBy;
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
