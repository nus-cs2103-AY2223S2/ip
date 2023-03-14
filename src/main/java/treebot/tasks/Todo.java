package treebot.tasks;

/**
 * Represents a Todo Task.
 */
public class Todo extends Task{
    /**
     * Returns a Todo with the given task description.
     * @param taskDescription
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toStorageFormatString() {
        return "T|" + (isDone ? "1" : "0") + "|" + this.taskDescription;

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Todo)) {
            return false;
        }

        Todo t = (Todo) o;

        return t.taskDescription.equals(this.taskDescription);
    }

}
