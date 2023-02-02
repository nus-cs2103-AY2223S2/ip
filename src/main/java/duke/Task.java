package duke;

public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the parsed task in the form of {task type} | {isDone} | {task name} | {Date}
     * as a string
     *
     * @return String contains task data
     */
    public String getParsedTaskDataString() {
        char taskType;

        if (this instanceof Todo) {
            taskType = 'T';
            return String.format("%c|%b|%s", taskType, isDone, taskName);
        } else if (this instanceof Deadline) {
            taskType = 'D';
            Deadline deadline = (Deadline) this;
            return String.format("%c|%b|%s|%s", taskType, isDone, taskName, deadline.getBy());
        } else {
            taskType = 'E';
            Event event = (Event) this;
            return String.format("%c|%b|%s|%s|%s", taskType, isDone, taskName, event.getFrom(), event.getTo());
        }
    }

    public String toString() {
        if (isDone) {
            return "[X]" + taskName;
        } else {
            return "[ ]" + taskName;
        }
    }
}
