package duke.task;
import duke.exceptions.IncorrectDateFormatException;
import duke.exceptions.NeroException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline is a subclass of Task. It contains a specified deadline for a task.
 */
public class Deadline extends Task {

    private LocalDate date;

    /**
     * Constructor for Deadline
     * @param description Details of the Deadline task
     * @param isDone Keeps track of whether the Deadline task is completed
     * @param deadline Due date of the Deadline task, formatted as yyyy-mm-dd
     * @throws NeroException When the date is entered in the incorrect format (not yyyy-mm-dd)
     */
    public Deadline(String description, boolean isDone,
            String deadline) throws NeroException {
        super(description, isDone);
        try {
            this.date = LocalDate.parse(deadline.trim());
        } catch (DateTimeParseException e) {
            throw new IncorrectDateFormatException();
        }
    }

    /**
     * Similar as above constructor, except it initialises isDone to False by default
     */
    public Deadline(String description, String deadline) throws NeroException {
        this(description, false, deadline);
    }


    /**
     * @return String of task icon
     */
    public String getTaskIcon() {
        return "D";
    }

    /**
     * @return String formatted as d MMM yyyy (e.g 1 Jan 2020)
     */
    String dateFormatter() {
        return "by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }


    @Override
    /**
     * @return String with task formatted to be saved into duke.txt
     */
    public String toSave() {
        return this.getTaskIcon() + SEPARATOR + convertBoolean()
                + SEPARATOR + this.getDescription()
                + SEPARATOR + this.date;
    }
    @Override
    public String toString() {
        return String.format("[%s]%s %s %s",
                this.getTaskIcon(), this.getStatusIcon(),
                this.getDescription(), this.dateFormatter());

    }
}
