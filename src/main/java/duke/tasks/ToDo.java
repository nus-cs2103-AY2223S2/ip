package duke.tasks;

import duke.Parser;
import duke.exceptions.TaskNameNotSpecified;

public class ToDo extends Task {

    // Factory method
    public static ToDo create(String commandInput) throws TaskNameNotSpecified {
        try {
            return new ToDo(Parser.parseToDoCmd(commandInput)[0], false);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TaskNameNotSpecified("ToDo description cannot be empty.");
        }
    }

    public ToDo(String taskName, boolean isDone) {
        super(taskName, "T");
        this.completed = isDone;
    }

    @Override
    public String stringFields() {
        return "";
    }
}