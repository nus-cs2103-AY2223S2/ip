package task;

/**
 * ToDos is a variation of Tasks that carries a task description
 * @author CShuwen
 * @version 1.0
 * @since 0.0
 */
public class ToDos extends Task {
    private int isDone;

    /**
     * Create a ToDo Task
     *
     * @param description decription of a task.
     * @param isDone 0 represents the task is not done and 1 represents the task is done.
     */
    public ToDos(String description, Integer isDone) {
        super(description, isDone);
        this.isDone = isDone;
    }

    @Override
    public void markAsDone() {
        isDone = 1;
    }

    @Override
    public void unMark() {
        isDone = 0;
    }

    @Override
    public String dataFormat() {
        if (isDone == 1) {
            return "T/1/" + description;
        } else {
            return "T/0/" + description;
        }
    }

    public String getStatusIcon() {
        return (isDone == 1 ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + description;
    }
}