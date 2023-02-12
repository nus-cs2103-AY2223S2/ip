package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task class representing a task with a deadline
 * @author Junyi
 */
public class DeadlineTask extends Task {
    /* The deadline of the task */
    protected LocalDate by;

    /**
     * Constructor to create a new instance of duke.task.Task.
     *
     * @param description Title of the task
     * @param isDone True if task is completed.
     * @param by Deadline of this task.
     */
    private DeadlineTask(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Constructor to create a new instance of duke.task.Task.
     * Tasks created are by default not completed.
     *
     * @param description Title of the task
     * @param by Deadline of this task.
     */
    public DeadlineTask(String description, LocalDate by) {
        this(description, false, by);
    }

    /**
     * Returns true if the target date is before or during this event.
     * @param targetDate The mentioned date.
     * @return True if the given date occurs before or during this event.
     */
    public boolean checkDateIsBefore(LocalDate targetDate) {
        return by.isEqual(targetDate) || by.isAfter(targetDate);
    }

    @Override
    public int compareTo(Task task) {
        LocalDate comparableDate;
        if (task instanceof EventTask) {
            comparableDate = ((EventTask) task).from;
        } else if (task instanceof DeadlineTask) {
            comparableDate = ((DeadlineTask) task).by;
        } else {
            // Non-comparable, they are equal
            return 0;
        }

        if (this.by == comparableDate) {
            return 0;
        } else if (this.by.isAfter(comparableDate)) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String serialise() {
        String serialisedString = super.serialise();
        StringBuilder stringBuilder = new StringBuilder(serialisedString);
        stringBuilder.insert(0, "D");
        stringBuilder.append(String.format(",%s", by));

        return stringBuilder.toString();
    }

    /**
     * Returns an instance of the task represented by the given data.
     * @param data The serialised string of the task.
     * @return An instance of duke.task.DeadlineTask.
     */
    public static Task deserialise(String data) {
        String[] args = data.split(",");

        boolean taskDone = args[1].equals("Y");
        String taskDesc = args[2];
        LocalDate taskBy = LocalDate.parse(args[3]);

        return new DeadlineTask(taskDesc, taskDone, taskBy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String byString = by.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }
}
