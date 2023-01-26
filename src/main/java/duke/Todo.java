package duke;

import duke.Task;

public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public Todo(String description) {
        super(description);
    }

    @Override
    public String printTask() {
        return String.format("[T][%s] %s",
                (super.isDone() ? "X" : " "),
                super.getDescription());
    }
    
    @Override
    public String formatTask() {
        return String.format("todo~-~-~%s~-~-~%s", this.getDescription(), this.isDone() ? "X" : "O");
    }
}
