import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected boolean fromHasTime = true;
    protected boolean toHasTime = true;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
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

    public Event(String description, String from, String to) {
        super(description);
        parseFrom(from);
        parseTo(to);
    }

    private void parseFrom(String from) {
        for (String format : DATE_FORMATS) {
            try {
                this.from = LocalDateTime.of(LocalDate.parse(from, DateTimeFormatter.ofPattern(format)),
                        LocalDateTime.now().toLocalTime());
                fromHasTime = false;

            } catch (DateTimeException e) {
                //Try next format
                continue;
            }
            break;
        }
        if (fromHasTime) {
            for (String format : DATE_TIME_FORMATS) {
                try {
                    this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern(format));
                } catch (DateTimeException e) {
                    //Try next format
                    continue;
                }
                break;
            }
        }
    }

    private void parseTo(String to) {
        for (String format : DATE_FORMATS) {
            try {
                this.to = LocalDateTime.of(LocalDate.parse(to, DateTimeFormatter.ofPattern(format)),
                        LocalDateTime.now().toLocalTime());
                toHasTime = false;

            } catch (DateTimeException e) {
                //Try next format
                continue;
            }
            break;
        }
        if (toHasTime) {
            for (String format : DATE_TIME_FORMATS) {
                try {
                    this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern(format));
                } catch (DateTimeException e) {
                    //Try next format
                    continue;
                }
                break;
            }
        }
    }

    public String getFrom() {
        if (fromHasTime) {
            return from.format(DISPLAY_DATE_TIME_FORMAT);
        } else {
            return from.toLocalDate().format(DISPLAY_DATE_FORMAT);
        }
    }

    public String getTo() {
        if (toHasTime) {
            return to.format(DISPLAY_DATE_TIME_FORMAT);
        } else {
            return to.toLocalDate().format(DISPLAY_DATE_FORMAT);
        }
    }

    @Override
    public String toString() {
        String fromPrint;
        String toPrint;
        if (fromHasTime) {
            fromPrint = from.format(DISPLAY_DATE_TIME_FORMAT);
        } else {
            fromPrint = from.toLocalDate().format(DISPLAY_DATE_FORMAT);
        }
        if (toHasTime) {
            toPrint = to.format(DISPLAY_DATE_TIME_FORMAT);
        } else {
            toPrint = to.toLocalDate().format(DISPLAY_DATE_FORMAT);
        }
        return "[E]" + super.toString() + " (from: " + fromPrint + " to: " + toPrint + ")";
    }
}
