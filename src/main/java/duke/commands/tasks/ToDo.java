package duke.commands.tasks;

public class ToDo extends Task {
    private static String IDENTIFIER = "T";

    public ToDo(String description) {
        super(description, false);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Generates a letter representing the type of task.
     *
     * @return a letter representing the type of this task.
     */
    public String getTaskClass() {
        return ToDo.IDENTIFIER;
    }

    public String toString() {
        return String.format("[%s][%s] %s", this.getTaskClass(), this.getStatusIcon(), this.description);
    }
}
