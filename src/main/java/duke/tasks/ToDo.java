package duke.tasks;

import duke.exceptions.InvalidTodo;

public class ToDo extends Task{
    String tag = "T";
    public ToDo() {
        super.tag = tag;
    }
    public ToDo(String description) {
        super.tag = tag;
        super.description = description;
    }

    @Override
    public void formatDescription(String input) throws InvalidTodo {
        if (input.isBlank()) {
            throw new InvalidTodo();
        }
        super.description = input;
    }
}
