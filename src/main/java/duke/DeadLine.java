package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task with one deadline.
 */
class DeadLine extends Task {

    /** The date of the deadline.*/
    LocalDate localDate;

    /**
     * Constructs a new Deadline.
     * @param keyword The keyword command to create a new Deadline task.
     * @param message The description of the task.
     * @param completed The completion status of the task.
     */
    public DeadLine(String keyword, String message, Boolean completed) {
        super(keyword, message, completed);
        String[] separateText = this.description.split(" /by ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.localDate = LocalDate.parse(separateText[1], formatter);
    }

    @Override
    public String provideDetails() {

        String[] separateText = this.description.split(" /by ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String date = localDate.format(formatter);

        return this.completed ? "[D]" + "[x] " + separateText[0] + " (by: " + date + ")"
                : "[D]" + "[ ] " + separateText[0] + " (by: " + date + ")";
    }

}

