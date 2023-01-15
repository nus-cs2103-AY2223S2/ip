package task;

import exception.MissingParameterException;

public class ToDoTask extends Task {
    public ToDoTask(String description) throws MissingParameterException {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
