package task;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    private static StringBuilder strBuild = new StringBuilder();
    private final LocalDateTime end;
    public Deadline(String name, String end, boolean done) {
        super(name, done);
        this.end = LocalDateTime.parse(end,
                Task.DATE_TIME_FORMATTER);
    }

    @Override
    public String write(File file) {
        return this.toWrite();
    }

    @Override
    public String toString() {
        return "   [D]" + super.toString() + " |by: "
                + end.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,
                FormatStyle.SHORT)) + "|\n";
    }

    @Override
    public String toWrite() {
        return "D | " + super.toWrite() + " | " + end.format(Task.DATE_TIME_FORMATTER) + "\n";
    }
}
