package duke;

import duke.exceptions.DukeyException;

import java.time.LocalDate;

public class Deadline extends Task {
    private static final String TYPE = "[D]";
    private LocalDate deadline;

    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline(String name, LocalDate deadline, boolean isMarked) {
        super(name, isMarked);
        this.deadline = deadline;
    }

    public static Deadline createDeadline(Ui ui) throws DukeyException {
        String deadlineName = ui.readTaskName("Deadline");
        LocalDate deadlineTime = ui.readTime("Deadline");

        return new Deadline(deadlineName, deadlineTime);

    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public static Deadline createDeadlineFromLog(String[] logStringArray) {
        String name = logStringArray[2];
        String deadlineString = logStringArray[3];
        boolean isMarked = !logStringArray[1].equals("0");
        LocalDate deadline = LocalDate.now();
        try {
            deadline = DukeyTime.getDateFromString(deadlineString);
        } catch (DukeyException e) {
            e.getMessage();
        }

        return new Deadline(name, deadline, isMarked);
    }

    @Override
    public String messageWhenAdded() {
        return "DukeyList just added a new deadline:";
    }

    @Override
    public String getLogString() {
        return "D" + "," + this.isDoneStatus() + "," + this.getName() + "," + this.getDeadline();
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X] " + this.getName() + " (by " + DukeyTime.dateToString(this.deadline) + ")";
        }
        return TYPE + "[ ] " + this.getName() + " (by " + DukeyTime.dateToString(this.deadline) + ")";
    }
}
