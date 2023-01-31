package task;


import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class handles the storing of
 * Event Task attributes.
 * @author Bryan Ong
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event(String name, String start, String end, boolean done) {
        super(name, done);
        this.start = LocalDateTime.parse(start,
                Task.DATE_TIME_FORMATTER);
        this.end = LocalDateTime.parse(end,
                Task.DATE_TIME_FORMATTER);
    }
    /**
     * This method handles the writing to data file
     * @param file File to be written to
     * @return String Information of task to be written.
     */
    @Override
    public String write(File file) {
        return this.toWrite();
    }

    /**
     * This method handles the creation of the String
     * to be printed on user interface.
     * @return String Message to be printed.
     */
    @Override
    public String toString() {
        return "   [E]" + super.toString() + " |from: "
                + start.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,
                FormatStyle.SHORT)) + " |to: "
                + end.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,
                FormatStyle.SHORT)) + "|\n";
    }

    /**
     * This method handles the creation of the String
     * to be written on data file.
     * @return String Message to be written.
     */
    @Override
    public String toWrite() {
        return "E | " + super.toWrite() + " | "
                + start.format(Task.DATE_TIME_FORMATTER) + " | "
                + end.format(Task.DATE_TIME_FORMATTER) + "\n";
    }
}