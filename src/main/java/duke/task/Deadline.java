package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw DukeException.DATETIME_FORMAT;
        } catch (Exception e) {
            throw new DukeException("Unknown error occurred when parsing datetime.");
        }
    }

    public Deadline(String description, String by, boolean isMarked) {
        this(description, by);
        isDone = isMarked;
    }

    @Override
    public String toString() {
        String formattedBy = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[D]%s (by: %s)", super.toString(), formattedBy);
    }

    @Override
    public String toData() {
        String formattedBy = by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.format("Deadline | marked: %s ; description: %s ; deadline: %s", getMarkedStatus(),
                this.description, formattedBy);
    }

    public static Task fromData(String data) {
        Pattern pattern = Pattern.compile("(marked:) (.*) ; (description:) (.*) ; (deadline:) (.*)");
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            boolean isMarked = matcher.group(2).equals("1");
            String description = matcher.group(4);
            String deadline = matcher.group(6);
            return new Deadline(description, deadline, isMarked);
        }
        return Task.EMPTY_TASK;
    }
}
