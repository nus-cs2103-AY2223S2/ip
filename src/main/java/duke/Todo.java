package duke;

import duke.Task;

public class Todo extends Task {
    /**
     * Constructs an Todo task with the given description and completion status.
     * @param description The description of Todo task.
     * @param isDone The done status of the Todo task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Constructs an Todo task with the given description. isDone status defaults to false.
     * @param description The description of Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats the Todo task to be printed by Duke.
     * @return The formatted Todo task as a String.
     */
    @Override
    public String printTask() {
        return String.format("[T][%s] %s",
                (super.isDone() ? "X" : " "),
                super.getDescription());
    }

    /**
     * Formats the Todo task to be saved in a format recognisable by Storage.
     * @return The formatted Todo task as a String.
     */
    @Override
    public String formatTask() {
        return String.format("todo~-~-~%s~-~-~%s",
                this.getDescription(),
                this.isDone() ? "X" : "O");
    }
}
