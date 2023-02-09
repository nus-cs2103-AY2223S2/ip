package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that has a deadline.
 */
public class DeadlineTask extends Task {
    private String by;
    private LocalDate byDate;

    public DeadlineTask(String title, String by) {
        super(title);
        this.by = by;
        formatIfDeadlineDate(by);
        assert this.by != null : "by String in DeadlineTask should not be null";
    }


    private void formatIfDeadlineDate(String by) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/uuuu");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd LLL uuuu");
        try {
            LocalDate output = LocalDate.parse(by, inputFormat);
            this.byDate =  output;
            this.by = output.format(outputFormat);
        } catch (DateTimeParseException e) {
            this.byDate = null;
            this.by = by;
        }
    }

    /**
     * Returns a String that contains information
     * on the DeadlineTask object that is used for loading
     * the task into the ToDoList on Duke startup.
     *
     * @return A String that is used for loading the task into Duke on startup.
     */
    @Override
    public String save() {
        String status = this.isDone ? "DONE/+/" : "NOTDONE/+/";
        String byToSave = this.by;
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d/M/uuuu");
        if (this.byDate != null) {
            byToSave = this.byDate.format(outputFormat);
        }
        return "DEADLINE/+/" + status + this.title + "/+/" + byToSave + "\n";
    }

    /**
     * Returns a String indicating the number of days left to the by date
     * in this DeadlineTask should the number of days left from today to
     * the date be less than or equal to the input number of days, otherwise
     * returns an empty String.
     *
     * @param dayRange The number of days from the by date to be compared to.
     * @return A String with the number of days remaining to the by date if
     *         today falls within the specified number of days in the input
     *         from the date, otherwise an empty String.
     */
    @Override
    public String remind(int dayRange) {
        if (this.byDate == null) {
            return "";
        }
        LocalDate today = LocalDate.now();
        int dayLeft = today.until(this.byDate).getDays();
        boolean isByDateOver = dayLeft < 0;
        boolean isByDateSoon = dayLeft <= dayRange;
        if (!isByDateOver && isByDateSoon && !this.isDone) {
            return "[D] " + title + " (Due in " + dayLeft + " day!)";
        }
        return "";
    }

    @Override
    public String toString() {
        String status = this.isDone ? "[x] " : "[ ] ";
        return "[D]" + status + this.title + " (by: " + this.by + ")";
    }
}
