package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task class representing a simple task that has a specific date range
 * @author Junyi
 */
public class EventTask extends Task {
    /* Start date of the task */
    protected LocalDate from;

    /* End date of the task */
    protected LocalDate to;

    /**
     * Constructor to create a new instance of duke.task.Task.
     *
     * @param description Title of the task.
     * @param isDone True if task is completed.
     * @param from Start date of this task.
     * @param to End date of this task.
     */
    private EventTask(String description, boolean isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        assert from != null && to != null;
        assert from.isBefore(to) || from.isEqual(to);

        this.from = from;
        this.to = to;
    }

    /**
     * Constructor to create a new instance of duke.task.Task.
     * Tasks created are by default not completed.
     *
     * @param description Title of the task.
     * @param from Start date of this task.
     * @param to End date of this task.
     */
    public EventTask(String description, LocalDate from, LocalDate to) {
        this(description, false, from, to);
    }

    /**
     * Returns true if the target date falls between the start and end date of this event.
     * @param targetDate The mentioned target date.
     * @return True if the target date is during this event period of interest.
     */
    public boolean checkDateDuringTask(LocalDate targetDate) {
        return (from.isEqual(targetDate) || from.isBefore(targetDate))
                && (to.isEqual(targetDate) || to.isAfter(targetDate));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String serialise() {
        String serialisedString = super.serialise();
        StringBuilder stringBuilder = new StringBuilder(serialisedString);
        stringBuilder.insert(0, "E");
        stringBuilder.append(String.format(",%s,%s", from, to));

        return stringBuilder.toString();
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

        if (this.from == comparableDate) {
            return 0;
        } else if (this.from.isAfter(comparableDate)) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Returns an instance of the task represented by the given data.
     * @param data The serialised string of the task.
     * @return An instance of duke.task.EventTask.
     */
    public static Task deserialise(String data) {
        String[] args = data.split(",");

        boolean taskDone = args[1].equals("Y");
        String taskDesc = args[2];
        LocalDate taskFrom = LocalDate.parse(args[3]);
        LocalDate taskTo = LocalDate.parse(args[4]);

        return new EventTask(taskDesc, taskDone, taskFrom, taskTo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String fromString = from.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String toString = to.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return "[E]" + super.toString() + " (from: " + fromString + " to: " + toString + ")";
    }
}
