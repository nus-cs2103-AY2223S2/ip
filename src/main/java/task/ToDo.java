package task;

import task.Task;

/**
 * ToDo is a type of task which consist of the task details only.
 */
public class ToDo extends Task {

    /**
     * Create a undone ToDo task which consist task details only.
     *
     * @param s Task details.
     */
    public ToDo(String s) {
        super(s);
    }

    /**
     * Creates an instance of ToDo that takes in task status.
     *
     * @param isTaskDone
     * @param taskDetails
     */
    public ToDo(Boolean isTaskDone, String taskDetails) {
        super(taskDetails);
        if (isTaskDone) {
            this.markDone();
        }
    }
    @Override
    public String writeToFile() {
        if (!isTaskDone) {
            return "T| |" + this.taskName;
        }
        return "T|X|" + this.taskName;
    }

    @Override
    public String toString() {
        if (!isTaskDone) {
            return "[T][ ] " + this.taskName;
        }
        return "[T][X] " + this.taskName;
    }

}
