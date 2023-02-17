package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import duke.exceptions.DeadlineByNotSpecified;
import duke.exceptions.TaskNameNotSpecified;
import duke.parsing.Parser;


/**
 * Wrapper class for 'Deadline' tasks
 */
public class Deadline extends Task {
    private String dueDate;
    private Optional<LocalDate> chornoDueDate;

    /**
     * Constructor method.
     *
     * @param taskName Task name
     * @param dueDate Due date of task
     * @param isDone Task completion status
     */
    public Deadline(String taskName, String dueDate, boolean isDone) {
        super(taskName, "D", isDone);
        this.dueDate = dueDate;
        this.chornoDueDate = Parser.parseDate(dueDate);
    }

    /**
     * Factory method.
     *
     * @param commandInput Command line input that the user entered.
     * @return New Deadline task
     * @throws TaskNameNotSpecified Task name was not specified
     * @throws DeadlineByNotSpecified Task due date was not specified
     */
    public static Deadline create(String commandInput) throws TaskNameNotSpecified, DeadlineByNotSpecified {
        String[] parseInfo = Parser.parseDeadlineCmd(commandInput);
        return new Deadline(parseInfo[0], parseInfo[1], false);
    }

    /**
     * Represents fields of this task as a string.
     *
     * @return String representation of fields in this task
     */
    @Override
    public String stringFields() {
        String dateString = this.chornoDueDate.isEmpty() ? dueDate
                : chornoDueDate.get().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format(" (by: %s)", dateString);
    }

    /**
     * Gets due date of task.
     *
     * @return due date of task
     */
    @Override
    public LocalDate getEndDate() {
        return this.chornoDueDate.get();
    }
}
