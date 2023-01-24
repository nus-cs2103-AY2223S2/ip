package duke.task;

import java.time.LocalDate;

/**
 * A TodoTask class that encapsulates the information a Todo Task.
 */
public class TodoTask extends DukeTask {
    /**
     * Constructs a TodoTask object with the given task information.
     * @param info The task information.
     */
    public TodoTask(String info) {
        super(info, TaskType.TODO);
    }

    /**
     * Returns the task information in the format suitable for storage.
     * @return The task information in the storage format.
     */
    @Override
    public String storageString() {
        String status = "[ ] | ";
        if (this.getStatus()) {
            status = "[X] | ";
        }
        return "[T] | " + status + this.getInformation();
    }

    /**
     * Determines whether the task matches the given date.
     * @param date The date to check against.
     * @return false, as TodoTasks do not have a date.
     */
    @Override
    public boolean matchesDate(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representation of the task.
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
