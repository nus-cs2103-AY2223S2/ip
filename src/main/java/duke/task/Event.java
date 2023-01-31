package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Objects;

public class Event extends Task {
    private static final String typeToString = "E";
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


    public Event(String task, String startDateTime, String endDateTime) throws DateTimeParseException {
        super(task);
        this.type = Types.EVENT;

        this.startDateTime = LocalDateTime.parse(startDateTime, FORMATTER);
        this.endDateTime = LocalDateTime.parse(endDateTime, FORMATTER);
    }

    public Event(String[] data) {
        super(data[2]);
        this.completed = Objects.equals(data[1], "X");
        this.startDateTime = LocalDateTime.parse(data[3]);
        this.endDateTime = LocalDateTime.parse(data[4]);
    }

    @Override
    public String status() {
        String status = this.completed ? "[X] " : "[ ] ";
        return "[" + typeToString + "]" + status + this.details +
                " (from: " + this.startDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " [" +
                this.startDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + "]" + ")" + " to: " +
                this.endDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " [" +
                this.endDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + "]" + ")" + ")";
    }

    @Override
    public ArrayList<String> data() {
        ArrayList<String> data = new ArrayList<>();
        data.add(typeToString);
        data.add(this.completed ? "X" : " ");
        data.add(this.details);
        data.add(this.startDateTime.toString());
        data.add(this.endDateTime.toString());
        return data;
    }
}
