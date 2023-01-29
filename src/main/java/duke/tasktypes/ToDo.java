package duke.tasktypes;

import duke.DukeExceptions;

public class ToDo extends Task {
    
    public ToDo(String taskName) throws DukeExceptions{
        super(taskName);
        if (taskName.length() <= 0 || taskName.isBlank()) {
            throw new DukeExceptions("todo");
        }
    }

    @Override
    public String toString() {
        String toReturn = "";
        if (this.done) {
            toReturn = "[T][X]" + this.name;
        } else {
            toReturn = "[T][ ]" + this.name;
        }
        return toReturn;
    }
}
