package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Objects;


public class Deadline extends Task {

    private static final String typeToString = "D";
    private final LocalDateTime deadline;


    public Deadline(String task, String deadline) throws DateTimeParseException {
        super(task);
        this.type = Types.DEADLINE;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    public Deadline(String[] data) {
        super(data[2]);
        this.completed = Objects.equals(data[1], "X");
        this.deadline = LocalDateTime.parse(data[3]);
    }

    @Override
    public String status() {
        String status = this.completed ? "[X] " : "[ ] ";
        return "[" + typeToString + "]" + status + this.details + " (by: " +
                this.deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " [" +
                this.deadline.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + "]" + ")";
    }

    @Override
    public ArrayList<String> data() {
        ArrayList<String> data = new ArrayList<>();
        data.add(typeToString);
        data.add(this.completed ? "X" : " ");
        data.add(this.details);
        data.add(this.deadline.toString());
        return data;
    }
}
