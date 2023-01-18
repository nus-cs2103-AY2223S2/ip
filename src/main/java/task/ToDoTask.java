package task;

import exception.MissingParameterException;

public class ToDoTask extends Task {
    public ToDoTask(String description) throws MissingParameterException {
        super(description);
    }

    public ToDoTask(String description, boolean isDone) throws MissingParameterException {
        super(description, isDone);
    }

    @Override
    public String serialize() {
        String[] data = {"T", String.valueOf(this.isDone()), this.getDescription()};
        return String.join(" / ", data);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
