package duke.task;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A TodoTask class that encapsulates the information a Todo Task.
 */
public class TodoTask extends DukeTask {
    private static final String STORAGE_FORMAT = "[T] | %s | %s";
    private static final String PRINT_FORMAT = "[T]%s %s";

    /**
     * Constructs a TodoTask object with the given task information.
     *
     * @param info The task information.
     */
    public TodoTask(String info) {
        super(info, TaskType.TODO);
    }

    /**
     * Returns the task information in the format suitable for storage.
     *
     * @return The task information in the storage format.
     */
    @Override
    public String storageString() {
        // Format the task status, task information into a single string
        String isCompleted = this.getStatus() ? "[X]" : "[ ]";
        return String.format(STORAGE_FORMAT, isCompleted, this.getInformation().trim());
    }

    /**
     * Determines whether the task matches the given date.
     *
     * @param date The date to check against.
     * @return false, as TodoTasks do not have a date.
     */
    @Override
    public boolean matchesDate(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        String isCompleted = this.getStatus() ? "[X]" : "[ ]";
        return String.format(PRINT_FORMAT, isCompleted, this.getInformation());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof TodoTask)) {
            return false;
        }

        TodoTask todoObj = (TodoTask) obj;
        return Objects.equals(this.getInformation(), todoObj.getInformation())
                && this.getStatus() == todoObj.getStatus();
    }
}
