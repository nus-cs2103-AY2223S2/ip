package task;

/**
 * ToDo is a task which includes the name.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo with default isTaskDone.
     *
     * @param taskName Name of task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Constructor for Todo.
     *
     * @param taskName Name of task.
     * @param isTaskDone Status of task.
     * @param priorityLevel Priority of task.
     */
    public ToDo(String taskName, Boolean isTaskDone, PriorityLevel priorityLevel) {
        super(taskName, isTaskDone, priorityLevel);
    }

    @Override
    public String writeToFile() {
        return this.formatForWriteToFile(this.isDone(), this.getName(), this.getPriority());
    }

    private String formatForWriteToFile(Boolean isDone, String taskName, PriorityLevel priorityLevel) {
        StringBuilder s = new StringBuilder("T");

        if (priorityLevel.equals(PriorityLevel.HIGH)) {
            s.append("|h|");
        } else if (priorityLevel.equals(PriorityLevel.MID)) {
            s.append("|m|");
        } else {
            s.append("|l|");
        }

        if (isDone) {
            s.append("X");
        } else {
            s.append(" ");
        }

        s.append("|");
        s.append(taskName);

        return s.toString();
    }

    @Override
    public String toString() {
        return this.formatForUserToSee(this.isDone(), this.getName());
    }

    private String formatForUserToSee(Boolean isDone, String taskName) {
        StringBuilder s = new StringBuilder("[T][");

        if (isDone) {
            s.append("X");
        } else {
            s.append(" ");
        }
        s.append("] ");
        s.append(taskName);

        return s.toString();
    }

}
