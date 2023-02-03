package duke;

/**
 * ToDo is a type of task which consist of the task details only.
 */
public class ToDo extends Task {

    public ToDo(String s) {
        super(s);
    }

    public ToDo(Boolean isTaskDone, String taskDetails) {
        super(taskDetails);
        if (isTaskDone) {
            this.markDone();
        }
    }
    @Override
    public String writeToFile() {
        if (!taskDone) {
            return "T| |" + this.taskName;
        }
        return "T|X|" + this.taskName;
    }

    @Override
    public String toString() {
        if (!taskDone) {
            return "[T][ ] " + this.taskName;
        }
        return "[T][X] " + this.taskName;
    }

}
