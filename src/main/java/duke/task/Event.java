package duke.task;

import duke.exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event task which stores the task description and the start and end of the task.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected String stringFrom;
    protected String stringTo;
    protected boolean fromHasTime = true;
    protected boolean toHasTime = true;
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

    /**
     * Constructor to create an Event task.
     *
     * @param description The description of the task.
     * @param from The start point of the task.
     * @param to The end point of the task.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        stringFrom = from;
        stringTo = to;
        parseFrom(from);
        parseTo(to);
        assert !from.isEmpty() && !to.isEmpty();
        if (from == null || to == null) {
            throw new DukeException("Invalid format for /from and /to fields!");
        }
    }

    /**
     * Parses the start inputted by the user into a LocalDateTime format.
     *
     * @param from The start point inputted by the user.
     */
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

    /**
     * Parses the end inputted by the user into a LocalDateTime format.
     *
     * @param to The end point inputted by the user.
     */
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

    /**
     * Returns the String start point inputted by the user.
     *
     * @return The String start point inputted by the user.
     */
    public String getFrom() {
        return stringFrom;
    }

    /**
     * Returns the String end point inputted by the user.
     *
     * @return The String end point inputted by the user.
     */
    public String getTo() {
        return stringTo;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
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
